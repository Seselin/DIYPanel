package com.seselin.diypanel.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seselin.diypanel.R;
import com.seselin.diypanel.adapter.GridAdapter;
import com.seselin.diypanel.bean.PrizeBean;
import com.seselin.diypanel.bean.PrizeUIBean;
import com.seselin.diypanel.util.DataUtil;
import com.seselin.diypanel.util.IndexUtil;
import com.seselin.diypanel.util.PrizeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PanelView extends FrameLayout {

    protected Context mContext;
    private RecyclerView recyclerView;
    private Button btnAction;

    private List<PrizeUIBean> prizeBeans;
    private List<PrizeUIBean> beans;
    private List<Integer> indexList;
    private GridAdapter adapter;

    private int currentIndex = 0;
    private int currentTotal = 0;
    private int stayIndex = 0;
    private int spanCount = 4;

    private boolean isGameRunning = false;
    private boolean isTryToStop = false;

    private static final int DEFAULT_SPEED = 150;
    private static final int MIN_SPEED = 50;
    private static final int MAX_SPEED = 800;
    private int currentSpeed = DEFAULT_SPEED;

    private PanelListener panelListener;

    public interface PanelListener {
        void onStart(PrizeBean selectBean);

        void onStop(PrizeBean selectBean);
    }

    public void setPanelListener(PanelListener panelListener) {
        this.panelListener = panelListener;
    }

    public PanelView(@NonNull Context context) {
        this(context, null);
    }

    public PanelView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanelView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflate(context, R.layout.panel_view, this);
    }

    /**
     * @param spanCount  转盘一行数量
     * @param prizeBeans 转盘上的奖品列表
     */
    public void initPanelData(int spanCount, List<PrizeBean> prizeBeans) {
        List<PrizeUIBean> prizeUIBeans = new ArrayList<>();
        for (PrizeBean prizeBean : prizeBeans) {
            prizeUIBeans.add(PrizeUIBean.formatBean(prizeBean));
        }

        this.spanCount = spanCount;
        this.prizeBeans = new ArrayList<>();

        int totalCount = (spanCount - 1) * 4;
        int beanSize = prizeUIBeans.size();
        if (beanSize == totalCount) {
            this.prizeBeans.addAll(prizeUIBeans);
        } else if (beanSize > totalCount) {//奖品数大于格子数
            this.prizeBeans.addAll(prizeUIBeans.subList(0, totalCount));
        } else {//奖品数小于格子数
            this.prizeBeans.addAll(prizeUIBeans);
            while (this.prizeBeans.size() < totalCount) {
                //添加默认奖品
                this.prizeBeans.add(DataUtil.getDefaultPrizeBean());
            }
        }

        Collections.shuffle(this.prizeBeans);

        beans = new ArrayList<>();
        for (int i = 0; i < spanCount * spanCount; i++) {
            beans.add(new PrizeUIBean());
        }

        indexList = IndexUtil.getIndexList(spanCount);
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            PrizeUIBean bean = this.prizeBeans.get(i);
            beans.set(index, bean);
        }

        setupView();
    }

    private int getStayIndex() {
        int selected = PrizeUtil.getPrizeIndex(prizeBeans);
        if (panelListener != null) {
            panelListener.onStart(prizeBeans.get(selected));
        }
        return selected;
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recycler_view);
        btnAction = findViewById(R.id.btn_action);

        initRecyclerView();
        setActionButton();
    }

    private long getInterruptTime() {
        currentTotal++;
        if (currentTotal > 100) {
            isTryToStop = true;
        }
        if (isTryToStop) {
            currentSpeed += 50;
            if (currentSpeed > MAX_SPEED) {
                currentSpeed = MAX_SPEED;
            }
        } else {
            if (currentTotal / indexList.size() > 0) {
                currentSpeed -= 10;
            }
            if (currentSpeed < MIN_SPEED) {
                currentSpeed = MIN_SPEED;
            }
        }

        Log.d("Test_Log", "currentSpeed" + currentSpeed);
        return currentSpeed;
    }

    private void setActionButton() {
        ViewTreeObserver vto = recyclerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = recyclerView.getHeight();
                int itemGridSize = height / spanCount;
                int buttonSize = height - 2 * itemGridSize - 8;
                if (buttonSize > 2 * itemGridSize) {
                    buttonSize = 2 * itemGridSize;
                }

                RelativeLayout.LayoutParams btnParams = (RelativeLayout.LayoutParams) btnAction.getLayoutParams();
                btnParams.width = buttonSize;  //设置组件宽度
                btnParams.height = buttonSize;  //设置组件高度
                btnAction.setLayoutParams(btnParams);//将设置好的布局参数应用到控件中
            }
        });

        btnAction.setOnClickListener(view -> {
            if (isGameRunning) {
                return;
            }
            int stayIndex = getStayIndex();
            startGame(stayIndex);
        });
    }

    private void startGame(int stayIndex) {
        isGameRunning = true;
        this.stayIndex = stayIndex;
        currentTotal = 0;
        isTryToStop = false;
        currentSpeed = DEFAULT_SPEED;
        new Thread(() -> {
            while (isGameRunning) {
                try {
                    Thread.sleep(getInterruptTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isGameRunning) {
                    recyclerView.post(ViewRunnable());
                }
            }
        }).start();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, spanCount);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GridAdapter(mContext, beans);
        recyclerView.setAdapter(adapter);
    }

    private Runnable ViewRunnable() {
        return () -> {
            int preIndex = currentIndex;
            currentIndex++;
            if (currentIndex >= indexList.size()) {
                currentIndex = 0;
            }
            beans.get(indexList.get(preIndex)).setFocus(false);
            beans.get(indexList.get(currentIndex)).setFocus(true);
            adapter.notifyDataSetChanged();

            if (isTryToStop && currentSpeed > MAX_SPEED - 200 && stayIndex == currentIndex) {
                isGameRunning = false;
                if (panelListener != null) {
                    panelListener.onStop(prizeBeans.get(stayIndex));
                }
            }
        };
    }

}

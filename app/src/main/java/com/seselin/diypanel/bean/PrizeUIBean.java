package com.seselin.diypanel.bean;

/**
 * Created by Seselin on 2019/9/14.
 */
public class PrizeUIBean extends PrizeBean {

    private boolean isFocus;
    private boolean isShow;

    public PrizeUIBean() {
    }

    public PrizeUIBean(String name, int weight) {
        super(name, weight);
        isShow = true;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public static PrizeUIBean formatBean(PrizeBean bean) {
        PrizeUIBean uiBean = new PrizeUIBean();
        uiBean.setName(bean.getName());
        uiBean.setWeight(bean.getWeight());
        uiBean.setImageRes(bean.getImageRes());
        uiBean.setShow(true);
        return uiBean;
    }

}

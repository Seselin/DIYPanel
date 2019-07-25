package com.seselin.diypanel.util;


import com.seselin.diypanel.bean.PrizeBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class DataUtil {

    public static List<PrizeBean> getDevilItems() {

        List<PrizeBean> beans = new ArrayList<>();
        beans.add(new PrizeBean("彩虹怪", 16, 0));
        beans.add(new PrizeBean("帝王天使怪", 16, 0));
        beans.add(new PrizeBean("水晶 15", 224, 0));
        beans.add(new PrizeBean("水晶 100", 20, 0));
        beans.add(new PrizeBean("魔力石 1万", 289, 0));
        beans.add(new PrizeBean("魔力石 5万", 20, 0));
        beans.add(new PrizeBean("魔力石 15万", 15, 0));
        beans.add(new PrizeBean("能量 15", 240, 0));
        beans.add(new PrizeBean("竞技场出场券 5", 120, 0));
        beans.add(new PrizeBean("神秘召唤书", 20, 0));
        beans.add(new PrizeBean("符文", 15, 0));
        beans.add(new PrizeBean("魔灵", 5, 0));

        Collections.shuffle(beans);
        return beans;
    }

    public static PrizeBean getDefaultPrizeBean() {
        return new PrizeBean("未知项", 1, 0);
    }

}

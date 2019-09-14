package com.seselin.diypanel.util;


import com.seselin.diypanel.bean.PrizeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seselin on 2019/7/2.
 */
public class DataUtil {

    public static List<PrizeBean> getDevilItems() {

        List<PrizeBean> beans = new ArrayList<>();
        beans.add(new PrizeBean("彩虹怪", 16));
        beans.add(new PrizeBean("帝王天使怪", 16));
        beans.add(new PrizeBean("水晶 15", 224));
        beans.add(new PrizeBean("水晶 100", 20));
        beans.add(new PrizeBean("魔力石 1万", 289));
        beans.add(new PrizeBean("魔力石 5万", 20));
        beans.add(new PrizeBean("魔力石 15万", 15));
        beans.add(new PrizeBean("能量 15", 240));
        beans.add(new PrizeBean("竞技场出场券 5", 120));
        beans.add(new PrizeBean("神秘召唤书", 20));
        beans.add(new PrizeBean("符文", 15));
        beans.add(new PrizeBean("魔灵", 5));

        return beans;
    }

    public static PrizeBean getDefaultPrizeBean() {
        return new PrizeBean("空白区域", 0);
    }

}

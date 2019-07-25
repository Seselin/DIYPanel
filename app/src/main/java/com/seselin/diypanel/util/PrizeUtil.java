package com.seselin.diypanel.util;


import com.seselin.diypanel.bean.PrizeBean;

import java.util.List;

public class PrizeUtil {

    public static int getPrizeIndex(List<PrizeBean> prizes) {
        int random = -1;
        try {
            // 计算总权重
            int sumWeight = 0;
            for (PrizeBean p : prizes) {
                sumWeight += p.getWeight();
            }

            // 产生随机数
            int randomNumber = (int) (1 + Math.random() * sumWeight);

            // 根据随机数在所有奖品分布的区域并确定所抽奖品
            int d1 = 0;
            int d2 = 0;
            for (int i = 0; i < prizes.size(); i++) {
                d2 += prizes.get(i).getWeight();
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += prizes.get(i - 1).getWeight();
                }
                if (randomNumber > d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        return random;
    }

}

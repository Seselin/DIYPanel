package com.seselin.diypanel.util;

import java.util.ArrayList;
import java.util.List;

public class IndexUtil {

    public static boolean debug = false;

    public static void main(String[] args) {
        List<Integer> indexList = getIndexList(4);
        System.out.println(indexList.toString() + "\n end");
    }

    public static List<Integer> getIndexList(int num) {// 一行数量
        int k = 1;// 计数方向
        List<Integer> indexList = new ArrayList<>();
        while (k < 5) {
            k = operate(k, num, indexList);
        }
        return indexList;
    }

    private static int operate(int k, int num, List<Integer> indexList) {
        int i, j, x, y;
        switch (k) {
            case 1:// 上边 由左到右递增
                i = 0;
                for (j = 0; j < num; j++) {
                    x = i;
                    y = j;
                    printIndex(num, x, y, indexList);
                }
                k = 2;
                break;
            case 2:// 右边 由上到下递增
                j = num - 1;
                for (i = 1; i < num; i++) {
                    x = i;
                    y = j;
                    printIndex(num, x, y, indexList);
                }
                k = 3;
                break;
            case 3:// 下边 由右到左递减
                i = num - 1;
                for (j = 1; j < num; j++) {
                    x = i;
                    y = (num - j - 1);
                    printIndex(num, x, y, indexList);
                }
                k = 4;
                break;
            case 4:// 左边 由下到上递减
                j = 0;
                for (i = 1; i < num - 1; i++) {
                    x = (num - i - 1);
                    y = j;
                    printIndex(num, x, y, indexList);
                }
                k = 5;
                break;
        }

        return k;
    }

    private static void printIndex(int num, int x, int y, List<Integer> indexList) {
        int index = (x * num + y);
        indexList.add(index);
        if (debug) {
            System.out.println(x + "," + y + " index=" + index);
        }
    }

}

package com.example.demo.service;

import com.example.demo.service.impl.SellAdultBullByRaiseCowBreed;
import com.example.demo.service.impl.SellBullCalfByRaiseCowBreed;

import java.util.Random;

/**
 * 饲养牲畜
 *
 * @author stephen
 * @date 2020/10/23 10:21 上午
 */
public interface RaiseLivestockService {

    int MONTH_FOR_YEAR = 12;

    /**
     * 根据{@code months}获取周年
     *
     * @param months 指定月数
     * @return int 整周年时返回对应年数，否则返回0
     * @author stephen
     * @date 2020/10/26 11:44 上午
     */
    default int getYear(int months) {
        int year = 0;
        if (months % MONTH_FOR_YEAR == 0) {
            year = months / MONTH_FOR_YEAR;
        }
        return year;
    }

    /**
     * 饲养
     *
     * @param initNum 初始饲养数量
     * @param initCost 初始成本(元/头)
     * @param initAge 初始年龄(月)
     * @param raiseYears 饲养年限
     * @author stephen
     * @date 2020/10/23 11:05 上午
     */
    void raise(int initNum, int initCost, int initAge, int raiseYears);

    public static void main(String[] args) {
        RaiseLivestockService raiseLivestockService = new SellAdultBullByRaiseCowBreed();
//        RaiseLivestockService raiseLivestockService = new SellBullCalfByRaiseCowBreed();
        raiseLivestockService.raise(20, 15000, 6, 10);
    }
}

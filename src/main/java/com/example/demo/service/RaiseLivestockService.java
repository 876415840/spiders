package com.example.demo.service;

import com.example.demo.service.impl.SellAdultBullByRaiseCowBreed;

/**
 * 饲养牲畜
 *
 * @author stephen
 * @date 2020/10/23 10:21 上午
 */
public interface RaiseLivestockService {

    int MONTH_FOR_YEAR = 12;

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
        raiseLivestockService.raise(10, 15000, 6, 15);
    }
}

package com.example.demo.service.impl;

import com.example.demo.service.AbstractRaiseCowService;

/**
 * 饲养母牛生产 - 卖成年公牛模式
 *
 * @author stephen
 * @date 2020/10/23 11:10 上午
 */
public class SellAdultBullByRaiseCowBreed extends AbstractRaiseCowService {

    @Override
    protected int getBullRaiseCost() {
        // 成本随时间是由低到高(均价400)
        return 400;
    }

    @Override
    protected int getBullSellPhase() {
        return 18;
    }

    @Override
    protected int getBullSellPrice() {
        // 20年最低售价2W+
        return 20000;
    }
}

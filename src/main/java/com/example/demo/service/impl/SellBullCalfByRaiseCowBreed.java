package com.example.demo.service.impl;

import com.example.demo.service.AbstractRaiseCowService;

/**
 * 饲养母牛生产 - 卖公牛犊模式
 *
 * @author stephen
 * @date 2020/10/26 11:20 上午
 */
public class SellBullCalfByRaiseCowBreed extends AbstractRaiseCowService {
    
    @Override
    protected int getBullRaiseCost() {
        return 300;
    }

    @Override
    protected int getBullSellPhase() {
        return 6;
    }

    @Override
    protected int getBullSellPrice() {
        // 20年6月牛犊售价最低1W+
        return 10000;
    }

}

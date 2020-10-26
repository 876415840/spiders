package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 饲养母牛抽象类
 *
 * @author stephen
 * @date 2020/10/23 11:12 上午
 */
public abstract class AbstractRaiseCowService implements RaiseLivestockService {

    /**
     * 初始生产周期(月)
     */
    protected static final int INIT_PARTAL_PHASE = 30;
    /**
     * 正常生产周期(月)
     */
    protected static final int NORMAL_PARTAL_PHASE = 12;
    /**
     * 正常生产最大年龄(月)
     * 11次
     */
    protected static final int NORMAL_PARTAL_MAX_AGE = INIT_PARTAL_PHASE + (NORMAL_PARTAL_PHASE * 10);
    /**
     * 不能生产母牛出售获利(元)
     */
    protected static final int PARTAL_COW_SELL_PROFIT = 10000;
    /**
     * 顺利生产比例
     */
    protected static final BigDecimal PARTAL_RATIO = new BigDecimal("0.8");
    /**
     * 母牛饲养成本(元/月)
     */
    protected static final int COW_RAISE_COST = 330;

    /**
     * 生产(分娩及之后正常成长)
     * 根据指定比例，得出随机结果
     *
     * @return 成功或失败
     */
    protected final boolean breed() {
        int maxVal = 1000000;
        int partalRatio = PARTAL_RATIO.multiply(BigDecimal.valueOf(maxVal)).intValue();
        // 获取一个随机数
        int luckyNumber = new Random().nextInt(maxVal);
        return luckyNumber <= partalRatio;
    }

}

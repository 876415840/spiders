package com.example.demo.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
     * 不能生产母牛出售价格(元)
     */
    protected static final int PARTAL_COW_SELL_PRICE = 10000;
    /**
     * 顺利生产比例
     */
    protected static final BigDecimal PARTAL_RATIO = new BigDecimal("0.8");
    /**
     * 生产公牛比例
     */
    protected static final BigDecimal BULL_RATIO = new BigDecimal("0.9");
    /**
     * 母牛饲养成本(元/月)
     */
    protected static final int COW_RAISE_COST = 330;

    /**母牛年龄*/
    protected Map<Integer, Integer> cowAges;
    /**公牛年龄*/
    protected Map<Integer, Integer> bullAges;
    /**成本(元)*/
    protected int cost;
    /**出售金额累计(元)*/
    protected int totalSellPrice;
    /**出售母牛数量-到期不可育*/
    protected int sellCowNum = 0;
    /**出售公牛数量*/
    protected int sellBullNum = 0;

    private int cowNum;
    private int bullNum = 0;

    @Override
    public void raise(int initNum, int initCost, int initAge, int raiseYears) {
        init(initNum, initCost, initAge, raiseYears);
        int raiseMonths = raiseYears * MONTH_FOR_YEAR;

        int costTemp = cost;
        int sellCowTempNum = sellCowNum;
        int sellBullTempNum = sellBullNum;
        int totalSellPriceTemp = totalSellPrice;
        int cowSizeTemp = cowAges.size();
        int bullSizeTemp = bullAges.size();
        for (int i = 1; i <= raiseMonths; i++) {
            // 饲养公牛
            raiseBull(getBullRaiseCost(), getBullSellPhase(), getBullSellPrice());
            // 饲养母牛
            raiseCow();
            int year = getYear(i);
            if (year > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("第[").append(year).append("]年，投入[").append(cost - costTemp).append("]元，累计投入[").append(cost).append("]元；");
                costTemp = cost;
                if (totalSellPriceTemp != totalSellPrice) {
                    sb.append("售卖收入[").append(totalSellPrice - totalSellPriceTemp).append("]元；累计售卖收入[").append(totalSellPrice).append("]元；");
                    totalSellPriceTemp = totalSellPrice;
                }

                int bullSize = bullAges.size();
                if (bullSizeTemp != bullSize) {
                    int newBull = bullSize - bullSizeTemp;
                    if (sellBullNum != sellBullTempNum) {
                        newBull += sellBullNum - sellBullTempNum;
                    }
                    sb.append("现存待售公牛[").append(bullSize).append("]头，当年新生[").append(newBull).append("]头；");
                    bullSizeTemp = bullAges.size();
                }
                if (sellBullNum != sellBullTempNum) {
                    sb.append("售卖公牛[").append(sellBullNum - sellBullTempNum).append("]头，累计售卖[").append(sellBullNum).append("]头；");
                    sellBullTempNum = sellBullNum;
                }

                int cowSize = cowAges.size();
                if (cowSizeTemp != cowSize) {
                    long partalNum = cowAges.values().stream().filter(v -> v >= INIT_PARTAL_PHASE - NORMAL_PARTAL_PHASE).count();
                    int newCow = cowSize - cowSizeTemp;
                    if (sellCowTempNum != sellCowNum) {
                        newCow += sellCowNum - sellCowTempNum;
                    }
                    sb.append("现存可育母牛[").append(partalNum).append("]头，幼母牛[").append(cowSize - partalNum).append("]头，当年新生母牛[").append(newCow).append("]头；");
                    cowSizeTemp = cowSize;
                }
                if (sellCowTempNum != sellCowNum) {
                    sb.append("售卖到期不能生产母牛[").append(sellCowNum - sellCowTempNum).append("]头，累计售卖[").append(sellCowNum).append("]头；");
                    sellCowTempNum = sellCowNum;
                }
                System.out.println(sb.toString());
            }
        }
    }

    /**
     * 公牛饲养成本(元/月)
     *
     * @return int
     * @author stephen
     * @date 2020/10/26 3:22 下午
     */
    protected abstract int getBullRaiseCost();
    /**
     * 公牛售卖周期(月)
     * @return int
     * @author stephen
     * @date 2020/10/26 3:21 下午
     */
    protected abstract int getBullSellPhase();
    /**
     * 公牛出售价格(元)
     *
     * @return int
     * @author stephen
     * @date 2020/10/26 3:23 下午
     */
    protected abstract int getBullSellPrice();

    private void init(int initNum, int initCost, int initAge, int raiseYears) {
        cowNum = initNum;
        cowAges = new HashMap<>(initNum * raiseYears);
        bullAges = new HashMap<>(raiseYears);
        for (int i = 1; i <= initNum; i++) {
            cowAges.put(i, initAge);
        }
        cost += initCost * initNum;
        System.out.println("初始养殖母牛[" + initNum + "]头，成本[" + cost + "]元");
    }

    /**
     * 命中比例
     *
     * @param ratio 指定比例
     * @return boolean
     * @author stephen
     * @date 2020/10/26 3:55 下午
     */
    private boolean hitRatio(BigDecimal ratio) {
        int maxVal = 1000000;
        int partalRatio = ratio.multiply(BigDecimal.valueOf(maxVal)).intValue();
        // 获取一个随机数
        int luckyNumber = new Random().nextInt(maxVal);
        return luckyNumber <= partalRatio;
    }

    /**
     * 饲养母牛
     *
     * @author stephen
     * @date 2020/10/26 11:48 上午
     */
    private void raiseCow() {
        int cowNewNum = 0;
        Iterator<Map.Entry<Integer, Integer>> cowAgeIt = cowAges.entrySet().iterator();
        int tempCost = 0;
        while (cowAgeIt.hasNext()) {
            Map.Entry<Integer, Integer> entry = cowAgeIt.next();
            int newAge = entry.getValue() + 1;
            entry.setValue(newAge);
            tempCost += COW_RAISE_COST;
            // 生产
            boolean isBreed = (newAge > INIT_PARTAL_PHASE && newAge % NORMAL_PARTAL_PHASE == 0)
                    || newAge == INIT_PARTAL_PHASE;
            if (isBreed && hitRatio(PARTAL_RATIO)) {
                if (hitRatio(BULL_RATIO)) {
                    bullAges.put(++bullNum, 0);
                } else {
                    cowNewNum++;
                }
            }

            if (newAge > NORMAL_PARTAL_MAX_AGE) {
                cowAgeIt.remove();
                sellCowNum++;
                totalSellPrice += PARTAL_COW_SELL_PRICE;
            }
        }
        cost += tempCost;
        for (int i = 0; i < cowNewNum; i++) {
            cowAges.put(++cowNum, 0);
        }
    }

    /**
     * 饲养公牛
     *
     * @param raiseCost 饲养成本(元/月)
     * @param phase 饲养周期(月)
     * @param sellPrice 售卖价格
     * @author stephen
     * @date 2020/10/26 11:55 上午
     */
    private void raiseBull(int raiseCost, int phase, int sellPrice) {
        if (!bullAges.isEmpty()) {
            Iterator<Map.Entry<Integer, Integer>> bullAgeIt = bullAges.entrySet().iterator();
            /*
             * 每月将每个公牛年龄加1月，成本也加1月饲养成本
             * 年龄加过后达到出栏年龄，出售获利
             */
            while (bullAgeIt.hasNext()) {
                Map.Entry<Integer, Integer> entry = bullAgeIt.next();
                int newAge = entry.getValue() + 1;
                cost += raiseCost;
                if (newAge < phase) {
                    entry.setValue(newAge);
                } else {
                    bullAgeIt.remove();
                    sellBullNum++;
                    totalSellPrice += sellPrice;
                }
            }
        }
    }

}

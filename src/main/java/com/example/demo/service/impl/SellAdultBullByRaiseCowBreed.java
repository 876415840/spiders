package com.example.demo.service.impl;

import com.example.demo.service.AbstractRaiseCowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 饲养母牛生产 - 卖成年公牛模式
 *
 * @author stephen
 * @date 2020/10/23 11:10 上午
 */
public class SellAdultBullByRaiseCowBreed extends AbstractRaiseCowService {

    private static final Logger log = LoggerFactory.getLogger(SellAdultBullByRaiseCowBreed.class);

    /**
     * 成年公牛周期(月)
     * 出栏周期
     */
    private static final int ADULT_BULL_PHASE = 18;
    /**
     * 成年公牛出售获利(元)
     */
    private static final int ADULT_BULL_SELL_PROFIT = 20000;
    /**
     * 公牛饲养成本(元/月)
     * 成本随时间是由低到高
     */
    private static final int ADULT_BULL_RAISE_COST = 400;
    /**母牛年龄*/
    private Map<Integer, Integer> cowAges;
    /**公牛年龄*/
    private Map<Integer, Integer> bullAges;
    /**成本(元)*/
    private int cost;
    /**出售获利(元)*/
    private int sellProfit;
    private int cowNum;
    private int bullNum = 0;
    private int sellCowNum = 0;
    private int sellBullNum = 0;
    private boolean cowOrBull = true;

    @Override
    public void raise(int initNum, int initCost, int initAge, int raiseYears) {
        init(initNum, initCost, initAge, raiseYears);
        int raiseMonths = raiseYears * MONTH_FOR_YEAR;

        int costTemp = cost;
        int sellCowTempNum = sellCowNum;
        int sellBullTempNum = sellBullNum;
        int sellProfitTemp = sellProfit;
        int cowSizeTemp = cowAges.size();
        int bullSizeTemp = bullAges.size();
        for (int i = 1; i <= raiseMonths; i++) {
            // 饲养公牛
            raiseBull();
            // 饲养母牛
            raiseCow();
            int year = getYear(i);
            if (year > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("第[").append(year).append("]年，投入[").append(cost - costTemp).append("]元，累计投入[").append(cost).append("]元；");
                costTemp = cost;
                if (sellProfitTemp != sellProfit) {
                    sb.append("售卖收入[").append(sellProfit - sellProfitTemp).append("]元；累计售卖收入[").append(sellProfit).append("]元；");
                    sellProfitTemp = sellProfit;
                }

                int bullSize = bullAges.size();
                if (bullSizeTemp != bullSize) {
                    int newBull = bullSize - bullSizeTemp;
                    if (sellBullNum != sellBullTempNum) {
                        newBull += sellBullNum - sellBullTempNum;
                    }
                    sb.append("现存幼年公牛[").append(bullSize).append("]头，当年新生[").append(newBull).append("]头；");
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
                log.info(sb.toString());
            }
        }
    }

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
            if (isBreed && breed()) {
                // 50% 公/母
                if (cowOrBull) {
                    cowNewNum++;
                } else {
                    bullAges.put(++bullNum, 0);
                }
                cowOrBull = !cowOrBull;
            }

            if (newAge > NORMAL_PARTAL_MAX_AGE) {
                cowAgeIt.remove();
                sellCowNum++;
                sellProfit += PARTAL_COW_SELL_PROFIT;
            }
        }
        cost += tempCost;
        for (int i = 0; i < cowNewNum; i++) {
            cowAges.put(++cowNum, 0);
        }
    }

    private int getYear(int months) {
        int year = 0;
        if (months % MONTH_FOR_YEAR == 0) {
            year = months / MONTH_FOR_YEAR;
        }
        return year;
    }

    private void raiseBull() {
        if (!bullAges.isEmpty()) {
            Iterator<Map.Entry<Integer, Integer>> bullAgeIt = bullAges.entrySet().iterator();
            /*
             * 每月将每个公牛年龄加1月，成本也加1月饲养成本
             * 年龄加过后达到出栏年龄，出售获利
             */
            while (bullAgeIt.hasNext()) {
                Map.Entry<Integer, Integer> entry = bullAgeIt.next();
                int newAge = entry.getValue() + 1;
                cost += ADULT_BULL_RAISE_COST;
                if (newAge < ADULT_BULL_PHASE) {
                    entry.setValue(newAge);
                } else {
                    bullAgeIt.remove();
                    sellBullNum++;
                    sellProfit += ADULT_BULL_SELL_PROFIT;
                }
            }
        }
    }

    private void init(int initNum, int initCost, int initAge, int raiseYears) {
        cowNum = initNum;
        cowAges = new HashMap<>(initNum * raiseYears);
        bullAges = new HashMap<>(raiseYears);
        for (int i = 1; i <= initNum; i++) {
            cowAges.put(i, initAge);
        }
        cost += initCost * initNum;
        log.info("初始养殖母牛[{}]头，成本[{}]元", initNum, cost);
    }

}

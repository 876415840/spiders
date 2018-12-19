package com.example.demo.crawler

import com.alibaba.fastjson.JSON
import com.geccocrawler.gecco.annotation.PipelineName
import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.scheduler.SchedulerContext
import java.util.*

/**
 * @Description TODO
 * @Author mengqinghao
 * @Date 2018/12/19 6:08 PM
 * @Version 1.0
 */
@PipelineName("bjLianJiaSecondHandHouse")
class HandleBjLianJiaSecondHandHouse : Pipeline<BjLianJiaSecondHandHouse> {

    /**
     * @Description: 处理抓取内容
     * @author mengqinghao
     * @param:
     * @date 2018/12/19 6:10 PM
     * @return:
     */
    override fun process(secondHandHouse: BjLianJiaSecondHandHouse?) {
        println("===========>"+JSON.toJSONString(secondHandHouse))

        // todo 处理数据
    }
}
package com.example.demo.service.impl

import com.example.demo.service.RealtyDataService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

/**
 * @Description 房地产数据接口实现
 * @Author mengqinghao
 * @Date 2018/11/19 3:38 PM
 * @Version 1.0
 */
@Service
class RealtyDataServiceImpl : RealtyDataService {

    private val LOGGER: Logger = LoggerFactory.getLogger(RealtyDataServiceImpl::class.java)
    
    /**
     * @Description: 爬取数据
     * @author mengqinghao
     * @param:
     * @date 2018/11/19 3:54 PM
     * @return: 
     */
    override fun spiderData() {
        LOGGER.info("爬取数据-------------")
    }

}
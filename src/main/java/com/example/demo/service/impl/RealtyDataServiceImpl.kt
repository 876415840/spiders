package com.example.demo.service.impl

import com.alibaba.fastjson.JSON
import com.example.demo.entity.User
import com.example.demo.mapper.UserMapper
import com.example.demo.service.RealtyDataService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description 房地产数据接口实现
 * @Author mengqinghao
 * @Date 2018/11/19 3:38 PM
 * @Version 1.0
 */
@Service
class RealtyDataServiceImpl : RealtyDataService {

    private val LOGGER: Logger = LoggerFactory.getLogger(RealtyDataServiceImpl::class.java)

    @Autowired
    lateinit var userMapper: UserMapper

    /**
     * @Description: 爬取数据
     * @author mengqinghao
     * @param:
     * @date 2018/11/19 3:54 PM
     * @return: 
     */
    override fun spiderData() {
        var user: User  = userMapper.getUserById(1)
        LOGGER.info("爬取数据-------------{}", JSON.toJSONString(user))
    }

}
package com.example.demo.service.impl

import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.pipeline.PipelineFactory
import com.geccocrawler.gecco.spider.SpiderBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service
import org.springframework.beans.factory.NoSuchBeanDefinitionException



/**
 * @Description TODO
 * @Author mengqinghao
 * @Date 2018/12/29 5:23 PM
 * @Version 1.0
 */
@Service
class SpringPipelineFactory :  PipelineFactory, ApplicationContextAware {

    private var applicationContext: ApplicationContext? = null

    override fun getPipeline(name: String?): Pipeline<out SpiderBean>? {
        try {
            val bean = name?.let { applicationContext!!.getBean(it) }
            if (bean is Pipeline<*>) {
                return bean
            }
        } catch (ex: NoSuchBeanDefinitionException) {
            System.out.println("no such pipeline : $name")
        }
        return null
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }
}
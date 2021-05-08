package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.EsTestService;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinghao.meng created on 2021/5/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EsTest.class);

    @Resource
    private EsTestService esTestService;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void save() {
        com.example.demo.entity.EsTest esTest = com.example.demo.entity.EsTest.builder().testId("1").name("AA").desc("nothing").build();
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(esTest).build();
        String result = elasticsearchTemplate.index(indexQuery);
        LOGGER.info("-------index-- {}", result);
        esTest = com.example.demo.entity.EsTest.builder().testId("2").name("BB").desc("....88.").build();
        esTestService.save(esTest);
    }

    @Test
    public void query() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("name", "AA")).build();
        List<com.example.demo.entity.EsTest> list = elasticsearchTemplate.queryForList(searchQuery, com.example.demo.entity.EsTest.class);
        LOGGER.info("query：{}", JSON.toJSONString(list));
    }
}

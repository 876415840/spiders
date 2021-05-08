package com.example.demo.mapper;

import com.example.demo.entity.EsTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author qinghao.meng created on 2021/5/8
 */
public interface EsTestRepository extends ElasticsearchRepository<EsTest, String> {
}

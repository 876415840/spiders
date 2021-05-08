package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author qinghao.meng created on 2021/5/8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "es_test")
public class EsTest {

    @Id
    private String testId;

    private String name;

    private String desc;
}

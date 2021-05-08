package com.example.demo.service;

import com.example.demo.entity.EsTest;

import java.util.List;
import java.util.Optional;

/**
 * @author qinghao.meng created on 2021/5/8
 */
public interface EsTestService {

    /**
     * count
     *
     * @return long
     * @author qinghao.meng
     * @date 2021/5/8
     */
    long count();

    /**
     * save
     *
     * @param esTest
     * @author qinghao.meng
     * @date 2021/5/8
     */
    void save(EsTest esTest);

    /**
     * findAll
     *
     * @param id
     * @return java.util.Optional<com.example.demo.entity.EsTest>
     * @author qinghao.meng
     * @date 2021/5/8
     */
    Optional<EsTest> findById(String id);
}

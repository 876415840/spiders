package com.example.demo.service.impl;

import com.example.demo.entity.EsTest;
import com.example.demo.mapper.EsTestRepository;
import com.example.demo.service.EsTestService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author qinghao.meng created on 2021/5/8
 */
@Service
public class EsTestServiceImpl implements EsTestService {

    @Resource
    private EsTestRepository esTestRepository;

    @Override
    public long count() {
        return esTestRepository.count();
    }

    @Override
    public void save(EsTest esTest) {
        esTestRepository.save(esTest);
    }

    @Override
    public Optional<EsTest> findById(String id) {
        return esTestRepository.findById(id);
    }
}

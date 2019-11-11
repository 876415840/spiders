package com.example.demo.mapper

import com.example.demo.entity.ShuangSeQiu
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 *
 * @author 系统生成
 */
@Mapper
interface ShuangSeQiuMapper {

    @Select("select max(period) from " + ShuangSeQiu.TABLE_NAME)
    fun getMaxPeriod(): Int
}

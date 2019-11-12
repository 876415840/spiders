package com.example.demo.mapper

import com.example.demo.entity.ShuangSeQiu
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 *
 * @author 系统生成
 */
@Mapper
interface ShuangSeQiuMapper {

    @Select("select max(period) from " + ShuangSeQiu.TABLE_NAME)
    fun getMaxPeriod(): Int

    @Insert("insert into "+ ShuangSeQiu.TABLE_NAME +"(period,red1,red2,red3,red4,red5,red6,blue,create_time,update_time) " +
            "values(#{shuangSeQiu.period},#{shuangSeQiu.red1},#{shuangSeQiu.red2},#{shuangSeQiu.red3},#{shuangSeQiu.red4},#{shuangSeQiu.red5},#{shuangSeQiu.red6},#{shuangSeQiu.blue},now(),now())")
    fun save(@Param("shuangSeQiu") shuangSeQiu: ShuangSeQiu): Int
}

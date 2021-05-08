package com.example.demo.mapper

import com.example.demo.entity.JobDetail
import com.example.demo.entity.PriceChange
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 *
 * @author 系统生成
 */
@Mapper
interface JobDetailMapper {

    @Insert("insert into "+ JobDetail.TABLE_NAME +"(tech_term,count,tag,create_time,update_time) " +
            "values(#{jobDetail.techTerm},#{jobDetail.count},#{jobDetail.tag},#{jobDetail.createTime},#{jobDetail.updateTime})")
    fun save(@Param("jobDetail") jobDetail: JobDetail): Int
}
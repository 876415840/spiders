package com.example.demo.mapper

import com.example.demo.entity.PriceChange
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 *
 * @author 系统生成
 */
@Mapper
interface PriceChangeMapper {

    @Insert("insert into "+ PriceChange.TABLE_NAME +"(house_code,total_price,unit_price,create_time,update_time) " +
            "values(#{priceChange.houseCode},#{priceChange.totalPrice},#{priceChange.unitPrice},#{priceChange.createTime},#{priceChange.updateTime})")
    fun save(@Param("houseInfo") priceChange: PriceChange): Int
}
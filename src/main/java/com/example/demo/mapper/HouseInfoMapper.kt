package com.example.demo.mapper

import com.example.demo.entity.HouseInfo
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 *
 * @author 系统生成
 */
@Mapper
interface HouseInfoMapper{

    @Select("select * from "+ HouseInfo.TABLE_NAME +" where id = #{id}")
    fun getUserById(@Param("id") id: Int): HouseInfo

    @Insert("insert into "+ HouseInfo.TABLE_NAME +"(title,code,housing_estate,specification,size,orientation,decoration_type,elevator,storey_type,year,tower_type,area,total_price,type,unit_price,subway,vr,taxfree,any_time) " +
            "values(#{houseInfo.title},#{houseInfo.code},#{houseInfo.housingEstate},#{houseInfo.specification},#{houseInfo.size},#{houseInfo.orientation},#{houseInfo.decorationType},#{houseInfo.elevator},#{houseInfo.storeyType}," +
            "#{houseInfo.year},#{houseInfo.towerType},#{houseInfo.area},#{houseInfo.totalPrice},#{houseInfo.type},#{houseInfo.unitPrice},#{houseInfo.subway},#{houseInfo.vr},#{houseInfo.taxfree},#{houseInfo.anyTime})")
    fun saveUser(@Param("houseInfo") houseInfo: HouseInfo): Int
}

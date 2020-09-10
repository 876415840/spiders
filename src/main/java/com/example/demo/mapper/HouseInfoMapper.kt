package com.example.demo.mapper

import com.example.demo.entity.HouseInfo
import org.apache.ibatis.annotations.*

/**
 *
 * @author 系统生成
 */
@Mapper
interface HouseInfoMapper{

    @Results(id = "",value = [
        (Result(column = "housing_estate",property = "housingEstate")),
        (Result(column = "decoration_type",property = "decorationType")),
        (Result(column = "storey_type",property = "storeyType")),
        (Result(column = "tower_type",property = "towerType")),
        (Result(column = "total_price",property = "totalPrice")),
        (Result(column = "unit_price",property = "unitPrice")),
        (Result(column = "any_time",property = "anyTime")),
        (Result(column = "create_time",property = "createTime")),
        (Result(column = "update_time",property = "updateTime"))
    ])

    @Select("select * from "+ HouseInfo.TABLE_NAME +" where code = #{code}")
    fun getByCode(@Param("code") code: String): HouseInfo?

    @Insert("insert into "+ HouseInfo.TABLE_NAME +"(title,code,housing_estate,specification,size,orientation,decoration_type,elevator,storey_type,year,tower_type,area,total_price,type,unit_price,subway,vr,taxfree,any_time,create_time,update_time) " +
            "values(#{houseInfo.title},#{houseInfo.code},#{houseInfo.housingEstate},#{houseInfo.specification},#{houseInfo.size},#{houseInfo.orientation},#{houseInfo.decorationType},#{houseInfo.elevator},#{houseInfo.storeyType}," +
            "#{houseInfo.year},#{houseInfo.towerType},#{houseInfo.area},#{houseInfo.totalPrice},#{houseInfo.type},#{houseInfo.unitPrice},#{houseInfo.subway},#{houseInfo.vr},#{houseInfo.taxfree},#{houseInfo.anyTime},#{houseInfo.createTime},#{houseInfo.updateTime})")
    fun save(@Param("houseInfo") houseInfo: HouseInfo): Int

    @Update("update "+ HouseInfo.TABLE_NAME +" set title=#{houseInfo.title},housing_estate=#{houseInfo.housingEstate},specification=#{houseInfo.specification},size=#{houseInfo.size},orientation=#{houseInfo.orientation},decoration_type=#{houseInfo.decorationType}," +
            "elevator=#{houseInfo.elevator},storey_type=#{houseInfo.storeyType},year=#{houseInfo.year},tower_type=#{houseInfo.towerType},area=#{houseInfo.area},total_price=#{houseInfo.totalPrice},type=#{houseInfo.type},unit_price=#{houseInfo.unitPrice}," +
            "subway=#{houseInfo.subway},vr=#{houseInfo.vr},taxfree=#{houseInfo.taxfree},any_time=#{houseInfo.anyTime},update_time=#{houseInfo.updateTime} where code = #{houseInfo.code}")
    fun updateByCode(@Param("houseInfo") houseInfo: HouseInfo): Int
}

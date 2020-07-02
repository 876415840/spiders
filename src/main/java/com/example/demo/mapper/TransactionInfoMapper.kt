package com.example.demo.mapper

import com.example.demo.entity.HouseInfo
import com.example.demo.entity.TransactionInfo
import org.apache.ibatis.annotations.*

/**
 *
 * @author 系统生成
 */
@Mapper
interface TransactionInfoMapper{

//    @Results(id = "",value = [
//        (Result(column = "transaction_date",property = "transactionDate")),
//        (Result(column = "transaction_period",property = "transactionPeriod")),
//        (Result(column = "deal_price",property = "dealPrice")),
//        (Result(column = "sticker_price",property = "stickerPrice")),
//        (Result(column = "adjust_count",property = "adjustCount")),
//        (Result(column = "look_count",property = "lookCount")),
//        (Result(column = "create_time",property = "createTime")),
//        (Result(column = "update_time",property = "updateTime"))
//    ])

    @Select("select hi.`code` from " + HouseInfo.TABLE_NAME + " hi left join " + TransactionInfo.TABLE_NAME + " ti on hi.`code` = ti.`code` where ti.`code` is null limit 10000")
    fun getTransactionCodes(): List<String>

    @Select("select count(*) from " + TransactionInfo.TABLE_NAME + " where DATEDIFF(create_time,CURDATE())=0")
    fun todayTransactionCount(): Long

    @Insert("insert into "+ TransactionInfo.TABLE_NAME +"(code,transaction_date,transaction_period,deal_price,sticker_price,adjust_count,look_count,follow,create_time,update_time) " +
            "values(#{transactionInfo.code},#{transactionInfo.transactionDate},#{transactionInfo.transactionPeriod},#{transactionInfo.dealPrice},#{transactionInfo.stickerPrice},#{transactionInfo.adjustCount}," +
            "#{transactionInfo.lookCount},#{transactionInfo.follow},#{transactionInfo.createTime},#{transactionInfo.updateTime})")
    fun save(@Param("transactionInfo") transactionInfo: TransactionInfo): Int

}

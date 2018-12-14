package com.example.demo.mapper

import com.example.demo.entity.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 *
 * @author 系统生成
 */
@Mapper
interface UserMapper {

    @Select("select * from user where id = #{id}")
    fun getUserById(@Param("id") id: Int): User
}

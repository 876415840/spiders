package com.example.demo.mapper

import com.example.demo.entity.User
import org.apache.ibatis.annotations.*

const val TABLE_NAME : String = "user"

/**
 *
 * @author 系统生成
 */
@Mapper
interface UserMapper {

    @Select("select * from "+ TABLE_NAME +" where id = #{id}")
    fun getUserById(@Param("id") id: Int): User

    @Insert("insert into "+ TABLE_NAME +"(name) values(#{user.name})")
    fun saveUser(@Param("user") user: User): Int

}

package com.amber.mycommunity.mapper;

import com.amber.mycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author amber
 * @date 2021/7/26 9:57
 */

@Repository
@Mapper
public interface UserMapper{
    @Insert("insert into user (name,account_Id,token,gmt_Create,gmt_Modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}

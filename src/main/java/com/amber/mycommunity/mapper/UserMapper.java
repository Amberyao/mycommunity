package com.amber.mycommunity.mapper;

import com.amber.mycommunity.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author amber
 * @date 2021/7/26 9:57
 */

@Repository
@Mapper
public interface UserMapper{
    @Insert("insert into user (name,account_Id,token,gmt_Create,gmt_Modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Long id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name},token=#{token},gmt_Modified=#{gmtModified},avatar_url=#{avatarUrl}")
    void update(User user);
}

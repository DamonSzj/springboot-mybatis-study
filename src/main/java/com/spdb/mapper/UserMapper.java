package com.spdb.mapper;

import com.spdb.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //List<User> findAllUser();

    List<User> findAllUser(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
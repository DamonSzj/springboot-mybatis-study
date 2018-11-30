package com.spdb.service.impl;

import com.spdb.mapper.UserMapper;
import com.spdb.model.User;
import com.spdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
   @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }
    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        return userMapper.findAllUser(pageNum,pageSize);

    }
}

package com.baomidou.springmvc.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.springmvc.mapper.system.UserMapper;
import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectTest(long l) {
        //List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().select(User::getIitt, User::getName));
        //或者使用QueryWrapper
        List<User> users = userMapper.selectList(new QueryWrapper<User>().select("id", "name"));
        IPage<User> userIPage = userMapper.selectPage(new Page<User>(1, 10, true), new QueryWrapper<User>().select("id", "name"));
        return userMapper.selectTest(l);
    }

}

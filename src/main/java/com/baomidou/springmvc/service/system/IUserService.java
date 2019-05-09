package com.baomidou.springmvc.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.springmvc.model.system.User;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

    User selectTest(long l);
}
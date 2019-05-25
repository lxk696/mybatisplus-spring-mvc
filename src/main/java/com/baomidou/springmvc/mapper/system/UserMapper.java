package com.baomidou.springmvc.mapper.system;

import com.baomidou.springmvc.common.SuperMapper;
import com.baomidou.springmvc.model.system.User;
import org.apache.ibatis.annotations.Param;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

    User selectTest(@Param("iitt") long iitt);


}

package com.baomidou.springmvc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.springmvc.model.enums.TypeEnum;
import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * Author: D.Yang
 * Email: koyangslash@gmail.com
 * Date: 16/10/9
 * Time: 上午11:58
 * Describe: 用户控制器
 * 
 * 代码生成器，参考源码测试用例：
 * 
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 *
 */
@Controller
public class UserController extends BaseController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/")
    public Object list() {
        User user = userService.selectTest(1L);

        HashMap<String, Object> params = new HashMap<>(16);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().inSql("", "").likeLeft("", "'").allEq(params, true);
        String sqlSelect = userQueryWrapper.getSqlSelect();
        String customSqlSegment = userQueryWrapper.getCustomSqlSegment();
        String sqlSet = userQueryWrapper.getSqlSet();
////////////////////////
        QueryWrapper<User> apply = new QueryWrapper<User>().apply("sql ", "params");
        String sqlSelect1 = apply.getSqlSelect();
////////////////////////////
        IPage<User> userIPage = userService.page(new Page<User>(1, 10, true), new QueryWrapper<User>().setEntity(user).select("id", "name"));
        //或者使用QueryWrapper
        List<User> list = userService.list(new QueryWrapper<User>().select("id", "name"));
///////////////////////
//        SELECT exists(select * from sys_user where age = 1)
        String sqlSelect2 = new QueryWrapper<User>().exists("select id from sys_user where age = 1").getSqlSelect();

        /////////////
        return userService.list(null);
    }

    @ResponseBody
    @RequestMapping("/preSave")
    public Object preSave(@RequestParam(value = "id", required = false) Long id) {
        return userService.getById(id);
    }

    @ResponseBody
    @RequestMapping("save")
    public Object save(User user) {
        user.setType(TypeEnum.DISABLED);
        if (user.getIitt() == null) {
            return userService.save(user) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            //userService.insertOrUpdate()
            return userService.updateById(user) ? renderSuccess("修改成功") : renderError("修改失败");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam(value = "id", required = false) Long id) {
        return userService.removeById(id) ? renderSuccess("删除成功") : renderError("删除失败");
    }
}

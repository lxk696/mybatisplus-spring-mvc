package com.baomidou.springmvc.model.system;

import java.io.Serializable;
import java.util.Date;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.springmvc.model.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 系统用户表
 */
@TableName("sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    public User(String name, TypeEnum type, Integer age, Date ctime) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.ctime = ctime;
    }

    private static final long serialVersionUID = 1L;

    @TableId(value = "iitt")
    private Long iitt;

    /**
     * 用户名
     */
    // 这样可以注入 LIKE 查询 @TableField(condition = SqlCondition.LIKE)
    private String name;
    /**
     * 通用枚举测试
     */
    private TypeEnum type;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 自定义填充的创建时间
     */
    @TableField(fill = FieldFill.INSERT) // 这样可以注入更新数据库时间 , update = "now()")// 该注解插入忽略验证，自动填充
    private Date ctime;


}

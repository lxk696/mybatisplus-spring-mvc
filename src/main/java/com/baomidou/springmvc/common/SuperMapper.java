package com.baomidou.springmvc.common;

import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.function.Predicate;

/**
 * <p>
 * 测试自定义 mapper 父类
 * </p>
 */
public interface SuperMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    // 这里可以写自己的公共方法、注意不要让 mp 扫描到误以为是实体 Base 的操作
    Integer deleteAll();

    Integer insertBatchSomeColumn(List<T> list);

    Integer insertBatchSomeColumn(@Param("list") List<T> list, Predicate<TableFieldInfo> predicate);

}

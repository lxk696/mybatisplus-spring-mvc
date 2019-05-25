package com.baomidou.springmvc.common.myplus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * 自定义全局操作
 * @author liuyangos8888
 */
public class MyInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList() {
        List<AbstractMethod> methodList = super.getMethodList();
        methodList.add(new DeleteAll());
        methodList.add(new InsertBatch());
        return methodList;
    }

}

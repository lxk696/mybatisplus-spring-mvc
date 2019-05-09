package test;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.springmvc.model.enums.TypeEnum;
import com.baomidou.springmvc.model.system.User;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: D.Yang
 * Email: koyangslash@gmail.com
 * Date: 16/10/9
 * Time: 下午12:22
 * Describe:
 */
public class IdWorkerTest {

    public static void main(String[] args) {

        User user = new User().setIitt(11L).setAge(188).setName("张三四十").setType(TypeEnum.DISABLED);

        HashMap<String, Object> params = new HashMap<>(16);
        params.put("age","111");
        //QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>().inSql("age", "1,2,3,4,5").likeLeft("name", "什么歌").allEq(params, true).or().eq("type",1).or(true,()->);
        //String sqlSelect = userQueryWrapper.getSqlSelect();
        //String customSqlSegment = userQueryWrapper.getCustomSqlSegment();
        //String sqlSet = userQueryWrapper.getSqlSet();
        ////////////////////////
        QueryWrapper<User> apply = new QueryWrapper<User>().apply("sql ", "params");
        String sqlSelect1 = apply.getSqlSelect();
        String sqlSelect12 = apply.getCustomSqlSegment();
        ////////////////////////////
        QueryWrapper<User> select = new QueryWrapper<User>().setEntity(user).select("id", "name");
        String sqlSelect3 = select.getSqlSelect();
        String sqlSelect32 = select.getCustomSqlSegment();


        QueryWrapper<User> select2 = new QueryWrapper<User>().select("id", "name");
        String sqlSelect4 = select2.getSqlSelect();
        String customSqlSegment1 = select2.getCustomSqlSegment();

        ///////////////////////
        //  SELECT exists(select * from sys_user where age = 1)
        QueryWrapper<User> exists = new QueryWrapper<User>().exists("select id from sys_user where age = 1");
        String sqlSelect2 = exists.getSqlSelect();
        String exists2 = exists.getCustomSqlSegment();

        //testIdWork();
    }

    private static void testIdWork() {
        int count = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.execute(new IdWorkerTest().new Task());
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Task implements Runnable {

        public void run() {
            try {
                long id = IdWorker.getId();
                System.err.println(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

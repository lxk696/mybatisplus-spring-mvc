package test;

import com.baomidou.springmvc.mapper.system.ProductConfigMapper;
import com.baomidou.springmvc.mapper.system.UserMapper;
import com.baomidou.springmvc.model.enums.TypeEnum;
import com.baomidou.springmvc.model.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class TestDivSql {


    private static final Logger LOG = LoggerFactory.getLogger(TestDivSql.class);

    private ApplicationContext iocContext = new ClassPathXmlApplicationContext("spring/spring.xml");

    UserMapper userMapper = iocContext.getBean("userMapper", UserMapper.class);

    ProductConfigMapper productConfigMapper = iocContext.getBean("productConfigMapper", ProductConfigMapper.class);

    /**
     * 测试 自定义 sql
     * @throws SQLException
     */
    @Test
    public void testInterceptor() throws SQLException {

        List<User> list = new ArrayList<>();
        list.add(new User("name22", TypeEnum.NORMAL, 22, new Date()));
        list.add(new User("name33", TypeEnum.DISABLED, 33, new Date()));
        list.add(new User("name44", TypeEnum.NORMAL, 44, new Date()));
        Integer insertBatch = userMapper.insertBatchSomeColumn(list);
        LOG.info("insertBatchSomeColumn 成功 1 ：" + insertBatch);

        //FIXME  t -> !t.getProperty().equals("age") 带条件报错
        //Integer insertBatch = userMapper.insertBatchSomeColumn(t -> !t.isLogicDelete(), list2);
        Integer insertBatch2 = userMapper.insertBatchSomeColumn(list, t -> !t.getProperty().equals("age"));
        LOG.info("insertBatchSomeColumn 成功 2 ：" + insertBatch2);



        //int insert = userMapper.insert(new User(null,"name1", TypeEnum.DISABLED, 11, new Date()));
        //LOG.info("insert 成功 1 ：" + insert);
        //
        //int insert2 = userMapper.insert(new User("name122", TypeEnum.NORMAL, 22, new Date()));
        //LOG.info("insert 成功 1 ：" + insert2);



        //User user = userMapper.selectById(1L);
        //LOG.info("selectById成功 1 ：" + user);
        //
        //ProductConfig productConfig = productConfigMapper.selectById(1L);
        //LOG.info("selectById成功 2 ：" + productConfig);
        //
        //
        //Integer result = userMapper.deleteAll();
        //LOG.info("删除成功1：" + result);
        //
        //Integer result2 = productConfigMapper.deleteAll();
        //LOG.info("删除成功2：" + result2);
    }
}

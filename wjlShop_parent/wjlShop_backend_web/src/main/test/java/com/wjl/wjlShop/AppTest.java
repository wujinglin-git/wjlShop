package com.wjl.wjlShop;


import com.wjl.wjlShop.pojo.ProductType;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    ApplicationContext atx = new ClassPathXmlApplicationContext("spring-dao.xml");
    ProductTypeDao  dao = atx.getBean(ProductTypeDao.class );
    ProductTypeService pr= atx.getBean(ProductTypeService.class );
    @Test
    public void findAll() {

        System.out.println(pr.findAll());
    }

    @Test
    public void testQueryOrderUserResultMap() {
        // mybatis和spring整合，整合之后，交给spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        // 创建Mapper接口的动态代理对象，整合之后，交给spring管理
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 使用userMapper执行根据条件查询用户，结果封装到Order类中
        List<Product> list = userMapper.queryOrderUserResultMap();
        for (Order o : list) {
            System.out.println(o);
        }
        // mybatis和spring整合，整合之后，交给spring管理
        sqlSession.close();
    }
}
}

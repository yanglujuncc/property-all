package org.property.core.domain.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.property.core.domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
*类的作用：测试在Spring中写测试框架
*
*
*@author 一叶扁舟
*@version 1.0
*@创建时间： 2014-9-21   下午10:19:41
*/
//表示先启动Spring容器，把junit运行在Spring容器中

public class PropertyMapperTest extends TestCase {

	/**
	 * 执行每一个测试方法的时候，先执行setUp()；
	 */
	protected void setUp() throws Exception {
		
		ClassPathXmlApplicationContext    applicationContext =new ClassPathXmlApplicationContext(
				"/spring3/spring3-beans.xml",
				"/spring3/spring3-datasource.xml",
				"/spring3/spring3-mybatis.xml"
		
				);
		
		
		
		sqlSession=(SqlSessionTemplate) applicationContext.getBean("sqlSession");
		
		

		super.setUp();
	}

	/**
	 * 执行每一个测试方法之后，执行tearDown();
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
    private SqlSessionTemplate sqlSession;


   @Test
   public void testQueryPropertyByPropertyId() throws Exception {

	
		PropertyMapper propertyMapper=sqlSession.getMapper(PropertyMapper.class);
		String propertyId="135673939";	
		Property property=propertyMapper.queryPropertyByPropertyId(propertyId);
		   System.out.println(JSON.toJSONString(property, true));
   }

   @Test
   public void testQueryPropertyByPropertyName() throws Exception {
		PropertyMapper propertyMapper=sqlSession.getMapper(PropertyMapper.class);
		String keywords="河滨";	
		int n=10;	
		List<Property> properties=propertyMapper.queryPropertyByPropertyName(keywords,n);
       
        System.out.println(JSON.toJSONString(properties, true));
   }
	
	public static void main(String[] args) {
		
		
		TestSuite suite = new TestSuite("Sample Tests");
		/* 逐一添加testCase类 */
		suite.addTestSuite(PropertyMapperTest.class);

		junit.textui.TestRunner.run(suite);

	}

}

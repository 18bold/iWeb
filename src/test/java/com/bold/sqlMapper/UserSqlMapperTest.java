package com.bold.sqlMapper;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.bold.entity.User;

public class UserSqlMapperTest {

	@Test
	public void test() throws Exception {
		// 加载mybatis 的配置文件
		// 创建sqlSession 的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(Resources.getResourceAsReader("mybatis-config.xml"));
		// 创建能够执行SQL映射文件中sql语句的sqlSession对象
		SqlSession session = sessionFactory.openSession();
		// 指定sql语句对应的标识字符串：namespace+id
		String statement = "com.bold.dao.IUserDao.selectAll";
		// 执行查询，返回一个list
		List<User> users = session.selectList(statement);
		System.out.println(users);
		session.close();
	}

}

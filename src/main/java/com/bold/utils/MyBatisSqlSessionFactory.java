package com.bold.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSqlSessionFactory {
	private static SqlSessionFactory sessionFactory;
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sessionFactory == null) {
			try {
				// 加载mybatis 的配置文件
				// 创建sqlSession 的工厂
				sessionFactory = new SqlSessionFactoryBuilder()
						.build(Resources.getResourceAsReader("mybatis-config.xml"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
		
	}
	public static SqlSession openSession() {
		return getSqlSessionFactory().openSession();
	}
}

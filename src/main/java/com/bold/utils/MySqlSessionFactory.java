package com.bold.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public enum MySqlSessionFactory {
	me;
	private SqlSessionFactory sessionFactory;
	MySqlSessionFactory() {
		try {
			// 加载mybatis 的配置文件
			// 创建sqlSession 的工厂
			sessionFactory = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsReader("mybatis-config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SqlSession openSession() {
		return sessionFactory.openSession();
	}
}

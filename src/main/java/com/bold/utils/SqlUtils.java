package com.bold.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public enum SqlUtils {
	me;
	public SqlSession getMyBatis() {
		try {
			// 加载mybatis 的配置文件
			// 创建sqlSession 的工厂
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsReader("mybatis-config.xml"));
			// 创建能够执行SQL映射文件中sql语句的sqlSession对象
			return sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

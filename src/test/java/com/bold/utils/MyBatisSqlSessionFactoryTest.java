package com.bold.utils;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyBatisSqlSessionFactoryTest {

	@Test
	public void test() {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		System.out.println("success");
	}

}

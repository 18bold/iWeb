package com.bold.utils;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MySqlSessionFactoryTest {

	@Test
	public void test() {
		SqlSession session = MySqlSessionFactory.me.openSession();
		System.out.println("success");
	}

}

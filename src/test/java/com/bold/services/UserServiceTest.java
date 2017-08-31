package com.bold.services;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bold.entity.User;
import com.bold.utils.NameUtil;

public class UserServiceTest {
	AbstractApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	UserService userService;
	@Test
	public void testSelectAll() {
		userService = (UserService) ac.getBean("userService");
		RowBounds rowBounds = new RowBounds(0,25);
		List<User> users = userService.selectAll(rowBounds);
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
	@Test
	public void testSelectById() {
		userService = (UserService) ac.getBean("userService");
		User user = userService.selectById(1);
		System.out.println(user.toString());
	}
	@Test 
	public void testInsert() {
		User user = new User();
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			user.setAge(random.nextInt(50));
			user.setName(NameUtil.getName());
			user.setSalary((double) random.nextInt(5000));
			userService.insert(user);
		}
	}
	
	@Test
	public void testSelectCount() {
		userService = (UserService) ac.getBean("userService");
		System.out.println(userService.selectCount());
	}
}

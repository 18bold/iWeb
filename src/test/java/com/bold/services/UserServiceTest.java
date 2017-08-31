package com.bold.services;

import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;

import com.bold.entity.User;
import com.bold.utils.NameUtil;

public class UserServiceTest {
	UserService userService = new UserService();
	@Test
	public void testSelectAll() {
		RowBounds rowBounds = new RowBounds(0,25);
		List<User> users = userService.selectAll(rowBounds);
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
	@Test
	public void testSelectById() {
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
		System.out.println(userService.selectCount());
	}
}

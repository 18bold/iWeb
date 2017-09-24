package com.bold.service;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bold.entity.User;
import com.bold.utils.NameUtil;


@ContextConfiguration({
		"classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	@Resource(name="userService")
	UserService userService;
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
		for (int i = 0; i < 1; i++) {
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

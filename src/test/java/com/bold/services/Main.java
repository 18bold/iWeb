package com.bold.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bold.entity.User;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		UserService userService = (UserService) ac.getBean("userService");
		/*RowBounds rowBounds = new RowBounds(0,25);
		List<User> users = userService.selectAll(rowBounds);
		for (User user : users) {
			System.out.println(user.toString());
		}*/
		User user = userService.selectById(1);
		System.out.println(user.toString());
		
		
		ac.close();
		
	}

}

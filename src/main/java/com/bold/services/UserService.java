package com.bold.services;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bold.entity.User;
import com.bold.repository.UserMapper;
import com.bold.utils.MyBatisSqlSessionFactory;

@Component("userService")
public class UserService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="userMapper")
	UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public List<User> selectAll(RowBounds rowBounds) {
		return userMapper.selectAll(rowBounds);
	}
	
	public User selectById(Integer id) {
		logger.debug("select User By id:{}",id);
		return userMapper.selectById(id);
	}
	
	public void insert(User user) {
		logger.debug("insert User");
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insert(user);
			sqlSession.commit();
		}
		catch (Exception e) {
		}
	}
	public int selectCount() {
		logger.debug("select count");
		return userMapper.selectCount();
	}
	public List<User> selectByWhere(RowBounds rowBounds,HashMap<String, Object> map) {
		logger.debug("selectByWhere");
		return userMapper.selectByWhere(rowBounds,map);
	}
	public int selectByWhereCount(HashMap<String, Object> hashMap) {
		logger.debug("selectByWhereCount");
		return userMapper.selectByWhereCount(hashMap);
	}
}

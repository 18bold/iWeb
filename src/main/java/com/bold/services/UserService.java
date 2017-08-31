package com.bold.services;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bold.entity.User;
import com.bold.mappers.UserMapper;
import com.bold.utils.MyBatisSqlSessionFactory;

public class UserService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<User> selectAll(RowBounds rowBounds) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.selectAll(rowBounds);
		}
		catch (Exception e) {
		}
		return null;
	}
	
	public User selectById(Integer id) {
		logger.debug("select User By id:{}",id);
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.selectById(id);
		}
		catch (Exception e) {
		}
		return null;
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
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.selectCount();
		}
		catch (Exception e) {
		}
		return 0;
	}
	public List<User> selectByWhere(RowBounds rowBounds,HashMap<String, Object> map) {
		logger.debug("selectByWhere");
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.selectByWhere(rowBounds,map);
		}
		catch (Exception e) {
		}
		return null;
	}
	public int selectByWhereCount(HashMap<String, Object> hashMap) {
		logger.debug("selectByWhereCount");
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();){
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.selectByWhereCount(hashMap);
		}
		catch (Exception e) {
		}
		return 0;
	}
}

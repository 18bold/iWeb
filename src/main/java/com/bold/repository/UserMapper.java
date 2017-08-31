package com.bold.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.bold.entity.User;

public interface UserMapper {
	List<User> selectAll(RowBounds rowBounds); 
	int selectCount();
	User selectById(Integer id);
	void insert(User user);
	int selectByWhereCount(HashMap<String, Object> hashMap);
	List<User> selectByWhere(RowBounds rowBounds,HashMap<String, Object> hashMap);
}

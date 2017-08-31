package com.bold.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSON;
import com.bold.entity.User;
import com.bold.services.UserService;
import com.bold.utils.RequestUtil;

/**
 * Servlet implementation class getUserList
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num1 = RequestUtil.getInt(request, "min", 0);
		int num2 = RequestUtil.getInt(request, "max", 0);
		int roleId = RequestUtil.getInt(request, "roleId", -1);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num1", num1);
		map.put("num2", num2);
		map.put("roleId", roleId);
		int page = RequestUtil.getInt(request, "page", 1);
		int limit = RequestUtil.getInt(request, "limit", 20);
		int offset = (page-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		UserService userService = new UserService();
//		List<User> userList2 = userService.selectAll(rowBounds);
		List<User> userList2 = userService.selectByWhere(rowBounds,map);
		for (User user : userList2) { 
			System.out.println(user.toString());
		}
		response.setContentType("application/json;charset=utf-8");
	    Map<String, Object> jsonMap = new HashMap<>();
	    jsonMap.put("code", 0);
	    jsonMap.put("msg", "");
	    jsonMap.put("count",userService.selectByWhereCount(map));
	    jsonMap.put("data", userList2);
	    // JSON.toJSONString:将对象序列化为JSON字符串
	    // JSON.parse(String):将JSON字符串反序列化为Java对象
	    String json = JSON.toJSONString(jsonMap);
	    response.getWriter().write(json);
	    response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

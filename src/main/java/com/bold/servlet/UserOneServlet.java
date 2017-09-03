package com.bold.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bold.entity.User;
import com.bold.services.UserService;

/**
 * Servlet implementation class getUserByIDServlet
 */
@WebServlet("/getUserByIdServlet")
public class UserOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserOneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserService userService = new UserService();
		User user = userService.selectById(Integer.valueOf(id));
		List<User> userList2 = new ArrayList<User>();
		userList2.add(user);
		response.setContentType("application/json;charset=utf-8");
	    Map<String, Object> jsonMap = new HashMap<String, Object>();
	    jsonMap.put("code", 0);
	    jsonMap.put("msg", "");
	    jsonMap.put("count",userList2.size());
	    jsonMap.put("data", userList2);
	    // JSON.toJSONString:将对象序列化为JSON字符串
	    // JSON.parse(String):将JSON字符串反序列化为Java对象
	    String json = JSON.toJSONString(jsonMap);
	    response.getWriter().write(json);
	    response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

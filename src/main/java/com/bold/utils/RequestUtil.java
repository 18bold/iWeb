package com.bold.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static Integer getInt(HttpServletRequest request, String param, int def) {
		if (request.getParameter(param)==null || request.getParameter(param)=="") {
			return def;
		} else {
			return Integer.valueOf(request.getParameter(param));
		}
	}
}

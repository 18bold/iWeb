package com.bold.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static Integer getInt(Integer param, int def) {
		if (param == null ) {
			return def;
		} else {
			return Integer.valueOf(param);
		}
	}
}

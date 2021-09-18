package com.jsp_servlet.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

	// Singleton pattern

	public static SessionUtil sessionUtil = null; // ==>sessionUtil (uniqueInstance)

	public static SessionUtil getInstance() {
		if (sessionUtil == null) {
			sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}

	// Duy trì thông tin người dùng trong hệ thống
	public void pushValue(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}

	// Thoát ra khỏi hệ thống
	public void removeValue(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

//	Lấy thông tin người dùng
	public Object getValue(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
}

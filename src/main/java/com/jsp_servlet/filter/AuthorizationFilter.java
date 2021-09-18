package com.jsp_servlet.filter;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.constant.SystemConstant;
import com.jsp_servlet.model.UserModel;
import com.jsp_servlet.utils.SessionUtil;

public class AuthorizationFilter implements Filter {

	@SuppressWarnings("unused")
	private ServletContext servletContext;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.servletContext = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		String url = httpServletRequest.getServletPath();
		if (url.startsWith("/admin")) {
			UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(httpServletRequest, "USERMODEL");
			if (userModel != null) {
				if (userModel.getRole().getCode().equals(SystemConstant.ADMIN)) {
					chain.doFilter(servletRequest, servletResponse);
				} else if (userModel.getRole().getCode().equals(SystemConstant.USER)) {
					servletRequest.setAttribute("message", resourceBundle.getString("not_permission"));
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath()
							+ "/login?action=login&message=not_permission&alert=danger");
				}
			} else {

				servletRequest.setAttribute("message", resourceBundle.getString("not_login"));
				httpServletResponse.sendRedirect(
						httpServletRequest.getContextPath() + "/login?action=login&message=not_login&alert=danger");

			}
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

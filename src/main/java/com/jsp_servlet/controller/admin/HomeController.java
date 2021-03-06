package com.jsp_servlet.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.service.INewService;
import com.jsp_servlet.service.implement.NewService;

@WebServlet(urlPatterns = { "/admin-home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private INewService newService;

	public HomeController() {
		newService = new NewService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/home.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

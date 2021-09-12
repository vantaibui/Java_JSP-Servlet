package com.jsp_servlet.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.service.ICategoryService;
import com.jsp_servlet.service.INewService;
import com.jsp_servlet.service.implement.CategoryService;
import com.jsp_servlet.service.implement.NewService;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ICategoryService categoryService;

	private INewService newService;

	public HomeController() {
		categoryService = new CategoryService();
		newService = new NewService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long cateogryId = 1L;

		req.setAttribute("categories", categoryService.findAll());

		req.setAttribute("news", newService.findByCategoryId(cateogryId));

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");

		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}

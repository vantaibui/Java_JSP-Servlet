package com.jsp_servlet.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.model.UserModel;
import com.jsp_servlet.service.ICategoryService;
import com.jsp_servlet.service.INewService;
import com.jsp_servlet.service.IUserService;
import com.jsp_servlet.service.implement.CategoryService;
import com.jsp_servlet.service.implement.NewService;
import com.jsp_servlet.service.implement.UserService;
import com.jsp_servlet.utils.FormUitl;
import com.jsp_servlet.utils.SessionUtil;

@WebServlet(urlPatterns = { "/home", "/login", "/logout" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private ICategoryService categoryService;

	@SuppressWarnings("unused")
	private INewService newService;

	@SuppressWarnings("unused")
	private IUserService userService;

	public HomeController() {
		categoryService = new CategoryService();
		newService = new NewService();
		userService = new UserService();
	}

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = null;
		String action = req.getParameter("action");

		if (action != null && action.equals("login")) {

			String message = req.getParameter("message");
			String alert = req.getParameter("alert");

			if (message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString("username_password_invalid"));
				req.setAttribute("alert", alert);
			}

			requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
			requestDispatcher.forward(req, resp);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
//			req.setAttribute("categories", categoryService.findAll());

			requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
			requestDispatcher.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if (action != null && action.equals("login")) {
			UserModel userModel = FormUitl.toModel(UserModel.class, req);

			userModel = userService.findByUserNameAndPasswordAndStatus(userModel.getUserName(), userModel.getPassword(),
					1);

			if (userModel != null) {
				// getInstance kiểm tra đối tượng tồn tại chưa
				SessionUtil.getInstance().pushValue(req, "USERMODEL", userModel);

				if (userModel.getRole().getCode().equals("USER")) {
					resp.sendRedirect(req.getContextPath() + "/home");
				} else if (userModel.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				}
			} else {
				resp.sendRedirect(
						req.getContextPath() + "/login?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}

}

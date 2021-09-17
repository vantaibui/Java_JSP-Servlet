package com.jsp_servlet.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.constant.SystemConstant;
import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.service.INewService;
import com.jsp_servlet.service.implement.NewService;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private INewService newService;

	public NewController() {
		newService = new NewService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel newModel = new NewModel();

		String pageStr = req.getParameter("page");
		String maxPageItemStr = req.getParameter("maxPageItem");
		if (pageStr != null) {
			newModel.setPage(Integer.parseInt(pageStr));
		} else {
			newModel.setPage(1);
		}
		if (maxPageItemStr != null) {
			newModel.setMaxPageItem(Integer.parseInt(maxPageItemStr));
		}

		Integer offset = (newModel.getPage() - 1) * newModel.getMaxPageItem();
		 newModel.setListResult(newService.findAll(offset, newModel.getMaxPageItem()));
		newModel.setTotalItem(newService.getTotalItem());
		
		// newModel.setTotalItem(newService().findAll().size())
		// newModel.setTotalItem(newModel.getListResult().size())
		
		newModel.setTotalPage((int) Math.ceil((double)newModel.getTotalItem() / newModel.getMaxPageItem()));
		req.setAttribute(SystemConstant.MODEL, newModel);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/new/list.jsp");
		requestDispatcher.forward(req, resp);
	}

}

package com.jsp_servlet.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp_servlet.constant.SystemConstant;
import com.jsp_servlet.dao.ICategoryDAO;
import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.paging.PageRequest;
import com.jsp_servlet.paging.Pageble;
import com.jsp_servlet.service.ICategoryService;
import com.jsp_servlet.service.INewService;
import com.jsp_servlet.service.implement.CategoryService;
import com.jsp_servlet.service.implement.NewService;
import com.jsp_servlet.sort.Sorter;
import com.jsp_servlet.utils.FormUitl;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private INewService newService;
	
	private ICategoryService categoryService;

	public NewController() {
		newService = new NewService();
		categoryService = new CategoryService();
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel newModel = FormUitl.toModel(NewModel.class, req);
		String view = "";
		
		if (newModel.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(newModel.getPage(), newModel.getMaxPageItem(),
					new Sorter(newModel.getSortName(), newModel.getSortBy()));
			newModel.setListResult(newService.findAll(pageble));

			newModel.setTotalItem(newService.getTotalItem());
			newModel.setTotalPage((int) Math.ceil((double) newModel.getTotalItem() / newModel.getMaxPageItem()));
			req.setAttribute(SystemConstant.MODEL, newModel);

			view = "/views/admin/new/list.jsp";
//			RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
//			requestDispatcher.forward(req, resp);
		} else if (newModel.getType().equals(SystemConstant.EDIT)) {
			if (newModel.getId() != null) {
				
				newModel = newService.findOne(newModel.getId());
			}else {
				
			}
			req.setAttribute("categories", categoryService.findAll());
			view = "/views/admin/new/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, newModel);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
		requestDispatcher.forward(req, resp);
	}

}

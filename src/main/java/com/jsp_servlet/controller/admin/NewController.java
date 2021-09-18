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
import com.jsp_servlet.paging.PageRequest;
import com.jsp_servlet.paging.Pageble;
import com.jsp_servlet.service.INewService;
import com.jsp_servlet.service.implement.NewService;
import com.jsp_servlet.sort.Sorter;
import com.jsp_servlet.utils.FormUitl;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private INewService newService;

	public NewController() {
		newService = new NewService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel newModel = FormUitl.toModel(NewModel.class, req);

//		Integer offset = (newModel.getPage() - 1) * newModel.getMaxPageItem();

		Pageble pageble = new PageRequest(newModel.getPage(), newModel.getMaxPageItem(),
				new Sorter(newModel.getSortName(), newModel.getSortBy()));

//		newModel.setListResult(
//				newService.findAll(offset, newModel.getMaxPageItem(), newModel.getSortName(), newModel.getSortBy()));
		
		newModel.setListResult(newService.findAll(pageble));
		
		newModel.setTotalItem(newService.getTotalItem());
		newModel.setTotalPage((int) Math.ceil((double) newModel.getTotalItem() / newModel.getMaxPageItem()));
		req.setAttribute(SystemConstant.MODEL, newModel);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/new/list.jsp");
		requestDispatcher.forward(req, resp);
	}

}

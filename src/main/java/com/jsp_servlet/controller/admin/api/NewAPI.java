package com.jsp_servlet.controller.admin.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.service.INewService;
import com.jsp_servlet.service.implement.NewService;
import com.jsp_servlet.utils.HttpUtils;

@WebServlet(urlPatterns = { "/api-admin-new" })
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = -2631877929939270998L;

	private INewService newService;

	public NewAPI() {
		newService = new NewService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		NewModel newModel = HttpUtils.of(req.getReader()).toModel(NewModel.class);
		newModel = newService.save(newModel);
		System.out.println(newModel);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(resp.getOutputStream(), newModel);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();

		NewModel updateNew = HttpUtils.of(req.getReader()).toModel(NewModel.class);
		updateNew = newService.update(updateNew);
		objectMapper.writeValue(resp.getOutputStream(), updateNew);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();

		NewModel newModel = HttpUtils.of(req.getReader()).toModel(NewModel.class);
		newService.delete(newModel.getIds());
		objectMapper.writeValue(resp.getOutputStream(), newModel);
	}

}

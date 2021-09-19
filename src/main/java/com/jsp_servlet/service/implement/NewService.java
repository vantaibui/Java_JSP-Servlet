package com.jsp_servlet.service.implement;

import java.sql.Timestamp;
import java.util.List;

import com.jsp_servlet.dao.ICategoryDAO;
import com.jsp_servlet.dao.INewDAO;
import com.jsp_servlet.dao.implement.CategoryDAO;
import com.jsp_servlet.dao.implement.NewDAO;
import com.jsp_servlet.model.CategoryModel;
import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.paging.Pageble;
import com.jsp_servlet.service.INewService;

public class NewService implements INewService {

	private INewDAO newDAO;

	private ICategoryDAO categoryDAO;

	public NewService() {
		newDAO = new NewDAO();
		categoryDAO = new CategoryDAO();
	}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
		Long newId = newDAO.save(newModel);
		System.out.println(newId);
		return newDAO.findOne(newId);
	}

	@Override
	public NewModel update(NewModel updateNew) {
		NewModel oldNew = newDAO.findOne(updateNew.getId());
		updateNew.setCreateBy(oldNew.getCreateBy());
		updateNew.setCreateDate(oldNew.getCreateDate());
		updateNew.setModifiedBy("");
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		newDAO.update(updateNew);

		return newDAO.findOne(updateNew.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			newDAO.delete(id);
		}
	}

	/*
	 * @Override public List<NewModel> findAll(Integer offset, Integer limit, String
	 * sortName, String sortBy) { return newDAO.findAll(offset, limit, sortName,
	 * sortBy); }
	 * 
	 */

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewModel findOne(Long id) {
		NewModel newModel = newDAO.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());

		return newModel;
	}

}

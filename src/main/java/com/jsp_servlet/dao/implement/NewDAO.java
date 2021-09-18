package com.jsp_servlet.dao.implement;

import java.util.List;

import com.jsp_servlet.dao.INewDAO;
import com.jsp_servlet.mapper.implement.NewMapper;
import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		// init parameter
		String sql = "SELECT * FROM news WHERE categoryid = ?";

		return query(sql, new NewMapper(), categoryId);

	}

	@Override
	public Long save(NewModel model) {
		String sql = "INSERT INTO news (title, content, categoryid) VALUES (?, ?, ?)";

		return insert(sql, model.getTitle(), model.getContent(), model.getCategoryId());
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);

		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE news SET ");
		sql.append("title = ?, thumbnail = ?, shortdescription= ?, content= ?, categoryid= ?");
		sql.append(", createby= ?, createdate=?");
		sql.append(" WHERE id = ?");
		sql.append(";");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThmbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreateBy(), updateNew.getCreateDate(),
				updateNew.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM news WHERE id = ?";

		// Kiểm tra trong comment có newid đó không nếu có thì delete comment (foreign
		// key newid) rồi mới xóa news
		update(sql, id);

	}

	/*
	 * @Override public List<NewModel> findAll(Integer offset, Integer limit, String
	 * sortName, String sortBy) { StringBuilder sql = new StringBuilder();
	 * sql.append("SELECT * FROM news"); if (sortName != null && sortBy != null) {
	 * sql.append(" ORDER BY" + " " + sortName + " " + sortBy + " "); } if (offset
	 * != null && limit != null) { sql.append("LIMIT" + " " + offset + "," + limit);
	 * } return query(sql.toString(), new NewMapper()); }
	 */

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM news");
		if (pageble.getSorter().getSortName() != null && pageble.getSorter().getSortBy() != null) {
			sql.append(" ORDER BY" + " " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy()
					+ " ");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append("LIMIT" + " " + pageble.getOffset() + "," + pageble.getLimit());
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}

}

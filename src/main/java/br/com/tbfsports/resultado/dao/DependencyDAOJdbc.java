package br.com.tbfsports.resultado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tbfsports.resultado.bean.DependencyBean;
import br.com.tbfsports.resultado.constant.Constants;
import br.com.tbfsports.resultado.dao.sql.CrudQueries;
import br.com.tbfsports.resultado.dto.DependencyDto;
import lombok.Setter;

@Setter
@Repository
public class DependencyDAOJdbc implements ICrudDAO<DependencyDto, DependencyBean> {
	
	private String table;
	
	@Override
	public long insert(Connection conn, DependencyBean bean) throws Exception {
		CrudQueries queries = new CrudQueries(table);
		int id = 0;
		
		String query = table == Constants.TEAM_TAB_NAME?
			getTabNameQuery(queries.getInsert()).replaceFirst("\\?,", "") : queries.getInsert();
		
		PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		ps = setCommonsParamsPreparedStatement(ps, bean);
		ps.setObject(isTeanTab()? 3 : 1, bean.getDthrCreate());
		ps.setObject(isTeanTab()? 4 : 3, bean.getDthrUpdate());
		ps.execute();

		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
		}
		rs.close();
		ps.close();

		return id;
	}

	@Override
	public DependencyDto findById(Connection conn, long id) throws Exception {
		CrudQueries queries = new CrudQueries(table);
		DependencyDto resultadoDto = new DependencyDto();
		PreparedStatement ps = conn.prepareStatement(getTabNameQuery( queries.getSelectById()));
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			resultadoDto = getDtoFromResultSet(rs);
		}
		rs.close();
		ps.close();
		return resultadoDto;
	}

	public DependencyDto findByName(Connection conn, String name) throws Exception {
		CrudQueries queries = new CrudQueries(table);
		DependencyDto resultadoDto = new DependencyDto();
		
		PreparedStatement ps = conn.prepareStatement(getTabNameQuery(queries.getSelectByName()));
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			resultadoDto = getDtoFromResultSet(rs);
		}
		rs.close();
		ps.close();
		return resultadoDto;
	}
	
	@Override
	public List<DependencyDto> findAll(Connection conn) throws Exception {
		CrudQueries queries = new CrudQueries(table);
		List<DependencyDto> lstDependencyDto = new ArrayList<DependencyDto>();
		PreparedStatement ps = conn.prepareStatement(getTabNameQuery(queries.getSelectAll()));
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			lstDependencyDto.add(getDtoFromResultSet(rs));
		}
		rs.close();
		ps.close();
		return lstDependencyDto;
	}

	@Override
	public int update(Connection conn, DependencyBean bean) throws Exception {
		CrudQueries queries = new CrudQueries(table);
		PreparedStatement ps = conn.prepareStatement(queries.getUpdate());
		
		ps = setCommonsParamsPreparedStatement(ps, bean);
		ps.setObject(isTeanTab()? 2 : 3, bean.getDthrUpdate());
		ps.setLong(isTeanTab()? 3 : 4, bean.getId());

		int affectedRows = ps.executeUpdate();
		ps.close();
		return affectedRows;
	}

	@Override
	public int delete(Connection conn, int id) throws Exception {
		CrudQueries queries = new CrudQueries(table);
		PreparedStatement ps = conn.prepareStatement(queries.getDelete());
		ps.setInt(1, id);
		int affectedRows = ps.executeUpdate();
		ps.close();

		return affectedRows;
	}
	
	private DependencyDto getDtoFromResultSet(ResultSet rs) throws Exception {
		DependencyDto dto = new DependencyDto();
		dto.setId(rs.getLong("id"));
		dto.setName(rs.getString("name"));
		if (isTeanTab()) {			
			dto.setDescription(rs.getString("description"));
		}
		return dto;
	}
	
	private PreparedStatement setCommonsParamsPreparedStatement(
		PreparedStatement ps,DependencyBean bean
	) throws Exception {
		ps.setString(1, bean.getName());
		if (isTeanTab()) {			
			ps.setString(2, bean.getDescription());
		}
		return ps;
	}
	
	private boolean isTeanTab() {
		return table != Constants.TEAM_TAB_NAME;
	}
	
	private String getTabNameQuery(String query) {
		return table == Constants.TEAM_TAB_NAME? query.replace(",description", "") : query;
	}
}
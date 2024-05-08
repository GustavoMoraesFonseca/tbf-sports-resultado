package br.com.tbfsports.resultado.dao.sql;

import java.util.List;

import br.com.tbfsports.resultado.constant.Constants;
import br.com.tbfsports.resultado.exception.BadRequestException;

public class CrudQueries {
	
	private String table;
	
	public CrudQueries(String table) throws Exception {
		var validValues = List.of(
			Constants.TEAM_TAB_NAME, Constants.CATEGOTY_TAB_NAME, Constants.AGE_TAB_NAME
		);
		if (!validValues.contains(table)) {
			throw new BadRequestException("Tabela informada está inválida.");
		}
		this.table = table;					
	}
	
	public String getInsert() {		
		return "INSERT INTO "+ table
			 + "(name,description,dthr_create,dthr_update)"
			 + "VALUES"
			 + "(?,?,?,?);";
	}
	
	public String getSelectAll() {		
		return "SELECT id, name,description "
			 + "FROM "+ table;
	}
	
	public String getSelectById() {		
		return getSelectAll() + " WHERE id = ?";
	}
	
	public String getSelectByName() {		
		return getSelectAll() + " WHERE name = ?";
	}
	
	public String getUpdate() {		
		return "UPDATE " + table + " SET"
			 + "name = ?,"
			 + "description = ?,"
			 + "dthr_update = ? "
			 + "WHERE id = ?;";
	}
	
	public String getDelete() {		
		return "DELETE FROM " + table
			 + " WHERE id = ?;";
	}
}
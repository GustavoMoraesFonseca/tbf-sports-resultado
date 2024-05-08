package br.com.tbfsports.resultado.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.tbfsports.resultado.bean.DependencyBean;
import br.com.tbfsports.resultado.config.MySQLConfig;
import br.com.tbfsports.resultado.dao.DependencyDAOJdbc;
import br.com.tbfsports.resultado.dto.DependencyDto;

@Repository
public class DependencyCommand {

	@Autowired
	DependencyDAOJdbc dependencyDAOImpl;
	
	public long create(DependencyBean bean, String tableName) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		long id = 0;
		try {
			setTableName(tableName);
			id = dependencyDAOImpl.insert(conn, bean);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return id;
	}
	
	public DependencyDto findByName(String name, String tableName) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		DependencyDto dto;
		try {
			setTableName(tableName);
			dto = dependencyDAOImpl.findByName(conn, name);
		} finally {
			conn.close();
		}
		return dto;
	}
	
	public DependencyDto findById(long id, String tableName) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		DependencyDto dto;
		try {
			setTableName(tableName);
			dto = dependencyDAOImpl.findById(conn, id);
		} finally {
			conn.close();
		}
		return dto;
	}	
	
	public List<DependencyDto> findAll(String tableName) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		List<DependencyDto> lst = new ArrayList<DependencyDto>();
		try {
			setTableName(tableName);
			lst = dependencyDAOImpl.findAll(conn);
		} finally {
			conn.close();
		}
		return lst;
	}
	
	public int update(DependencyBean bean, String tableName) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		int affectedRows = 0;
		try {
			setTableName(tableName);
			affectedRows = dependencyDAOImpl.update(conn, bean);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return affectedRows;
	}
	
	public int delete(int id, String tableName) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		int affectedRows = 0;
		try {
			setTableName(tableName);
			affectedRows = dependencyDAOImpl.delete(conn, id);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return affectedRows;
	}
	
	private void setTableName(String table) {
		this.dependencyDAOImpl.setTable(table);
	}
}
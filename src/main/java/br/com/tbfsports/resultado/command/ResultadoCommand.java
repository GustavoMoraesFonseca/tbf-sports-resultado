package br.com.tbfsports.resultado.command;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.tbfsports.resultado.bean.ResultadoBean;
import br.com.tbfsports.resultado.config.MySQLConfig;
import br.com.tbfsports.resultado.dao.ResultadosDAOJdbc;
import br.com.tbfsports.resultado.dto.ResultadoDto;

@Repository
public class ResultadoCommand {

	@Autowired
	ResultadosDAOJdbc resultadosDAOImpl;
	
	public long create(ResultadoBean bean) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		long id = 0;
		try {
			id = resultadosDAOImpl.insert(conn, bean);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return id;
	}
	
	public ResultadoDto findById(long id) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		ResultadoDto dto;
		try {
			dto = resultadosDAOImpl.findById(conn, id);
		} finally {
			conn.close();
		}
		return dto;
	}	
	
	public List<ResultadoDto> findAll() throws Exception {
		Connection conn = MySQLConfig.getConnection();
		List<ResultadoDto> lst = new ArrayList<ResultadoDto>();
		try {
			lst = resultadosDAOImpl.findAll(conn);
		} finally {
			conn.close();
		}
		return lst;
	}
	
	public int update(ResultadoBean bean) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		int affectedRows = 0;
		try {
			affectedRows = resultadosDAOImpl.update(conn, bean);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return affectedRows;
	}
	
	public int delete(int id) throws Exception {
		Connection conn = MySQLConfig.getConnection();
		int affectedRows = 0;
		try {
			affectedRows = resultadosDAOImpl.delete(conn, id);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return affectedRows;
	}
}
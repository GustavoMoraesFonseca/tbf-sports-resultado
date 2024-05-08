package br.com.tbfsports.resultado.dao;

import java.sql.Connection;
import java.util.List;

public interface ICrudDAO<Dto, Bean> {
	
	long insert(Connection conn, Bean bean) throws Exception;
	Dto findById(Connection conn, long id) throws Exception;
	List<Dto> findAll(Connection conn) throws Exception;
	int update(Connection conn, Bean bean) throws Exception;
	int delete(Connection conn, int id) throws Exception;
}
package br.com.tbfsports.resultado.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySQLConfig {

	public static Connection getConnection() throws Exception {
		Properties props = new Properties();
		Connection conn = null;
		try {
			props.put("user", "tbf-admin");
			props.put("password", "TBF@sports2023");
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/tbf-sports?allowPublicKeyRetrieval=true&useTimezone=true&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull",
				props
			);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw e;
		}
		return conn;
	}
}
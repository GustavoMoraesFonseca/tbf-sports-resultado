package br.com.tbfsports.resultado.commons;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.tbfsports.resultado.constant.Constants;
import br.com.tbfsports.resultado.exception.NotFoundExeption;

public abstract class CommonUtils {

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Timestamp(System.currentTimeMillis()));
	}
	
	public static void notFoundChecker(long param) throws NotFoundExeption {
		if (param == 0)
			throw new NotFoundExeption(Constants.NOT_FOUND_MSG);
	}
}
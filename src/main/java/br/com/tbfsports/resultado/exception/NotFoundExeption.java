package br.com.tbfsports.resultado.exception;

public class NotFoundExeption extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NotFoundExeption(String msg) {
		super(msg);
	}
}
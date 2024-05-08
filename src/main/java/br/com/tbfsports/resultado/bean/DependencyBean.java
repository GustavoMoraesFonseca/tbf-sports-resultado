package br.com.tbfsports.resultado.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class DependencyBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String Description;
	private String dthrCreate;
	private String dthrUpdate;
}
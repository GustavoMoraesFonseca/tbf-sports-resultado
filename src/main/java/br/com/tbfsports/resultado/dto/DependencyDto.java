package br.com.tbfsports.resultado.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DependencyDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String Description;
}
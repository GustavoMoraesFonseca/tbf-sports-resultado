package br.com.tbfsports.resultado.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultadoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private int colocacao;
	private int numPeito;
	private String nome;
	private String genero;
	private int idade;
	private String faixaEtaria;
	private int classFaixaEtaria;
	private String tmpBruto;
	private String tmpLiquido;
	private String categoria;
	private String equipe;
	private String cpf;
	private long idAgeGroup;
	private long idCategory;
	private long idTeam;
}
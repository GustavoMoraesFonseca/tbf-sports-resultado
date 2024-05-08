package br.com.tbfsports.resultado.bean;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	private int colocacao;
	private int numPeito;
	private String nome;
	private char genero;
	private int idade;
	private int classFaixaEtaria;
	private String cpf;
	private String tmpBruto;
	private String tmpLiquido;
	private long idAgeGroup;
	private long idCategory;
	private long idTeam;
	private String dthrCreate;
	private String dthrUpdate;
}
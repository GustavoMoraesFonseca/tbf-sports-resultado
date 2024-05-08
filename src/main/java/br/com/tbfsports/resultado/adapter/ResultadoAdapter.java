package br.com.tbfsports.resultado.adapter;

import org.springframework.stereotype.Component;

import br.com.tbfsports.resultado.bean.ResultadoBean;
import br.com.tbfsports.resultado.commons.CommonUtils;
import br.com.tbfsports.resultado.dto.ResultadoDto;

@Component
public class ResultadoAdapter {

	public ResultadoDto adapterValuesToDto(String[] values) {
		ResultadoDto dto = new ResultadoDto();
		dto.setColocacao(Integer.parseInt(values[0]));
		dto.setNumPeito(Integer.parseInt(values[1]));
		dto.setNome(values[2]);
		dto.setGenero(values[3]);
		dto.setIdade(Integer.parseInt(values[4]));
		dto.setFaixaEtaria(values[5]);
		dto.setClassFaixaEtaria(values[6].equals("-") ? 0 : Integer.parseInt(values[6]));
		dto.setTmpBruto(values[7]);
		dto.setTmpLiquido(values[8]);

		if (values.length > 9) {
			dto.setCategoria(values[9].isBlank() ? "0" : values[9]);
			dto.setEquipe(values[10].isBlank() ? null : values[10]);
			dto.setCpf(values[values.length - 1].matches("\\d+") ? values[values.length - 1] : null);
		}
		return dto;
	}

	public ResultadoBean adapterDtoToBean(ResultadoDto dto) {
		return ResultadoBean.builder()
			.id(dto.getId())
			.classFaixaEtaria(dto.getClassFaixaEtaria())
			.colocacao(dto.getColocacao())
			.cpf(dto.getCpf())
			.genero(dto.getGenero().charAt(0))
			.idade(dto.getIdade())
			.nome(dto.getNome())
			.numPeito(dto.getNumPeito())
			.tmpBruto(dto.getTmpBruto())
			.tmpLiquido(dto.getTmpLiquido())
			.idAgeGroup(dto.getIdAgeGroup())
			.idCategory(dto.getIdCategory())
			.idTeam(dto.getIdTeam())
			.dthrCreate(CommonUtils.getDate())
			.dthrUpdate(CommonUtils.getDate())
		.build();
	}
}
package br.com.tbfsports.resultado.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.tbfsports.resultado.adapter.ResultadoAdapter;
import br.com.tbfsports.resultado.bean.ResultadoBean;
import br.com.tbfsports.resultado.command.ResultadoCommand;
import br.com.tbfsports.resultado.constant.Constants;
import br.com.tbfsports.resultado.dto.DependencyDto;
import br.com.tbfsports.resultado.dto.ResultadoDto;
import br.com.tbfsports.resultado.exception.NotFoundExeption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultadoBusiness {

	@Autowired
	ResultadoAdapter adapter;

	@Autowired
	ResultadoCommand command;

	@Autowired
	DependencyBusiness dependencyBusiness;

	private BufferedReader getBufferedReaderFromFile(MultipartFile result) throws Exception {
		
		String fileContent = new String(result.getBytes(), "UTF-8");
		
		System.out.println(fileContent);
		
		File resultFile = new File(System.getProperty("java.io.tmpdir") + "/result.tmp");
		result.transferTo(resultFile);
 
		FileReader fr = new FileReader(resultFile, StandardCharsets.UTF_8);
		return new BufferedReader(fr);
	}

	private Long getDependencyId(String name, String tabName) throws Exception {
		DependencyDto dependencyDto = new DependencyDto();
		long id = 0;
		try {
			if (name == null) {
				return id;
			}
			
			id = dependencyBusiness.findDependencyByName(name, tabName).getId();
		} catch (NotFoundExeption e) {
			dependencyDto.setName(name);
			id = dependencyBusiness.createDependency(dependencyDto, tabName);
		}
		return id;
	}
	
	public List<ResultadoDto> saveResult(MultipartFile result) throws Exception {
		BufferedReader br = getBufferedReaderFromFile(result);
		List<ResultadoDto> lst = new ArrayList<>();
		try {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				ResultadoDto dto = null;
				try {					
					dto = adapter.adapterValuesToDto(values);

					//Erro UTF-8
					if (dto.getNome().startsWith("CAMILA MUSUMECCI")) {
						System.out.println(dto.getNome());
					}
					
					dto.setIdTeam(getDependencyId(dto.getEquipe(), Constants.TEAM_TAB_NAME));
					dto.setIdAgeGroup(getDependencyId(dto.getFaixaEtaria(), Constants.AGE_TAB_NAME));
					dto.setIdCategory(getDependencyId(dto.getCategoria(), Constants.CATEGOTY_TAB_NAME));
					
					createResultado(dto);
					lst.add(dto);
				} catch (Exception e) {
					System.out.println(e.getMessage() + ": " +dto);
				}
			}
		} finally {
			br.close();
		}
		return lst;
	}
	
	public long createResultado(ResultadoDto resultadoDto) throws Exception {
		ResultadoBean resultadoBean;
		long id = 0;
		try {
			resultadoBean = adapter.adapterDtoToBean(resultadoDto);
			id = command.create(resultadoBean);
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return id;
	}

	public ResultadoDto findResultadoById(long id) throws Exception {
		ResultadoDto resultadoDto = new ResultadoDto();
		try {
			resultadoDto = command.findById(id);
			notFoundChecker(resultadoDto.getId());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return resultadoDto;
	}

	public List<ResultadoDto> findAllResultados() throws Exception {
		List<ResultadoDto> resultadosDtos = new ArrayList<ResultadoDto>();
		try {
			resultadosDtos = command.findAll();
			notFoundChecker(resultadosDtos.size());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return resultadosDtos;
	}

	public String updateResultado(ResultadoDto resultadoDto, int id) throws Exception {
		ResultadoBean resultadoBean;
		int affectedRows = 0;
		try {
			resultadoDto.setId(id);
			resultadoBean = adapter.adapterDtoToBean(resultadoDto);
			affectedRows = command.update(resultadoBean);
			notFoundChecker(affectedRows);
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return affectedRows + "";
	}

	public String deleteResultado(int id) throws Exception {
		int affectedRows = 0;
		try {
			affectedRows = command.delete(id);
			notFoundChecker(affectedRows);
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return affectedRows + "";
	}

	private static void notFoundChecker(long param) throws NotFoundExeption {
		if (param == 0)
			throw new NotFoundExeption(Constants.NOT_FOUND_MSG);
	}
	
	   public static void main(String args[]) throws Exception {
		      String s = "CAMILA MUSUMECCI GUIMAR�ES BRITO";
		      byte arr[] = s.getBytes("UTF8");
		      System.out.print(new String(arr));
		   }
}
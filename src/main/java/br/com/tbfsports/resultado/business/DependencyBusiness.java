package br.com.tbfsports.resultado.business;

import static br.com.tbfsports.resultado.commons.CommonUtils.notFoundChecker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tbfsports.resultado.adapter.DependencyAdapter;
import br.com.tbfsports.resultado.bean.DependencyBean;
import br.com.tbfsports.resultado.command.DependencyCommand;
import br.com.tbfsports.resultado.dto.DependencyDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DependencyBusiness {

	@Autowired
	DependencyAdapter adapter;
	
	@Autowired
	DependencyCommand command;
	
	public long createDependency(DependencyDto dependencyDto, String tableName) throws Exception {
		DependencyBean dependencyBean;
		long id = 0;
		try {
			dependencyBean = adapter.adapterDtoToBean(dependencyDto);
			id = command.create(dependencyBean, tableName);
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return id;
	}
	
	public DependencyDto findDependencyById(long id, String tableName) throws Exception {
		DependencyDto dependencyDto = new DependencyDto();
		try {
			dependencyDto = command.findById(id, tableName);
			notFoundChecker(dependencyDto.getId());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return dependencyDto;
	}

	public DependencyDto findDependencyByName(String name, String tableName) throws Exception {
		DependencyDto dependencyDto = new DependencyDto();
		try {
			dependencyDto = command.findByName(name, tableName);
			notFoundChecker(dependencyDto.getId());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return dependencyDto;
	}
	
	public List<DependencyDto> findAllDependencys(String tableName) throws Exception {
		List<DependencyDto> dependencysDtos = new ArrayList<DependencyDto>();
		try {
			dependencysDtos = command.findAll(tableName);
			notFoundChecker(dependencysDtos.size());
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return dependencysDtos;
	}
	
	public String updateDependency(DependencyDto dependencyDto, int id, String tableName) throws Exception {
		DependencyBean dependencyBean;
		int affectedRows = 0;
		try {
			dependencyDto.setId(id);
			dependencyBean = adapter.adapterDtoToBean(dependencyDto);
			affectedRows = command.update(dependencyBean, tableName);
			notFoundChecker(affectedRows);
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return affectedRows+"";
	}
	
	public String deleteDependency(int id, String tableName) throws Exception {
		int affectedRows = 0;
		try {
			affectedRows = command.delete(id, tableName);
			notFoundChecker(affectedRows);
		} catch (Exception e) {
			log.error("Error: " + e);
			throw e;
		}
		return affectedRows+"";
	}
}
package br.com.tbfsports.resultado.adapter;

import org.springframework.stereotype.Component;

import br.com.tbfsports.resultado.bean.DependencyBean;
import br.com.tbfsports.resultado.commons.CommonUtils;
import br.com.tbfsports.resultado.dto.DependencyDto;

@Component
public class DependencyAdapter {

	public DependencyBean adapterDtoToBean(DependencyDto dto) {
		DependencyBean bean = new DependencyBean();
		bean.setName(dto.getName());
		bean.setDescription(dto.getDescription());
		bean.setDthrCreate(CommonUtils.getDate());
		bean.setDthrUpdate(CommonUtils.getDate());
		return bean;
	}
}

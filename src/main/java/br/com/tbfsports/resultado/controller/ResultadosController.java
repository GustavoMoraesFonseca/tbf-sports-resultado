package br.com.tbfsports.resultado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.tbfsports.resultado.business.ResultadoBusiness;
import br.com.tbfsports.resultado.dto.ResponseDto;
import br.com.tbfsports.resultado.dto.ResultadoDto;

@RestController
@RequestMapping("/resultado/v1")
public class ResultadosController {

	@Autowired
	ResultadoBusiness business;
	
	@PostMapping("/populate")
	public ResponseEntity<List<ResultadoDto>> saveResults(
		@RequestParam(value = "file", required = true) MultipartFile result
	) throws Exception {
		var x = business.saveResult(result);
		return ResponseEntity.ok(x);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDto<Long>> create(@RequestBody ResultadoDto dto) throws Exception {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new ResponseDto<Long>(business.createResultado(dto), null));
	}

	@GetMapping
	public ResponseEntity<ResponseDto<List<ResultadoDto>>> findAll() throws Exception {
		return ResponseEntity
			.ok(new ResponseDto<List<ResultadoDto>>(business.findAllResultados(), null));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<ResultadoDto>> findById(@PathVariable("id") Long id) throws Exception {
		return ResponseEntity
			.ok(new ResponseDto<ResultadoDto>(business.findResultadoById(id), null));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDto<String>> update(@RequestBody ResultadoDto dto, @PathVariable("id") int id) throws Exception {		
		return ResponseEntity
			.ok(new ResponseDto<String>(business.updateResultado(dto, id), null));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDto<String>> delete(@PathVariable("id") int id) throws Exception {
		return ResponseEntity
			.ok(new ResponseDto<String>(business.deleteResultado(id), null));
	}
}
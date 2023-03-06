package com.dng.bank.app.controller.core;

import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public abstract class BaseEntityController<T extends BaseLongPrimaryKeyEntity, DTO> {
	
	protected final BaseService<T, DTO> baseEntityService;
	
	public BaseEntityController(BaseService<T, DTO> baseEntityService) {
		this.baseEntityService = baseEntityService;
	}
	
	@GetMapping
	public ResponseEntity<List<DTO>> getAll() {
		List<DTO> testDataDtoList = baseEntityService.findAll();
		return new ResponseEntity<>(testDataDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getDataByID(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(baseEntityService.findById(id), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(error(exception), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<DTO> create(@RequestBody DTO dto) {
		return new ResponseEntity<>(baseEntityService.save(dto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody DTO dto) {
		try {
			baseEntityService.save(dto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(error(exception), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		boolean deleted = baseEntityService.deleteById(id);
		return (deleted) ? new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	public Map<String, Object> error(Exception exception) {
		return Map.of("timestamp", System.currentTimeMillis(),
			"error", exception.getMessage());
	}
	
}

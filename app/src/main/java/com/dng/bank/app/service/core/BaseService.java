package com.dng.bank.app.service.core;

import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;
import com.dng.bank.app.exception.EntityNotFoundException;
import com.dng.bank.app.repository.core.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseService<T extends BaseLongPrimaryKeyEntity, DTO> {
	
	@Autowired
	protected BaseRepository<T, Long> repository;
	@Autowired
	protected ModelMapper modelMapper;
	
	public BaseService(BaseRepository<T, Long> repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}
	
	public abstract Class<DTO> getDtoType();
	
	public abstract Class<T> getEntityType();
	
	public abstract String getEntityName();
	
	public List<DTO> findAll() {
		return repository
			       .findAll()
			       .stream()
			       .map(s -> modelMapper.map(s, getDtoType()))
			       .collect(Collectors.toList());
	}
	
	public boolean exist(Long id) {
		return repository.existsById(id);
	}
	
	public DTO findById(Long id) {
		Optional<T> data = repository.findById(id);
		if (data.isPresent()) {
			return modelMapper.map(data.get(), getDtoType());
		}
		throw new EntityNotFoundException(getEntityName() + " not found with id: " + id);
	}
	
	public T findByEntityId(Long id) {
		Optional<T> data = repository.findById(id);
		if (data.isPresent()) {
			return data.get();
		}
		throw new EntityNotFoundException(getEntityName() + " not found with id: " + id);
	}
	
	public DTO save(DTO dto) {
		T data = modelMapper.map(dto, getEntityType());
		T newData = repository.save(data);
		return modelMapper.map(newData, getDtoType());
	}
	
	public boolean deleteById(Long id) {
		if (!repository.existsById(id)) {
			log.warn(getEntityName() + " not found with id: " + id);
			return false;
		} else {
			repository.deleteById(id);
			return true;
		}
	}
	
}

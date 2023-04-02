package com.satsana.generic.service;

import com.satsana.generic.model.AbstractEntity;
import com.satsana.generic.repository.LongIdCrudRepository;


public abstract class LongIdCrudService<
	Entity extends AbstractEntity,
	CreateDto,
	GetDto,
	Repository extends LongIdCrudRepository<Entity>
> extends GenericCrudService<Long, Entity, CreateDto, GetDto, Repository> {
	
	public LongIdCrudService(Repository repository) {
		super(repository);
	}

	@Override
	protected Entity setId(Long id, Entity entity) {
		entity.setId(id);
		return entity;
	}	
}
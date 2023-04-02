package com.satsana.generic.service;

import com.satsana.generic.model.AbstractEntity;
import com.satsana.generic.repository.LongIdCrudRepository;

public abstract class NoDtoLongIdCrudService<
	Entity extends AbstractEntity,
	Repository extends LongIdCrudRepository<Entity>
> extends LongIdCrudService<Entity, Entity, Entity, Repository> {
	
	public NoDtoLongIdCrudService(Repository repository) {
		super(repository);
	}

	@Override
	public Entity ingress(Entity dto) {
		return dto;
	}

	@Override
	public Entity egress(Entity entity) {
		return entity;
	}
}

package com.satsana.generic.service;

import com.satsana.generic.repository.GenericCrudRepository;

public abstract class NoDtoCrudService<
	Id,
	Entity,
	Repository extends GenericCrudRepository<Id, Entity>
> extends GenericCrudService<Id, Entity, Entity, Entity, Repository> {

	public NoDtoCrudService(Repository repository) {
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
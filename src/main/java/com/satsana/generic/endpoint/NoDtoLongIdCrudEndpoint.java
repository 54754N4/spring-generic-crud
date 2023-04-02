package com.satsana.generic.endpoint;

import com.satsana.generic.model.AbstractEntity;
import com.satsana.generic.repository.LongIdCrudRepository;
import com.satsana.generic.service.NoDtoLongIdCrudService;

public abstract class NoDtoLongIdCrudEndpoint<
	Entity extends AbstractEntity, 
	Repository extends LongIdCrudRepository<Entity>,
	Service extends NoDtoLongIdCrudService<Entity, Repository>> 
extends LongIdCrudEndpoint<Entity, Entity, Entity, Repository, Service> {
	
	public NoDtoLongIdCrudEndpoint(Service service) {
		super(service);
	}
}

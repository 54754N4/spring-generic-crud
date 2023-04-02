package com.satsana.generic.endpoint;

import com.satsana.generic.model.AbstractEntity;
import com.satsana.generic.repository.LongIdCrudRepository;
import com.satsana.generic.service.LongIdCrudService;

public abstract class LongIdCrudEndpoint<
	Entity extends AbstractEntity,
	CreateDTO,
	GetDTO,
	Repository extends LongIdCrudRepository<Entity>,
	Service extends LongIdCrudService<Entity, CreateDTO, GetDTO, Repository>
> extends GenericCrudEndpoint<Long, Entity, CreateDTO, GetDTO, Repository, Service>{
	
	public LongIdCrudEndpoint(Service service) {
		super(service);
	}
}
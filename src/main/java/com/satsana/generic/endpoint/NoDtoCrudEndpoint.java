package com.satsana.generic.endpoint;

import com.satsana.generic.repository.GenericCrudRepository;
import com.satsana.generic.service.NoDtoCrudService;

public class NoDtoCrudEndpoint<
	Id,
	Entity,
	Repository extends GenericCrudRepository<Id, Entity>,
	Service extends NoDtoCrudService<Id, Entity, Repository>
> extends GenericCrudEndpoint<Id, Entity, Entity, Entity, Repository, Service> {

	public NoDtoCrudEndpoint(Service service) {
		super(service);
	}
}
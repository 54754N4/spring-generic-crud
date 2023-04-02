package com.satsana.generic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericCrudRepository<Id, Entity> extends CrudRepository<Entity, Id> {
	Page<Entity> findAll(Pageable pageable);
}

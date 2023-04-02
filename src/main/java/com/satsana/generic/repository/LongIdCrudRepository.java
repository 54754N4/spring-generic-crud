package com.satsana.generic.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.satsana.generic.model.AbstractEntity;

/* JPA query grammar
 * Reference: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 */
@NoRepositoryBean
public interface LongIdCrudRepository<Entity extends AbstractEntity> extends GenericCrudRepository<Long, Entity> {
	
}

package com.satsana.generic.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.satsana.generic.exception.CrudError.NotFoundException;
import com.satsana.generic.repository.GenericCrudRepository;

import jakarta.transaction.Transactional;

public abstract class GenericCrudService<
	Id,
	Entity,
	CreateDto,
	GetDto,
	Repository extends GenericCrudRepository<Id, Entity>
> {
	private Repository repository;

	public GenericCrudService(Repository repository) {
		this.repository = repository;
	}

	protected Repository getRepository() {
		return repository;
	}

	protected abstract Entity setId(Id id, Entity entity);
	public abstract Entity ingress(CreateDto dto);
	public abstract GetDto egress(Entity entity);

	@Transactional
	public Entity create(Entity entity) {
		return repository.save(entity);
	}

	public Entity get(final Id id) throws NotFoundException {
		var entity = repository.findById(id);
		if (entity.isEmpty())
			throw new NotFoundException();
		return entity.get();
	}

	public Page<Entity> getAll(final Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Entity> getAll() {
		return getAll(PageRequest.ofSize(Integer.MAX_VALUE));
	}
	
	public Entity getOrCreate(final Id id, Entity entity) {
		try {
			return get(id);
		} catch (NotFoundException e) {
			return create(entity);
		}
	}

	public Entity upsert(final Id id, Entity entity) throws NotFoundException {
		try {
			setId(id, entity);
			return update(id, entity);
		} catch (NotFoundException e) {
			return create(entity);
		}
	}

	@Transactional
	public Entity update(final Id id, Entity entity) throws NotFoundException {
		setId(id, entity);
		if (!repository.existsById(id))
			throw new NotFoundException();
		return repository.save(entity);
	}

	@Transactional
	public Entity remove(final Id id) throws NotFoundException {
		Optional<Entity> entity = repository.findById(id);
		Entity result = null;
		if (entity.isEmpty())
			throw new NotFoundException();
		repository.delete(result = entity.get());	
		return result;
	}

	@Transactional
	public Long removeAll() {
		long count = repository.count();
		repository.deleteAll();
		return count;
	}
}
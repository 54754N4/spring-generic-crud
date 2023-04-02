package com.satsana.generic.endpoint;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.satsana.generic.exception.CrudError.NotFoundException;
import com.satsana.generic.repository.GenericCrudRepository;
import com.satsana.generic.service.GenericCrudService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

public class GenericCrudEndpoint<
	Id,
	Entity,
	CreateDTO,
	GetDTO,
	Repository extends GenericCrudRepository<Id, Entity>,
	Service extends GenericCrudService<Id, Entity, CreateDTO, GetDTO, Repository>
> {
	private Service service;

	public GenericCrudEndpoint(Service service) {
		this.service = service;
	}

	public Service getService() {
		return service;
	}

	@Operation(summary = "Create new entity")
	@PostMapping("/create")
	public ResponseEntity<GetDTO> create(@RequestBody CreateDTO dto) {
		return ResponseEntity.ok(service.egress(service.create(service.ingress(dto))));
	}

	@Operation(summary = "Get entity by it's ID or create it if non-existant")
	@PostMapping("/get/create/{id}")
	public ResponseEntity<GetDTO> getOrCreate(
			@Parameter(description = "id of entity to be fetched/created") @PathVariable Id id,
			@RequestBody CreateDTO dto
			) {
		return ResponseEntity.ok(service.egress(service.getOrCreate(id, service.ingress(dto))));
	}

	@Operation(summary = "Get entity by it's ID")
	@GetMapping("/get/{id}")
	public ResponseEntity<GetDTO> get(
			@Parameter(description = "id of entity to be fetched") @PathVariable Id id
			) throws NotFoundException {
		return ResponseEntity.ok(service.egress(service.get(id))); 
	}

	@Operation(summary = "Get all entities as paged results")
	@GetMapping("/get/all")
	public Page<GetDTO> getAll(@ParameterObject Pageable pageable) {
		return service.getAll(pageable)
				.map(service::egress);
	}

	@Operation(summary = "Get all entities")
	@GetMapping("/get/all")
	public Page<GetDTO> getAll() {
		return service.getAll()
				.map(service::egress);
	}
	
	@Operation(summary = "Updated entity by it's ID")
	@PutMapping("/update/{id}")
	public ResponseEntity<GetDTO> update(
			@Parameter(description = "id of entity to be updated") @PathVariable Id id,
			@RequestBody CreateDTO dto
			) throws NotFoundException {
		return ResponseEntity.ok(service.egress(service.update(id, service.ingress(dto))));
	}

	@Operation(summary = "Delete entity by it's ID")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GetDTO> remove(
			@Parameter(description = "id of entity to be deleted") @PathVariable Id id
			) throws NotFoundException {
		Entity entity = service.remove(id);
		if (entity == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(service.egress(entity));
	}

	@Operation(summary = "Delete all entities")
	@DeleteMapping("/delete/all")
	public Long removeAll() {
		return service.removeAll();
	}
}
package com.ra.model.repository;

import java.util.List;

public interface IGenericRepository<Entity, Long> {
    List<Entity> findAll();

    Entity save(Entity entity);

    Entity findById(Long id);

    void delete(Entity entity);

}

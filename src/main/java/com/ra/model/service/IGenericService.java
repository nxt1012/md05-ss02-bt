package com.ra.model.service;

import java.util.List;

public interface IGenericService <Entity, Long>{
    List<Entity> findAll();

    Entity save(Entity entity);

    Entity findById(Long id);

    void delete(Entity entity);
}

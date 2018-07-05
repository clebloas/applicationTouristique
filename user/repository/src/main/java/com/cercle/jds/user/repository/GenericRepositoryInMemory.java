package com.cercle.jds.user.repository;

import com.cercle.jds.user.domain.Player;
import com.cercle.jds.user.domain.common.Entity;
import com.cercle.jds.user.domain.common.Identity;
import com.cercle.jds.user.domain.common.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GenericRepositoryInMemory<T extends Entity> implements Repository<T> {

    private final Map<Identity<T>, T> entities;

    public GenericRepositoryInMemory() {
        entities = new HashMap<>();
    }

    @Override
    public T load(Identity<T> identity) {
        return entities.get(identity);
    }

    @Override
    public void save(T entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public boolean exist(Identity<T> identity) {
        return entities.containsKey(identity);
    }

    @Override
    public Set<T> retrieveAll() {
        return entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }
}

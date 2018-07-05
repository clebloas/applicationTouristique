package com.cercle.jds.user.domain.common;

import java.util.Set;

public interface Repository<T extends Entity> {
    T load(Identity<T> identity);
    void save(T entity);
    boolean exist(Identity<T> identity);
    Set<T> retrieveAll();
}

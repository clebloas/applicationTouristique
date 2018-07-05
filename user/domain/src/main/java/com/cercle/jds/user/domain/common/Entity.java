package com.cercle.jds.user.domain.common;

import lombok.Getter;

import static org.apache.commons.lang3.Validate.notNull;

public abstract class Entity<T> {
    @Getter
    private Identity<T> id;

    protected Entity(Identity<T> id) {
        this.id = notNull(id);
    }

    boolean is(Entity<T> entity) {
        return this.id.equals(entity.id);
    }
}

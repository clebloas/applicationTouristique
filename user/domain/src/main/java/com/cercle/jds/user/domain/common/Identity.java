package com.cercle.jds.user.domain.common;

import lombok.EqualsAndHashCode;

import static org.apache.commons.lang3.Validate.notBlank;

@EqualsAndHashCode(doNotUseGetters = true)
public abstract class Identity<T> {

    private final String id;

    protected Identity(String id) {
        this.id = notBlank(id);
    }

    public String identity() {
        return id;
    }

}

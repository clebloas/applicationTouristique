package com.cercle.jds.user.domain;

import com.cercle.jds.user.domain.common.Identity;

public class PlayerId extends Identity<Player> {
    public PlayerId(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "{PlayerId:"+identity()+"}";
    }
}

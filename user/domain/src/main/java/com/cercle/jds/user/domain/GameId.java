package com.cercle.jds.user.domain;

import com.cercle.jds.user.domain.common.Identity;

public class GameId extends Identity<Game> {
    public GameId(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return "{GameId:"+identity()+"}";
    }
}

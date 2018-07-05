package com.cercle.jds.user.domain;

import com.cercle.jds.user.domain.common.Entity;
import com.cercle.jds.user.domain.common.Identity;

import java.util.UUID;

public class Game extends Entity<Game> {
    protected Game(Identity<Game> id) {
        super(id);
    }

    public static Game create(){
        return new Game(new GameId(UUID.randomUUID().toString()));
    }

}

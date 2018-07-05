package com.cercle.jds.user.domain;

import com.cercle.jds.user.domain.common.Entity;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.unmodifiableSet;
import static org.apache.commons.lang3.Validate.notNull;

public final class Party extends Entity<Party> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Party.class);
    private final GameId gameId;
    private final Set<PlayerId> players;

    @Getter
    private Optional<Instant> startedAt;
    @Getter
    private final Optional<Instant> finishedAt;

    /**
     * Should be use only by the repository on loading
     */
    public Party(PartyId id, GameId gameId, Set<PlayerId> players, Optional<Instant> startedAt, Optional<Instant> finishedAt) {
        super(id);
        this.gameId = notNull(gameId);
        this.players = notNull(players);
        this.startedAt = notNull(startedAt);
        this.finishedAt = notNull(finishedAt);
    }

    public static Party create(GameId gameId, Set<PlayerId> players){
        return new Party(new PartyId(UUID.randomUUID().toString()), gameId, players, Optional.empty(), Optional.empty());
    }

    public void start(Clock clock){
        if(startedAt.isPresent()){
            LOGGER.warn("Party {} already started" + getId().identity());
            return;
        }
        startedAt = Optional.of(clock.instant());
    }

    public void finish(Clock clock){
        if(finishedAt.isPresent()){
            LOGGER.warn("Party {} already finished" + getId().identity());
            return;
        }
        startedAt = Optional.of(clock.instant());
    }

    public boolean finished(){
        return finishedAt.isPresent() ;
    }

    public boolean started(){
        return startedAt.isPresent() ;
    }

    public GameId gameId(){
        return gameId;
    }

    public Set<PlayerId> listPlayers(){
        return unmodifiableSet(players);
    }
}

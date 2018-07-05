package com.cercle.jds.user.domain;

import com.cercle.jds.user.domain.common.Entity;
import com.cercle.jds.user.domain.common.Identity;
import lombok.Getter;

import java.util.*;

import static java.util.Collections.unmodifiableSet;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Player extends Entity<Player> {

    @Getter
    private final String lastName;
    @Getter
    private final String firstName;
    private final Set<PartyId> parties;
    private final Set<GameId> ownGames;

    /**
     * Should only be used by repository on the load
     */
    public Player(Identity<Player> id, String lastName, String firstName, Set<PartyId> parties, Set<GameId> ownGames) {
        super(id);
        this.lastName = notEmpty(lastName);
        this.firstName = notEmpty(firstName);
        this.parties = notNull(parties);
        this.ownGames = notNull(ownGames);
    }

    public static Player create(String firstName, String lastName){
        return new Player(new PlayerId(UUID.randomUUID().toString()), lastName, firstName, new HashSet<>(), new HashSet<>());
    }

    public void hold(GameId gameId){
        this.ownGames.add(notNull(gameId));
    }

    public void participateTo(PartyId partyId){
        this.parties.add(notNull(partyId));
    }

    public Set<PartyId> parties(){
        return unmodifiableSet(parties);
    }

    public Set<GameId> listOwnGames(){
        return unmodifiableSet(ownGames);
    }

}

package com.cercle.jds.player.application;

import com.cercle.jds.user.domain.Game;
import com.cercle.jds.user.domain.Party;
import com.cercle.jds.user.domain.Player;
import com.cercle.jds.user.domain.common.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ControllersConfiguration {
    @Bean
    public PartyController partyController(Repository<Party> parties, Clock clock){
        return new PartyController(parties, clock);
    }
    @Bean
    public GameController gameController(Repository<Game> games){
        return new GameController(games);
    }
    @Bean
    public PlayerController playerController(Repository<Player> players, Repository<Game> games, Repository<Party> parties){
        return new PlayerController(players, games, parties);
    }
}

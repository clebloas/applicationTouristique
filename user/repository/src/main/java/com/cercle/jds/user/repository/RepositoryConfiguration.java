package com.cercle.jds.user.repository;

import com.cercle.jds.user.domain.Game;
import com.cercle.jds.user.domain.Party;
import com.cercle.jds.user.domain.Player;
import com.cercle.jds.user.domain.common.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    @Bean
    public Repository<Player> playerRepository(){
        return new GenericRepositoryInMemory<>();
    }
    @Bean
    public Repository<Party> partyRepositoryInMemory(){
        return new GenericRepositoryInMemory<>();
    }
    @Bean
    public Repository<Game> gameRepositoryInMemory(){
        return new GenericRepositoryInMemory<>();
    }

}

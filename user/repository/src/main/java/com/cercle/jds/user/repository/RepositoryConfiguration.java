package com.cercle.jds.user.repository;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    @Bean
    UserDao userDao(DSLContext dslContext){
        return new UserDao(dslContext);
    }
}

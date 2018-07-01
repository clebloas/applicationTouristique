package com.cercle.jds.user.application;

import com.cercle.jds.user.repository.RepositoryConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RepositoryConfiguration.class,
})
public class UserConfiguration {
}

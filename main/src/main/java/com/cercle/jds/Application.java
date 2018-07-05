package com.cercle.jds;

import com.cercle.jds.player.application.ControllersConfiguration;
import com.cercle.jds.user.repository.RepositoryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
		RepositoryConfiguration.class,
		ControllersConfiguration.class,
        DocumentationConfiguration.class,
		ClockConfiguration.class
})
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

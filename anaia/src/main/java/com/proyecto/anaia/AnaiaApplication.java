package com.proyecto.anaia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.proyecto.repositorios")
@EntityScan("com.proyecto.modelo")
@ComponentScan(basePackages = { "com.proyecto" })
public class AnaiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnaiaApplication.class, args);
	}

}

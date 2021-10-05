package com.github.juliherms.spring.web.flux;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Spring webflux crud example",
		version = "1.0",
		description = "sample documents"
))
public class SpringWebFluxExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxExampleApplication.class, args);
	}

}

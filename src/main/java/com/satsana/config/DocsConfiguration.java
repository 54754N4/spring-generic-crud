package com.satsana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
public class DocsConfiguration {
    @Bean
    OpenAPI baseOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Generic CRUD Template Spring Project")
				.version("1.0.0")
				.description("Documentation of endpoints")
				.contact(new Contact()
					.name("@54754N4")
					.url("https://github.com/54754N4")));
	}
}

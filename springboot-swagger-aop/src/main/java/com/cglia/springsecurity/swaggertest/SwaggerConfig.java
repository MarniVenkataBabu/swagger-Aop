package com.cglia.springsecurity.swaggertest;

import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket todoApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("REST-APIs").apiInfo(apiInfo()).select()
				.paths(postPaths()::test).build();
	}

	private Predicate<String> postPaths() {
		return PathSelectors.regex("/api.*");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Customer Rest APIs").description("API reference for Customer Service")
				.contact(new Contact("MARNI VENKATA BABU", "https://github.com/MarniVenkataBabu",
						"venkatthekiller@gmail.com"))
				.version("1.0").build();
	}

}

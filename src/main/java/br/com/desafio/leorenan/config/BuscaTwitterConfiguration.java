package br.com.desafio.leorenan.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class BuscaTwitterConfiguration extends WebMvcConfigurationSupport {

	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }
	
	@Bean
    public Docket swaggerTwitterApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Desafio Twitter")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.desafio.leorenan.controller.v1.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Twitter - REST APIs")
                .description("Aplicação Spring Boot com Docker para CI/CD.").termsOfServiceUrl("")
                .contact(new Contact("Léo Renan", "https://medium.com/the-resonant-web", "leo_renan@outlook.com"))
                .license("Apache License Version 2.0")	
                .version("0.0.1")
                .build();
    }
	
	@Override
	  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	        .addResourceLocations("classpath:/META-INF/resources/");

	    registry.addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	  }
	
}

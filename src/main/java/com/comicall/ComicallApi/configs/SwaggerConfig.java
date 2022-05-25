package com.comicall.ComicallApi.configs;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("comicall-api")
                .packagesToScan("com.comicall.ComicallApi.controllers")
                .pathsToMatch("/**")
                .build();
    }

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes("bearer-key",
//                                new SecurityScheme().type(SecuritySchemeType.HTTP).scheme("bearer").bearerFormat("JWT")));
//    }



}

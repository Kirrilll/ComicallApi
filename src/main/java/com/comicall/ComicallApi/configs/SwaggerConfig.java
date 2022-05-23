package com.comicall.ComicallApi.configs;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("comicall-api")
                .packagesToScan("com.comicall.ComicallApi.controllers")
                .pathsToMatch("/**")
                .build();
    }

}

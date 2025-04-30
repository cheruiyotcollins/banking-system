package com.dtb.profile.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Profile Service API")
                        .version("1.0")
                        .description("API documentation for Profile Service")
                        .contact(new Contact()
                                .name("DTB Bank Dev Team")
                                .email("DTBdev@bank.com")
                        )
                );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("profile-public")
                .pathsToMatch("/api/**")
                .build();
    }
}

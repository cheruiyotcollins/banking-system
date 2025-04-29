package com.dtb.payment_service.config;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI paymentServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Payment Service API")
                        .description("API for handling banking transactions")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Payment Service Documentation")
                        .url("https://api.example.com/docs/payment"));
    }
}
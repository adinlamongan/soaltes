package com.sekawanmedia.soaltes.swagger;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AllArgsConstructor;

@Configuration
public class OpenApiConfiguration {

        @Value("${adn.openapi.dev-url}")
        private String devUrl;

        @Value("${adn.openapi.prod-url}")
        private String prodUrl;

        @Bean
        public OpenAPI myOpenAPI() {
                Server devServer = new Server();
                devServer.setUrl(devUrl);
                devServer.setDescription("Server URL in Development environment");

                Server prodServer = new Server();
                prodServer.setUrl(prodUrl);
                prodServer.setDescription("Server URL in Production environment");

                Contact contact = new Contact();
                contact.setEmail("jmajid7@gmail.com");
                contact.setName("adin");


                Info info = new Info()
                                .title("Tutorial Management API")
                                .version("1.0")
                                .contact(contact)
                                .description("This API exposes endpoints to manage tutorials.");

                return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
        }
}
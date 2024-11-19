package br.com.alfac.foodproduto.infra.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        Info info = new Info();
        info.title("API Produto - Aplicação Fast Food - ALFAC");
        info.description("API para manipulação de produtos");
        info.version("0.2");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("auth");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("auth");


        return new OpenAPI().info(info)
                .addSecurityItem(securityRequirement)
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("auth", securityScheme));
    }
    
}

package org.example.gestionrh.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion RH API")
                        .version("1.0")
                        .description("Documentation de l'API pour la gestion RH"))
                .components(new Components()
                        .addSecuritySchemes("OAuth2",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .clientCredentials(new OAuthFlow()
                                                        .tokenUrl("https://keyckoak-prod-production-551c.up.railway.app/realms/gestionrh/protocol/openid-connect/token")
                                                        .scopes(new Scopes().addString("openid", "OpenID Connect scope"))
                                                )
                                        )
                        ))
                .addSecurityItem(new SecurityRequirement().addList("OAuth2"));
    }
}


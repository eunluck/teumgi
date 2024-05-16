package com.teumgi.api.configure;

import com.teumgi.api.configure.support.SimpleOffsetPageRequest;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfigure implements WebMvcConfigurer {

    @Value("${apiPrefix}")
    private String apiPrefix;

    @Bean
    public GroupedOpenApi publicRestApi() {
        return GroupedOpenApi.builder()
            .group(apiPrefix)
            .pathsToMatch("/**")
            .build();
    }

    @Bean
    public OpenAPI restOpenApi() {
        SpringDocUtils.getConfig()
            .replaceParameterObjectWithClass(Pageable.class, SimpleOffsetPageRequest.class)
            .addAnnotationsToIgnore(AuthenticationPrincipal.class);

        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("Authorization", new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("jwt")
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization")))
            .addSecurityItem(new SecurityRequirement().addList("Authorization"))
            .info(
                new Info().title("Teumgi-API")
                    .description("Teumgi application API document")
                    .version("0.5"))
            .externalDocs(new ExternalDocumentation()
                .description("010-2352-3943")
                .url("http://test.com"));

    }

}
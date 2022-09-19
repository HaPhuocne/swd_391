package com.fpt.swd391.fall2022.swd391.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi30Config {

    private final String moduleName;
    private final String apiVersion;

    public OpenApi30Config(
            @Value("${module-name:Module}") String moduleName,
            @Value("${api-version:v1}") String apiVersion) {
    this.moduleName = moduleName;
    this.apiVersion = apiVersion;
    }

//  @Bean
//  public OpenAPI customOpenAPI() {
//    final String securitySchemeName = "bearerAuth";
//    final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
//    return new OpenAPI()
//        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
//        .components(
//            new Components()
//                .addSecuritySchemes(securitySchemeName,
//                    new SecurityScheme()
//                        .name(securitySchemeName)
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")))
//        .info(new Info().title(apiTitle).version(apiVersion));
//  }
}

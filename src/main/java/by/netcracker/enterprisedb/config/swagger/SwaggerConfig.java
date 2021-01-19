package by.netcracker.enterprisedb.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/** @author Andrey Egorov */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Enterprise DB")
        .description("Project for NetCracker")
        .license("No license")
        .licenseUrl("http://unlicense.org")
        .termsOfServiceUrl("")
        .version("1.0")
        .contact(
            new Contact("Andrey Egorov", "https://t.me/myfosse", "andrey.egorov.minsk@gmail.com"))
        .build();
  }

  @Bean
  public Docket docket() {
    List<SecurityReference> references =
        Collections.singletonList(
            new SecurityReference(
                "basicAuth",
                Stream.of(new AuthorizationScope("basicAuth", "basicAuth"))
                    .toArray(AuthorizationScope[]::new)));
    List<SecurityContext> securityContexts =
        Collections.singletonList(SecurityContext.builder().securityReferences(references).build());
    return new Docket(DocumentationType.SWAGGER_2)
        .securitySchemes(Collections.singletonList(new BasicAuth("basicAuth")))
        .securityContexts(securityContexts)
        .apiInfo(apiInfo())
        .select()
        .paths(PathSelectors.any())
        .apis(RequestHandlerSelectors.basePackage("by.netcracker.enterprisedb.controller"))
        .build();
  }
}

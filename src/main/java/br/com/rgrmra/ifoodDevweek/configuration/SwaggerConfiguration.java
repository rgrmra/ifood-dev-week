package br.com.rgrmra.ifoodDevweek.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {
    @Bean
    public Docket getBean() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .paths(PathSelectors.regex("/ifood-dev-week/.*"))
                .build()
                .apiInfo(getInfo());
    }

    public ApiInfo getInfo() {
        return new ApiInfoBuilder()
                .title("Ifood Dev Week Project - DIO")
                .description("API to manage a delivery application.<br>" +
                        "This application contain the basic functionalities, however, necessary for the operation" +
                        "of a delivery application.")
                .version("1.0.0")
                .build();
    }
}


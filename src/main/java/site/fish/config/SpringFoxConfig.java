package site.fish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Description: [SpringFoxConfig ]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 21:49
 */
@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(
                        RequestHandlerSelectors.withClassAnnotation(RestController.class)
                                .or(RequestHandlerSelectors.withClassAnnotation(Controller.class))
                                .or(RequestHandlerSelectors.withClassAnnotation(RequestMapping.class))
                                .and(RequestHandlerSelectors.basePackage("site.fish"))
                )
                .paths(PathSelectors.any())
                .build();
    }
}

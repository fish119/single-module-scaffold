package site.fish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description: [Main Class]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 14:04
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
@ComponentScan("site.fish")
public class SingleModuleScaffoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleModuleScaffoldApplication.class, args);
    }

}

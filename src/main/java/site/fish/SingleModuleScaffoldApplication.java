package site.fish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 * Description: [Main Class]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 14:04
 */
@SpringBootApplication
@EnableJpaAuditing
public class SingleModuleScaffoldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleModuleScaffoldApplication.class, args);
	}

}

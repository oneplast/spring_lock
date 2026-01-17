package io.river.spring_lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLockApplication.class, args);
	}

}

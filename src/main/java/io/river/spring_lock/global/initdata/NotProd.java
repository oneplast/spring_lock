package io.river.spring_lock.global.initdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import io.river.spring_lock.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("!prod")
@Configuration
@Slf4j
@RequiredArgsConstructor
public class NotProd {

	@Autowired
	@Lazy
	private NotProd self;

	private final PostService postService;

	@Bean
	@Order(3)
	public ApplicationRunner initNotProd() {
		return args -> {
			if (postService.count() > 0)
				return;

			self.work1();
		};
	}

	@Transactional
	public void work1() {
		postService.write("제목 1");
		postService.write("제목 2");
		postService.write("제목 3");
	}
}

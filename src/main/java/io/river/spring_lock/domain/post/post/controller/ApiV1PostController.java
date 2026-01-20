package io.river.spring_lock.domain.post.post.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.river.spring_lock.domain.post.post.entity.Post;
import io.river.spring_lock.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1PostController {

	private final PostService postService;

	@GetMapping("/{id}")
	public Post getPost(@PathVariable long id) {

		return postService.findById(id).get();
	}

	@SneakyThrows
	@GetMapping("/{id}/withShareLock")
	public Post getWithShareLockPost(@PathVariable long id) {
		Post post = postService.findWithShareLockById(id).get();

		Thread.sleep(10_000L);

		return post;
	}

	@SneakyThrows
	@GetMapping("/{id}/withWriteLock")
	@Transactional
	public Post getWithWriteLockPost(@PathVariable long id) {
		Post post = postService.findWithWriteLockById(id).get();

		Thread.sleep(10_000L);

		return post;
	}

	@GetMapping("/{id}/putWithPessimistic")
	@Transactional
	public Post modifyWithPessimistic(@PathVariable long id, String title) {
		Post post = postService.modifyWithPessimistic(id, title);

		return post;
	}

	@GetMapping("/{id}/putWithOptimistic")
	@Transactional
	public Post modifyWithOptimistic(@PathVariable long id, String title) {
		Post post = postService.modifyWithOptimistic(id, title);

		return post;
	}
}

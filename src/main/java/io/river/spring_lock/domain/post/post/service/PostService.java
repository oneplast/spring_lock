package io.river.spring_lock.domain.post.post.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.river.spring_lock.domain.post.post.entity.Post;
import io.river.spring_lock.domain.post.post.repository.PostRepository;
import io.river.spring_lock.global.rsdata.RsData;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;
	private final EntityManager em;
	private final ApplicationEventPublisher publisher;

	@Transactional
	public RsData<Post> write(String title) {
		Post post = postRepository.save(
			Post.builder()
				.title(title)
				.build()
		);

		return RsData.of(post);
	}

	public long count() {
		return postRepository.count();
	}
}

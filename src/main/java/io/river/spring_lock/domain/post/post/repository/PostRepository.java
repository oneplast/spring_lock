package io.river.spring_lock.domain.post.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import io.river.spring_lock.domain.post.post.entity.Post;
import jakarta.persistence.LockModeType;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Lock(LockModeType.PESSIMISTIC_READ)
	Optional<Post> findWithShareLockById(long id);
}

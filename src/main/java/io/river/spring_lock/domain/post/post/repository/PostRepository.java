package io.river.spring_lock.domain.post.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.river.spring_lock.domain.post.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}

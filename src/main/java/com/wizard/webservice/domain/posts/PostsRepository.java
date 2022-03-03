package com.wizard.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD메서드가 자동생성된다.
 * @Repository
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}

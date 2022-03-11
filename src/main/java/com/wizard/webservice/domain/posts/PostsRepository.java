package com.wizard.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

/**
 * JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD메서드가 자동생성된다.
 * @Repository
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    /**
     * Spring Data JPA에서 제공하는 기본 메서드를 사용할 수 있지만
     * 이런 식으로 @Query로도 가능하다는 것을 보여주기 위해 작성하였다.
     */
    @Query("Select p" +
            " FROM Posts p" +
            " ORDER BY p.id DESC")
    Stream<Posts> findAllDesc();
}

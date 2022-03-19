# 알게 된 점

- Repository 인터페이스에 `JpaRepository<Entity클래스, PK타입>` 상속
    - JpaRepository는 인터페이스이다.
        - `JPA specific extension of org.springframework.data.repository.Repository`
    - `@Repository`를 사용하지 않아도 된다 !
    - JpaRepository에는 검색 관련 메서드는 `findAll`밖에 없다.
        - 인터페이스에 검색을 위한 메서드를 추가하는 것으로, 필요한 검색 메서드를 늘릴 수 있다.
- DB가 없는데 Repository 사용?
    - SpringBoot에서의 테스트 코드는 메모리 DB인 H2를 기본적으로 사용하기 때문이다.
- DTO를 왜 사용할까?
    - Request와 Request용 DTO는 View를 위한 클래스라 자주 변경 OK
    - Entity 클래스는 setter 사용하지 않지만 DTO는 `@ReqeustBody`로 외부에서 데이터를 받는 경우엔 `기본생성자 + setter` 를 통해서만 값이 할당 되기 때문이다.
        - [https://jojoldu.tistory.com/407](https://jojoldu.tistory.com/407)
        - POST 요청일 때
            - Setter가 없어도 Jackson2HttpMessageConverter의 ObjectMapper를 사용하여 할당된다 !
        - GET 요청일 때
            - JSON이 아닌 Query Parameter이기 때문에 Jackson2HttpMessageConverter를 사용하지 않으므로 Setter이 없으면 안된다.
                - `@ControllerAdvice` 선언하면 됨
                    - setter가 아닌 Field에 직접 접근 하는 것
                        - `iniDirectFieldAccess`
- Controller에서 `Dto.toEntity()`를 통해서 바로 전달해도 되는데 Service에서 DTO를 받는 이유?
    - Controller와 Service의 역할을 분리하기 위함이다. 👉  [OSIV와 성능 최적화](https://www.notion.so/OSIV-0e1aafd1b33441b4a8e1341c1a37715f)
    - 비즈니스 로직 & 트랜잭션 관리 모두 Service에서 관리하고, View와 연동되는 부분은 Controller에서 담당하도록 구성
- 트랜잭션? [트랜잭션 (Transaction) // ACID](https://www.notion.so/Transaction-ACID-cff3ccc4b5ac422d8cd3f98b47970275)  `,`  [OSIV와 성능 최적화](https://www.notion.so/OSIV-0e1aafd1b33441b4a8e1341c1a37715f)
    - Service 메서드는 `@Transaction`을 기본으로 갖는다.

---

# 개발 일지

2022년 3월 2일

- Sourcetree를 설치했다.
    - 왜 필요할까?
    - `Git`을 사용하기 위해서
        - `Git`은 DVCS라고해서 코드 관리를 `로컬저장소`와 `원격저장소` 양쪽에서 관리할 수 있다.
        - 코드 관리용 서버를 구축할 필요 없이 `Github`를 사용하면 바로 원격 저장소를 사용할 수 있기 때문에 `Git`을 사용하는게 더 낫다.
- Github에 Repository 생성
- Sourcetree - Repository 설정
    - 에서 Repository에 Respository Settings에 들어가 로컬의 프로젝트와 Github와 연동시켰다.
    - Sourcetree로 로컬의 프로젝트와 Github와 연동 끝
- `.gitigonre`
    - Git에서 관리하지 않는 대상들을 제거
    - 각 IDE에서 프로젝트가 생성되면 자동으로 생성되는 파일들은 굳이 Git으로 관리할 필요가없기 때문이다.
    - Git 체크대상에서 제외
- Git commit&push
    - Sourcetree를 이용해 로컬과 원격 저장소를 함께 관리할 수 있다.
    - 👇 원격 저장소인 Github에 푸시 성공

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6a62d06f-0b6a-499f-a6d9-cf195a4de41e/Untitled.png)

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b919088f-d66b-496b-934c-2723c437e374/Untitled.png)


---

<aside>
🧙‍♂️ SpringBoot & JPA 사용  🧙‍♂️

</aside>

- Entity 클래스 생성
    - `@Builder`를 사용한 이유 [빌더 패턴 (Builder pattern)](https://www.notion.so/Builder-pattern-0d3ed3011dbe4ad598275719ce9a5b22) [Setter, Builder](https://www.notion.so/Setter-Builder-d0a0af8305be4e7c82d4e923fb2ec2c1)
        - 생성자의 경우 지금 채워야할 필드가 무엇인지 명확히 지정할 수가 없기 때문이다.


2022년 3월 3일

- JpaRepository<Entity클래스, PK타입>
    - `ibatis`/`MyBatis` 등에서 DAO라고 불리는 DB Layer 접근자이다.
    - JPA에서 Repository라고 부른다 ! (인터페이스로 생성)
    - 단순히 인터페이스 생성후 JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD 메서드가 자동생성된다.
    - `@Repository`를 추가할 필요가 없다.
- lombok
    - `@RequiredArgsConstructor`
    - `final` 이 붙은 필드를 인자값으로 하는 생성자
    - 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결할 수 있다.
        - 해당 컨트롤러에 새로운 서비스를 추가하거나, 기존 컴포넌트를 제거하는 등이 발생해도 생성자 코드는 전혀 손대지 않아도 된다
- PostsSaveRequestDto
    - Entity 직접 반환이 아닌 DTO 반환
        - Entity 클래스가 변경되면 여러 클래스에 영향
        - Request와 Response용 DTO는 View를 위한 클래스라 자주 변경 OK
        - `View Layer`와 `DB Layer`를 역할 분리하자 !!!!!
    - 왜 Entity 클래스에서는 `@Setter`를 사용하지 않는데 DTO에서는 사용할까?
        - Controller에서 @RequestBody로 외부에서 데이터를 받는 경우엔 `기본생성자 + set메서드`를 통해서만 값이 할당 된다.
        - 그래서 이때만 `setter`를 허용한다.
    - `join`할 때
        - Controller에서 결과값으로 여러 테이블을 조인해서 줘야 할 경우가 빈번하다.
            - → Entity 클래스만으로 표현하기가 어렵다.

2022년 3월 4일

😢 ...

2022년 3월 5일

- Postman + h2 web console
    - 나는 application.properties보다 YAML을 선호한다.
        - application.properties → application.yml
    - H2 DB랑 연결해주기
        - 쿼리를 보기 위해`show-sql: true` 설정을 해주었다.

        ```yaml
        spring:
          datasource:
            url: jdbc:h2:tcp://localhost/~/spring-webservice
            username: sa
            password:
            driver-class-name: org.h2.Driver
        
          jpa:
            hibernate:
              ddl-auto: create
            show-sql: true
        ```

    - Postman으로 POST 요청 OK

- 생성시간/수정시간 자동화 - `JPA Auditing`
    - 보통 Entity에는 해당 데이터의 생성시간과 수정시간이 들어간다.
        - 차후 유지보수에 중요한 정보이기 때문이다.
        - 매번 DB에 INSERT 하기 전, UPDATE 하기 전에 날짜 데이터를 등록/수정 하는 코드가 들어간다.

            ```java
            public void savePosts() {
            		...
            		posts.setCreateDate(new LocalDate());
            		postsRepository.save(posts);
            		...
            }
            ```

    - LocalDate 사용
        - Java의 기본 날짜 타입인 Date의 문제점을 고친 LocalDate와 LocalDateTime이 Java8부터 등장
    - BaseTimeEntity 생성
        - 모든 Entity들의 상위 클래스가 되어 Entity들의 createdDate, modifiedDAte를 자동으로 관리하는 역 할 !! 😃
        - [MappedSuperClass](https://www.notion.so/MappedSuperClass-dd59a8a7fb9146a28001532c93f4641b)
            - JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드를 createdDate, modifiedDate도 컬럼으로 인식하게 한다.
        - [EntityListeners](https://www.notion.so/EntityListeners-972d0406f10147d4a209fbfd2e6e8b36)
            - BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다. + (AuditingEntityListener.class)

        ```java
        @Getter
        @MappedSuperclass
        @EntityListeners(AuditingEntityListener.class)
        public class BaseTimeEntity {
        
            @CreatedDate
            private LocalDateTime createdDate;
        
            @LastModifiedDate
            private LocalDateTime modifiedDate;
        }
        
        ```

    - Posts 클래스가 BaseTimeEntity르 상속 받도록 변경
        - `public class Posts extends BaseTimeEntity`
    - `JPA Auditing 어노테이션`들을 모두 활성화 시킬 수 있도록 Application 클래스에 `활성화 어노테이션`을 추가해야 한다.
- PostsRepositoryTest 클래스에 테스트 메서드 추가
    - JPA Auditing을 이용한 LocalDateTime이 상속된 Entity에 잘 들어갔는가?  🙆‍♂️ ok

      ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b88e1f1f-44d7-4288-b619-be28ebfd47ad/Untitled.png)


---

- Thymeleaf로 View를 만들자
    - 프로젝트 중간에 추가했기 때문에 implementation을 추가했다.

    ```java
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect'
    ```

    - WebController으로 main 페이지 띄우기 🙆‍♂️ ok
        - ViewResolver 이용 된 것
    - WebControllerTest 테스트 코드 🙆‍♂️ ok
- Service 메서드 추가하기
    - Service 메서드를 생성해서 `Transaction`까지 관리하기 위해
        - [OSIV와 성능 최적화](https://www.notion.so/OSIV-0e1aafd1b33441b4a8e1341c1a37715f)
- PostsService 생성
    - PostsServiceTest 테스트코드 작성
    - dto 패키지 만들어 분리
    - 생성자에 `@Builder`추가
- bootstarp 4.0, jQuery 추가
- 🤢
    - ㅇPostsService에서 PostsSaveRequestDto를 받아야 하는데 Controller에서 dto.toEntity()해서 Posts로 미리 형 변환해서 넘겨줘서 오류가 생겼었다.

### 2022년 03월 19일

* EC2 생성 후 Elastic IP 설정, 보안설정
* pem키 ~/.ssh에 copy해서 config 만들어서 git bash로 접속 완료
* RDS 생성 후 접속하는데 오류 🤢
  * 🙆‍♂ ok
    * SSLHandshakeException으로 TLS Protocol 버전이 달라서 설정해서 해결함
* MariaDB 파라미터 호환 오류 🤢
  * 🙆‍♂ ok
    * 버전에 따라 설정이 달라 여기서는 utf8이 아닌 utf8mb4 와 utf8mb4_general_ci로 한글이 깨지지 않게 설정하였다.
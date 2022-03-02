<aside>
🧙‍♂️ 2022년 3월 1일

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
    - 원격 저장소인 Github에 푸시 성공




---

<aside>
🧙‍♂️ SpringBoot & JPA 사용  🧙‍♂️

</aside>

- Entity 클래스 생성
    - `@Builder`를 사용한 이유 [빌더 패턴 (Builder pattern)](https://www.notion.so/Builder-pattern-0d3ed3011dbe4ad598275719ce9a5b22) [Setter, @Builder](https://www.notion.so/Setter-Builder-d0a0af8305be4e7c82d4e923fb2ec2c1)
        - 생성자의 경우 지금 채워야할 필드가 무엇인지 명확히 지정할 수가 없기 때문이다.

</aside>
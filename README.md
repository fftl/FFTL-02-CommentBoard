# CommentBoard <댓글 게시판 만들기>

### 개요

Vue.js를 이용한 프론트엔드와 Springboot를 이용해 진행한 프로젝트 입니다. Vue.js를 이용한 프론트엔드 서버와 Spring Boot 서버가 rest api로 데이터를 통신하여 실행되는 프로젝트입니다. Heroku를 이용해서 배포되고 있습니다. (https://fftl-02-front.herokuapp.com/)

### 사용기술

-   Java, Spring Boot, postgreSQL(heroku postgreSQL), JPA, gradle, git

### DB설계

```java

@Table(name="users")
User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String joinDate;
    private String nickname;

    @OneToMany(mappedBy = "user")
    List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Comment> comments = new ArrayList<>();

}

Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String nickname;
    private String regdate;

    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;

    @OneToMany(mappedBy = "board")
    List<Comment> comments = new ArrayList<>();
}

Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String comment;
    private String regdate;

    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

}

```

### 진행사항

꽤 이전에 완성했다고 멈췄던 프로젝트였습니다. 하지만 이후에 살펴보니 많이 정리가 되지 않은 상태의 프로젝트였기에 수정해보는 것이 좋겠다는 생각이 들었고, 프로젝트를 수정하고 있습니다.

-   연관관계 매핑을 사용하지 않고 직접 외래키를 만드는 방식으로 DB를 설계했었는데 이번에 연관관계를 사용해 보았습니다.

-   heroku를 통해 client, server 모두 배포를 하였습니다. server를 배포하던 중 DB도 heroku에서 제공하는 postgreSQL을 사용했습니다. 이 과정에서 user table을 생성할 수 없다는 에러가 발생했었습니다. 원인은 postgreSQL에서는 user가 예약어로써 사용되고 있었기 때문에 테이블 이름을 users로 그리고 이와 연관관계를 맺는 user_id를 users_id로 변경해 주어서 해결하였습니다.

-   Server의 Response를 정리하였습니다. api 특성상 다양한 형태의 반환값이 필요합니다. 그 때마다 그에 맞는 dto를 생성해주는 방식으로 진행하다보니 너무나 지저분한 느낌이 들도록 파일이 많아졌습니다. 그래서 Generic을 통한 Response로 일관화를 진행하여 파일을 정리하였습니다.

-   연관관계 매핑을 진행하며 발생한 문제도 있었습니다. JPA에서 연관관계 매핑을 사용할 때 많이들 겪게되는 문제라는데 바로 JPA 양방향 무한 루프 였습니다. 간단히 보자면 양방향 연관관계를 맺는 entitiy를 Response에 entity 그대로 반환하게 될 경우 아래와 같은 무한루프가 발생하는 경우입니다. User 안에는 board가 있고 board 또한 user의 정보를 가지고 있기에 계속해서 반복되는 것입니다.

    ```
    user
    {
        "id" : 1,
        "username" : "user",
        "password" : "1234",
        "board" : [
            {
                "title":"제목",
                "content" : "내용입니다"
                "user" : [
                    {
                        "id" : 1,
                        "username" : "user",
                        "password" : "1234",
                        "board" : [
                            {
                                "title":"제목",
                                "content" : "내용입니다"
                                "user" : [
                                            .
                                            .
                                            .
                ]
            }
        ]

    }
    ```

    이를 해결하기 위한 방법으로 Response를 할 떄에는 그에 맞는 Dto를 만들어 반환하는 방법과 Spring Boot에서 사용할 수 있는 어노테이션인 @JsonIdentityInfo를 사용하는 것입니다. 그 외에도 더 있는 것 같지만 저는 Dto를 사용하여 이를 해결해 내었습니다. 코드를 살펴 보시면 Service 단계에서 Entity를 반환하고 Controller 단계에서 마지막으로 Dto에 감싸서 Response하게 되는 모습을 볼 수 있습니다.

-   현재는 테스트 코드를 작업 중 입니다. 일단 Controller 테스트를 진행해 보았습니다. @WebMvcTest 를 이용하여 컨트롤러 부분을 테스트 클래스를 구성했고, MockMvc를 이용해서 요청을 확인하고 @MockBean를 이용해 @Service 단의 응답 값을 내보내주어 테스트가 성립되도록 구성해 보았습니다.

    다만 이렇게 진행 할 경우 update와 delete를 테스트할 때에는 미리 저장된 값이 없게 되는데 어떻게 진행할 지에 대해서는 더 찾아보고 있는 중입니다.

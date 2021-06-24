# FFTL-02-CommentBoard ( 댓글 게시판 만들기 )

> 목차

-   [개요](#개요)
-   [DB설계](#DB설계)
-   [API](#API)
-   [실행방법](#실행방법)
-   [사용기술](#사용기술)
-   [후기](#후기)

### 개요

Vue.js를 이용한 프론트엔드와 Nest.js, Springboot를 이용해 진행한 프로젝트 입니다. Vue.js를 이용한 프론트엔드 서버와 rest api로 데이터를 통신하며, Nest.js와 Springboot 두가지 모두 같은 요청값(?)을 가지고 있어 두가지 모두 동일하게 작동을 할 수 있도록 만들어 보았습니다.

### DB설계

회원가입, 로그인 기능이 존재하며, 댓글까지 존재하는 게시판입니다. 회원, 게시글, 댓글 엔티티가 존재합니다. 기본적으로 아래와 같이 구성되어 있으며 Springboot 프로젝트의 경우 JPA Entity, Nest.js 의 경우 TypeORM 으로 사용하고 있습니다.

SpringBoot, JPA Entity

```java
Board {

    @GeneratedValue
    @Id
    private Long bid;

    private String title;
    private String content;
    private String nickname;
    private String bregdate;

    private Long uid;
}

Comment {

    @GeneratedValue
    @Id
    private Long cid;

    private String nickname;
    private String comment;
    private String cregdate;

    private Long uid;
    private Long bid;

}

User {

    @Id
    @GeneratedValue
    private Long uid;

    private String username;
    private String password;
    private String joinDate;
    private String nickname;

}
```

### API

```java
// User @RequestMapping(value = "/user")
@GetMapping("/profile") => 로그인 토큰을 이용하여 현재 유저의 정보를 가져옵니다.

@PostMapping("/saveUser") => 회원가입 합니다.
@PostMapping("/login")  => 로그인 합니다.

// Board @RequestMapping(value = "/board")
@GetMapping("") => 모든 board를 가져옵니다.
@GetMapping("/{bid}") => id를 이용하여 board 하나를 가져옵니다.

@PostMapping("") => board를 생성합니다.

@PatchMapping("/{bid}" => id를 이용하여 board 하나를 수정합니다.

@DeleteMapping("/{bid}") => id를 이용하여 board 하나를 삭제합니다.

// Comment @RequestMapping("/comment")
@GetMapping("/{bid}") => board의 id를 이용하여 해당 board의 모든 comment를 가져옵니다.

@PostMapping("") => comment를 등록합니다.

@DeleteMapping("/{bid}") => id를 이용하여 comment 하나를 삭제합니다.

```

### 실행방법

~/fftl-vue-js01

```
$ npm run serve
```

~/fftl-nest-js01

```
npm run start:dev
```

</br>

### 사용기술

#### front

-   Vue.js
-   JavaScript

#### back

-   Nest.js

    -   TypeScript
    -   TypeORM

-   Springboot

    -   Java
    -   JPA

-   Spring Security
-   JWT
-   MySQL

### 후기

이번에는 회원가입, 로그인, 댓글쓰기 기능을 추가해보았습니다. 그리고 보안적인 공부를 하다가 알게된 jwt 를 이용한 로그인을 적용해 보았습니다. Spring boot의 경우 이전 팀프로젝트에서 사용해본 경험이 있어 조금은 나았지만, Nest.js의 경우 시행착오가 많았습니다. 또 테스트 코드에 대한 공부를 진행중이며 프로젝트를 정리한 뒤에 테스트 코드를 적용해 볼 예정입니다.

# FFTL-02-CommentBoard ( 댓글 게시판 만들기 )

## 개요

Vue.js를 이용한 프론트엔드와 Nest.js, Springboot를 이용해 진행한 프로젝트 입니다. Vue.js를 이용한 프론트엔드 서버와 rest api로 데이터를 통신하며, Nest.js와 Springboot 두가지 모두 같은 요청값(?)을 가지고 있어 두가지 모두 동일하게 작동을 할 수 있도록 만들어 보았습니다.

### DB설계

회원가입, 로그인 기능이 존재하며, 댓글까지 존재하는 게시판입니다. 회원, 게시글, 댓글 엔티티가 존재합니다.

SpringBoot, JPA Entity

Nest.Js, TypeORM

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

front

-   Vue.js

back

-   Nest.js

    -   TypeORM

-   Springboot

    -   JPA

-   MySQL

### 후기

이번에는 회원가입, 로그인, 댓글쓰기 기능을 추가해보았습니다. 로그인 기능에는 jwt 토큰을 사용해 보았습니다.

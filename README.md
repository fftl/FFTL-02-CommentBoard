# CommentBoard <댓글 게시판 만들기>

## 개요

로그인 기능과 문자로 이루어진 게시글 작성, 댓글 작성의 기능을 가지고 있는 게시판입니다. Vue.js를 이용한 클라이언트와 Springboot를 이용한 REST API서버로 실행되는 프로젝트 입니다. Heroku를 이용해서 배포되고 있습니다. Heroku의 특성상 처음 실행을 하여 요청을 할 때에 요청에 조금의 시간이 소요될 수 있습니다.(https://fftl-02-front.herokuapp.com/)

## 사용기술

Java, Spring Boot, postgreSQL(heroku postgreSQL), JPA, gradle, git

## DB설계

User, Board, Comment 세 개의 테이블만을 가진 작은 설계입니다. JPA를 사용하기 때문에 연관관계 맵핑을 사용하였고 연관관계를 맺은 각각은 서로의 id 값을 가진 것이 아니라 서로의 객체를 직접 가지고 있습니다.

```java
User

Long id
String username
String password
String joinDate
String nickname
Board **boards**
Comment **comments**

```

```java
Board

Long id
String title
String nickname
String regdate
User **user**
Comment **comments**

```

```java
Comment

Long id
String nickname
String comment
String regdate
**user**
**board**

```

## 프로젝트 후기

### **연관관계 매핑**

이전 까지는 JPA를 사용했지만 연관관계 맵핑을 하는 과정이 어렵기도 하고 각 Entity에 직접 foreign key를 집어 넣는 방법을 사용했었습니다. 이러한 방식은 연관된 테이블에 접근 할 때에 매번 foreign key를 이용해 조회를 하는 일을 해야하고, 이 일이 반복될 경우 무시할 수 없는 비용이 발생하기도 하고 객체지향적지 않기 때문에 연관관계 매핑에 대하여 학습을 하였고 적용해 보았습니다.

### **테스트코드 사용**

이전 까지는 Spring으로 API를 만들 때에 직접 서버를 실행시키고 postman과 같은 api 테스트 프로그램을 이용하여 일일이 테스트를 진행했었습니다. 허나 계속해서 서버를 실행시키고 테스트하고 수정 하고를 반복하면서 굉장히 비 효율적임을 느끼고 있었지만 익숙한 방식임에 이를 놓지 못하고 있었습니다.

허나 최근 책을 보고 공부를 진행하며 테스트 코드에 대한 중요성을 지속적으로 강조하는 많은 자료를 보게 되었고 Spring Boot test에 대해서 찾아보고, 적용해 보게 되었습니다. 이번 프로젝트에는 Controller를 테스트하는 테스트코드 만을 작성하였고 아직 미숙하지만 사용할 수 있게 된 것에 만족하는 기회였습니다.

### **Heroku를 통한 배포**

개요에서 설면한 것 처럼 이 프로젝트는 Heroku라는 클라우드 PaaS를 이용하여 배포되고 있습니다. 이전에는 AWS를 이용하여 저의 프로젝트를 배포해 보기도 하였으나 AWS는 완전한 무료가 아니었기에 무료이며 더 간단하고 접근하기 좋았던 Heroku를 이용하여 Server, Client를 각각 배포해 보았습니다. 개인 프로젝트를 진행 할 때에는 매우 유용한 Platform 인 것 같습니다.

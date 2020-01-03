# Spring Demo
멀티 모듈로 구성한 Springboot 데모 프로젝트입니다. `build.gradle` 만 코틀린으로 작성하였습니다. 

## 참고

+ 전체적인 구성 참고 : https://github.com/ihoneymon/bootiful-your-life
+ JPA/Hiberante 소스 참고 : https://github.com/holyeye/jpabook 
+ OAuth 참고 : https://brunch.co.kr/@sbcoba/1
```
+ Springboot 2.x
+ Gradle 6.x
+ Java 11
+ DBMS : H2

+ JPA/Hibernate
+ OAuth
+ Redis (정리중)
+ RabbitMQ Pub/Sub (정리중)
+ Redis Pub/Sub (정리중)
```

## 구성  
```
spring-demo
└─ module-domain : DB 설정, Entity, 공통 Util을 정의.
└─ module-api    : 클라이언트(회원가입, 주문) 비즈니스 로직과 관련된 Controller / Service / Repository 정의
└─ module-oauth  : 스프링 시큐리티 / OAuth2 서버 기능을 정의한 모듈 
└─ module-redis  : 정리중...
└─ module-amqp   : 정리중...
```

## oauth-server

### 
- TokenStore
+ https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql



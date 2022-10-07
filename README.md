# omechu-backend

### Summary

아…저번에 유튜브에서 봤던 그 가게 어디지??” 라고 자주 생각했습니다. 그러나 유튜브에 나왔던 가게는 찾기가 어려워 결국 다른 가게를 간 경우가 많았습니다.  그래서 유튜버들이 소개하는 맛집을 한눈에 볼 수 있고, 자기가 원하는 조건에 맞게 검색할 수 있는 사이트를 만들고자 프로젝트를 시작했습니다.             
## 프로젝트 전체 구조
![omechu_프로젝트 구조](https://user-images.githubusercontent.com/78574530/187073342-cbdaa715-77ab-42d6-8796-f502c6877b1c.jpg)

## 🚀 프로젝트 주요 관심사
- 이유와 근거가 명확한 기술의 사용을 지향합니다.
- 이 코드를 "왜" 써야하는지를 생각하였습니다.
- 백엔드와 프론트가 분산된 환경에 익숙해지고자 하였습니다. 
- 반복적인 작업은 자동화하여 개발의 효율을 높이기 위해 노력하였습니다.
  -  CI/CD 구축

## 기술 스택
> Java, Spring Boot, Spring Security, OAuth2 client, JPA + QueryDSL, Lombok, Gradle

- Spring Boot 2.7.
  - Spring Framework에서 클래스패스의 라이브러리를 자동으로 인식하여 설정해주고 내장 서버를 제공하는 등 많은 편의성을 제공하기 때문에 빠른 개발이 가능하다고 생각하여 Spring Boot를 사용했습니다.
  - Spring Security, Oauth 2.0
    - 스프링 시큐리티에서 인증, 인가 기능을 적용하기 위해 사용했습니다.
    - 간편한 로그인 및 회원가입을 위해 Oauth 2.0을 사용하였습니다.
    - OAuth2userInfo 인터페이스를 생성하여 카카오 이외에 다른 소셜 로그인 기능도 쉽게 추가할 수 있게 했습니다
```java
    public interface OAuth2userInfo {
        String getProviderId();     // ex google, facebook
        String getProvider();       // 해당 플랫폼 고유 아이디 ex> 123412312543
        String getEmail();  
        String getName();
    }

   🔽소셜 회원가입 관련 로직
  if( userRequest.getClientRegistration().getRegistrationId().equals("google") ) {
        log.info("구글 로그인 요청");
        oAuth2userInfo = new GoogleUserInfo(oAuth2User.getAttributes());
  } else if ( userRequest.getClientRegistration().getRegistrationId().equals("kakao") ) {
         log.info("카카오 로그인 요청");
        oAuth2userInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());
  } else {
         log.info("우리 사이트는 구글, 카카오 로그인만 지원합니다.");
  }
  
  String provider   = oAuth2userInfo.getProvider();    // google
  String providerId = oAuth2userInfo.getProviderId();  // 숫자 ex) 12345566
  String username   = provider + "_" + providerId;     // google_123456
  String password   = bCryptPasswordEncoder.encode(INITIAL_PW);
  String email      = oAuth2userInfo.getEmail();
  RoleType role     = RoleType.ROLE_USER;
  
  User userEntity = userRepository.findByUsername(username);
  
  if( userEntity == null ) {
        userEntity = User.builder()
                         .username(username)
                         .password(password)
                         .email(email)
                         .role(role)
                         .provider(provider)
                         .providerId(providerId)
                         .build();
        userRepository.save(userEntity);
        log.info(userEntity.getUsername() + " 님의 회원가입이 완료되었습니다.");
  } else {
        log.info(userEntity.getUsername() + " 님은 이미 회원가입되었습니다. ");
  }
  ```
- JWT
  -  Session 방식보다 확장성이 높고, 자원낭비가 덜하다고 생각해 로그인 방식으로 JWT를 사용했습니다.
- AWS EC2 배포
  - 스프링부트 프로젝트와 AWS RDS 연동으로 'omechu(http://omechu.com)' 에 웹사이트를 배포했습니다.
- MariaDB
- GitHub Action, AWS CodePipeline CI/CD
  - repository에 push가 발생 할 때마다 빌드와 배포를 자동화하여 개발 효율성을 높일 수 있도록 GitHub Action, AWS CodePipeline를 활용하였습니다

## API 설계 및 진행상황

### 로그인/회원가입 및 회원 정보

| Feature   | Request | API                                | 설명          | 체크  |
|-----------|---------|------------------------------------|-------------|-----|
| 회원 가입     |         |                                    | Oauth2 사용   | ☑️  |
| 회원 정보     | GET     | /api/user/user/{id}                | 회원 정보 조회    | ☑️  |
| 닉네임 중복 확인 | GET     | /api/user/checkNickname/{nickname} | 닉네임 중복 확인   | ☑️  |
| 추가 정보 기입  | PATCH   | /api/user/checkNickname/{userId}   | 추가 회원 정보 기입 | ☑️  |
| 회원 정보 수정  | PATCH   | /api/user/user/modify/{id}         | 회원 정보 수정    | ️   |
| 기타        | GET     | /, /health                         | AWS 헬스 체크   | ☑️  |



### 유튜브 컨텐츠 및 가게 정보

| Feature            | Request | API                    | 설명                 | 체크  |
|--------------------|---------|------------------------|--------------------|-----|
| 유튜브 컨텐츠 작성         | POST    | /api/admin/store       | 가게 정보 및 유튜브 컨텐츠 생성 | ☑️  |
| 유튜브 컨텐츠 수정         | PATCH   | /api/admin/store/{id}) | 가게 정보 및 유튜브 컨텐츠 수정 | ☑️  |
| 가게 정보 및 유튜브 컨텐츠 삭제 | DELETE  | /api/admin/store/{id}  | 가게 정보 및 유튜브 컨텐츠 삭제 | ☑️  |
| 특정 가게 조회           | GET     | /api/admin/store/{id}  | 특정 가게 조회           | ️☑️ |
| 특정 가게 조회           | GET     | /stores                | 모든 가게 조회           | ️☑️ |

### 좋아요

| Feature | Request | API                      | 설명           | 체크  |
|---------|---------|--------------------------|--------------|-----|
| 좋아요 추가  | POST    | /api/user/like/{storeId} | 특정 가게 좋아요 추가 | ☑️  |
| 좋아요 취소  | DELETE  | /api/user/like/{storeId} | 특정 가게 좋아요 취소 | ☑️  |

### 요청 게시판
| Feature       | Request | API                   | 설명               | 체크  |
|---------------|---------|-----------------------|------------------|-----|
| 요청 게시판 작성     | POST    | /api/user/request     | 요청 게시판 작성        | ☑️  |
| 요청 게시글 리스트 조회 | GET     | /requests             | 검색을 통한 요청 게시판 조회 | ️  |
| 특정 게시글 조회     | GET     | /api/user/request/{requestId} | 특정 게시글 조회        | ☑️  |
| 특정 게시글 삭제     | DELETE  | /api/user/request/{requestId} | 특정 가게 삭제         | ️☑️ |
| 특정 가게 수정      | PATCH   | /api/user/request/{requestId}               | 모든 가게 수정         | ️☑️ |

## ERD

![오메추 - ERD drawio](https://user-images.githubusercontent.com/78574530/187075405-342f3b89-6e34-4009-aea1-b5543fdb2381.png)
![오메추 - 클래스 다이어그램 drawio](https://user-images.githubusercontent.com/78574530/187074552-9266b7ed-1761-48b4-9d8a-66c8ea0d9bf1.png)

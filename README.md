# omechu-backend

Summary


## 프로젝트 전체 구조

## API 설계 및 진행상황

### 로그인/회원가입 및 회원 정보

| Feature  | Request | API                        | 설명        | 체크  |
|----------|---------|----------------------------|-----------|-----|
| 회원 가입    |         |                            | Oauth2 사용 | ☑️  |
| 회원 정보    | GET     | /api/user/user/{id}        | 회원 정보 조회  | ☑️  |
| 회원 정보 수정 | PATCH   | /api/user/user/modify/{id} | 회원 정보 수정  | ️   |
| 기타       | GET     | /, /health                 | AWS 헬스 체크 | ☑️  |



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


# 📌 주식 포트폴리오+커뮤니티 시스템

주식 종목별 **커뮤니티 게시판**과 **실시간 잔고 정보 조회** 기능을 통합 제공하는 **Spring Boot 기반 웹 서비스**입니다.  
사용자는 **종목코드 기반 커뮤니티**를 통해 정보를 공유하고,  
**한국투자증권 OpenAPI**를 활용하여 **본인의 주식 잔고 현황을 실시간으로 조회**할 수 있습니다.

---

## 📋 주요 기능 요약

| 기능 영역         | 상세 설명 |
|------------------|-----------|
| 게시판 기능       | 게시글 목록 조회, 등록, 수정, 삭제, 종목코드별 게시글 필터링 |
| 잔고 조회 기능    | 한국투자증권 OpenAPI를 통한 실시간 잔고 정보 조회 |
| API          | 종목코드 기반 게시글 JSON API (`/api/board/stockBoard/{stockCode}`) |
| 페이징 및 검색    | DTO 기반 검색 조건 + QueryDSL 적용 |

---

## 🛠 백엔드 구성 (Spring Boot + JPA + QueryDSL)


### 🔧 게시판 기능 상세

| 라우트 | 설명 |
|--------|------|
| GET `/board/list` | 페이징 + 검색 조건 기반 전체 게시글 목록 조회 |
| GET `/board/register` | 게시글 등록 폼 페이지 |
| POST `/board/register` | 게시글 저장 후 목록 또는 종목 상세로 이동 |
| GET `/board/read` | 게시글 상세 정보 조회 |
| POST `/board/modify` | 게시글 수정 처리 |
| POST `/board/remove` | 게시글 삭제 |
| GET `/api/board/stockBoard/{stockCode}` | 종목코드 기반 게시글 리스트 API |

- DTO: `BoardDTO`, `PageRequestDTO`, `PageResponseDTO`
- Service: `BoardService`, `BoardServiceImpl`
- Repository: `BoardRepository` + 사용자 정의 검색 `BoardSearchImpl(QueryDSL)`
- View: JSP 기반 템플릿 구성

---

### 💰 주식 잔고 정보 조회

| 라우트 | 설명 |
|--------|------|
| GET `/portfolio/totest` | 한국투자증권 API 호출을 통한 실시간 잔고 조회 |

- Controller: `PortfolioController`
- Service: `PortfolioServiceImpl`
- OpenAPI 통신: `RestTemplate.exchange()` 활용  
  → 헤더에 `appKey`, `appSecret`, `Authorization` 포함  
  → 응답 JSON → DTO(`BalanceResponseDTO`) 

---

### 📦 기술 스택 및 도구

| 항목 | 설명 |
|------|------|
| Spring Boot | 전체 백엔드 프레임워크 |
| JPA / QueryDSL | 게시판 검색 기능 |
| RestTemplate | 외부 API 호출 |
| Jackson | JSON 직렬화 및 역직렬화 |
| JSP | 템플릿 기반 뷰 처리 |
| 한국투자증권 OpenAPI | 잔고 조회 및 주식정보 제공 |

---

### 🌐 API 명세

#### ✅ 게시판

| 메서드 | URL | 설명 |
|--------|-----|------|
| GET | `/board/list` | 게시글 목록 조회 |
| GET | `/board/read?bno=1` | 게시글 상세 조회 |
| POST | `/board/register` | 게시글 등록 |
| POST | `/board/modify` | 게시글 수정 |
| POST | `/board/remove` | 게시글 삭제 |
| GET | `/api/board/stockBoard/{stockCode}` | 종목코드별 게시글 JSON 응답 |

#### ✅ 잔고 조회

| 메서드 | URL | 설명 |
|--------|-----|------|
| GET | `/portfolio/totest` | 잔고 데이터 JSON 응답 |

---

## 🌟 주요 특징

- **종목별 커뮤니티 기능**: 특정 주식 종목에 대해 사용자 간 자유롭게 의견을 공유
- **외부 API 연동**: 한국투자증권 OpenAPI를 통해 실제 사용자 계좌 정보를 조회
- **직관적인 UI 구성**: 게시판과 잔고 정보가 통합된 종목 상세 페이지 구현
- **검색 및 페이징 최적화**: QueryDSL을 통한 검색 조건 처리 및 페이지네이션 적용

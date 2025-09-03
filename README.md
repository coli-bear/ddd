# DDD 도메인 개발 연습하기 

> 참고 1 : [카카오페이 여신코어 DDD(Domain Driven Design, 도메인 설계 주도)로 구축하기](https://tech.kakaopay.com/post/backend-domain-driven-design/#step1---bounded-context--aggregate-root)<br/>
> 참고 2 : Chat GPT 5

## 학습 진행 플랜 

1. DDD 기본 개념 및 용어
   - DDD의 기본 개념과 원칙 이해
   - Bounded Context, Aggregate, Entity, Value Object 등 주요 용어 학습


2. 전술적 패턴
   - Repository, Factory, Service 등 전술적 패턴 이해
   - Domain Event, Domain Service 등 추가 개념
   - 각 패턴의 역할과 사용 사례 

3. 전략적 패턴
   - Context Mapping, Anti-Corruption Layer 등 전략적 패턴 이해
   - 시스템 간의 경계 설정과 통합 방법 학습


4. 유비쿼터스 언어 
   - 도메인 전문가와 개발자 간의 공통 언어 구축 방법 학습
   - 도메인 모델링과 유비쿼터스 언어의 중요성 이해


5. TDD 기반의 실전 프로젝트 
   - 요구사항 도출 및 유비쿼터스 언어 정의 (케이스 스터디) 
   - Bounded Context 식별
   - Context Mapping 설계 (전략적 패턴)
   - Aggregate 설계 
   - Repository, Factory, Service 등 적용 (전술적 패턴)

6. CQRS와 이벤트 소싱
   - CQRS(Command Query Responsibility Segregation) 개념 이해
   - 이벤트 소싱(Event Sourcing) 개념 이해
   - CQRS와 이벤트 소싱을 활용한 도메인 모델 설계 및 구현

## 학습 페이지 

1. [도메인 주도 설계(DDD) 개념 및 용어 정리](./docs/01.ddd-concepts.md)
2. [전술적 패턴](docs/02.ddd-tactical-patterns.md)
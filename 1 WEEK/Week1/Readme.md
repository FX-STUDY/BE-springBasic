# 회원 가입 및 조회 기능 구현 

---

## package member
### GradeType
- Member 클래스의 grade를 enum타입으로 사용

### Member
- member의 id, name, grade 를 getter setter함

### Product 
- product의 id, name, price, discountedPrice getter setter 함



---


## package repository
### MemberRepository
- save 와 findById 메소드 정의

### MemoryMemberRepository
- MemberRepository 인터페이스 구현
- 동시성 해결을 위해 Map 대신 ConcurrentHashMap 사용

        private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();

- public Member save() 에서 id 호출 시 1씩 증가하도록 함
- public Member findById() - memberId를 키 값으로 Member 반환

---

## package service
### MemberSerivce 
- member의 signUp과 findByName 구현
- private static final 을 사용해 외부에서의 접근과 재할당을 방지

        private static final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

### Main
- Main class 회원가입 후 고유 id 출력과 고유 id를 사용해 Member의 name을 출력하는 기능을 구현
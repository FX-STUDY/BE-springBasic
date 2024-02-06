# 10WEEK MISSION


*Student class*
```java
    private String stuName;
    private Long stuNum;
    private int stuGrade;
    private String stuMajort; //ENUM처리 고려
```

처음 타입을 모두 String으로 하였으나 MAP에서 KEY값을 지정하기 위해 stuNum타입을 Long으로 변경하였다.

따라서 학번을 기준으로 조회 및 삭제가 가능하다.


*StudentRepository*
```java

    Student save(Student stu);

    Student findByStuNum(Long stuNum);

    List<Student> findAll();

    void delteByStuNum(Long stuNum);

    void deleteAll();

    Student updateStu(Long stuNum, Student stu);

    int countStu();
```

`CRUD`를 작성 중 어려웠던 부분은 각 `메서드명`을 어떻게 지정하는지 결정하는 것이다. <br>
보편적으로 많이 사용하는 `JPA`의 `CRUD` `메서드명`을 참고하여 결정했다.


*StudentServiceTest*
```java
    @BeforeEach
    public void BeforeEach() {
        Student stu1 = new Student();
        stu1.setStuNum(2100000L);
        stu1.setStuName("PARK");
        stu1.setStuGrade(2);
        stu1.setStuMajort("SoftwareDept");
        studentService.signUp(stu1);

        Student stu2 = new Student();
        stu2.setStuNum(1900000L);
        stu2.setStuName("KIM");
        stu2.setStuGrade(4);
        stu2.setStuMajort("SoftwareDept");
        studentService.signUp(stu2);
    }

    @AfterEach
    public void afterEach() {
        studentService.deleteAllStudent();
    }
```

`@BeforeEach`를 활용하여 각 단위 테스트에서 필요한 `save` 중복 코드를 방지했다.
또한, `@AfterEach`를 이용하여 각 단위 테스트가 종료될 때 초기화를 수행함으로써
통합 테스트를 효과적으로 수행할 수 있게 구현하였다.

## Builder Pattern

빌더 패턴 장점 
- 필요한 데이터만 설정 가능
- 유연성 확보 가능
- 가독성이 높아짐
- 불변성 확보 가능

*Student*
```
@Builder
public class Student
```

*StudentServiceTest*
```java
    public void BeforeEach() {
        Student stu1 = Student.builder()
                .stuNum(2100000L)
                .stuName("PARK")
                .stuGrade(2)
                .stuMajor("SoftwareDept")
                .build();
        studentService.signUp(stu1);


        Student stu2 = Student.builder()
                .stuNum(1900000L)
                .stuName("KIM")
                .stuGrade(4)
                .stuMajor("SoftwareDept")
                .build();
        studentService.signUp(stu2);
    }
```

이전 코드보다 훨씬 직관적이다.







## Controller

### REST API

| METHOD | 정의                    | CRUD               |
|--------|-----------------------|--------------------|
| GET    | 리소스를 조회하고 정보를 가져온다    | READ               |
| POST   | 리소스를 생성한다             | CREATE             |
| PUT    | 해당 리소스를 수정한다.         | CREATE OR UPDATE   |
| PATCH  | 해당 리소스를 수정한다.(일부만 수정) | UPDATE             |
| DELETE | 해당 리소스를 삭제합니다.        | DELETE             |


*초기 데이터 - StudentManagementApplication*
```
    @PostConstruct
    public void post() {
        Student stu1 = Student.builder()
                .stuNum(2100000L)
                .stuName("PARK")
                .stuGrade(2)
                .stuMajor("SoftwareDept")
                .build();
        studentService.signUp(stu1);

        Student stu2 = Student.builder()
                .stuNum(1900000L)
                .stuName("KIM")
                .stuGrade(4)
                .stuMajor("SoftwareDept")
                .build();
        studentService.signUp(stu2);
    }
```
```json
    {
        "stuName": "PARK",
        "stuNum": 2100000,
        "stuGrade": 2,
        "stuMajor": "SoftwareDept"
    },
    {
        "stuName": "KIM",
        "stuNum": 1900000,
        "stuGrade": 4,
        "stuMajor": "SoftwareDept"
    }
```


## Postman Test

### 학생 등록 - POST http://localhost:8080/students

```java
    @PostMapping("/students") //학생 등록
    public List<Student> signUp(@RequestBody Student student) {
        studentService.signUp(student);
        return studentService.showAllStudent(); //학생 등록 확인을 위해 전체 출력
    }
```
입력데이터
```json
    {
        "stuName": "TEST",
        "stuNum": 1800000,
        "stuGrade": 1,
        "stuMajor": "ComputerDept"
    }
```

*확인*

![스크린샷 2024-01-24 152533.png](img%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-01-24%20152533.png)

- 초기데이터와 입력된 데이터 확인 가능


### 학생 단일 조회 - GET http://localhost:8080/students/2100000

```java
    @GetMapping("/students/{stuNum}") // 단일 학생 조회
    public Student showStudent(@PathVariable Long stuNum) {
        return studentService.showStudent(stuNum);
    }
```

![단일조회.png](img%2F%EB%8B%A8%EC%9D%BC%EC%A1%B0%ED%9A%8C.png)

- 학번 2100000의 데이터 정보 확인 가능



### 모든 학생 조회 - GET http://localhost:8080/students

```java
    @GetMapping("/students") // 모든 학생 조회
    public List<Student> showAllStudent() {
        return studentService.showAllStudent();
    }
```

![모든 학생 조회.png](img%2F%EB%AA%A8%EB%93%A0%20%ED%95%99%EC%83%9D%20%EC%A1%B0%ED%9A%8C.png)

- 초기 학생 데이터값 전체 확인 가능



### 단일 학생 삭제 - DELETE http://localhost:8080/students/2100000

```java
    @DeleteMapping("/students") // 다중 학생 삭제
    public List<Student> deleteAllStudent() {
        studentService.deleteAllStudent();
        return studentService.showAllStudent();
    }
```

![단일삭제.png](img%2F%EB%8B%A8%EC%9D%BC%EC%82%AD%EC%A0%9C.png)

- 학번 2100000 데이터가 제거됨

### 다중 학생 삭제 - DELETE http://localhost:8080/students

```java
   @DeleteMapping("/students") // 다중 학생 삭제
    public List<Student> deleteAllStudent() {
        studentService.deleteAllStudent();
        return studentService.showAllStudent();
    }
```

![다중 삭제.png](img%2F%EB%8B%A4%EC%A4%91%20%EC%82%AD%EC%A0%9C.png)

- 전체 학생 데이터가 제거됨

### 단일 학생 정보 수정 - Patch  http://localhost:8080/students/2100000

```java
    @PatchMapping("/students/{stuNum}") // 단일 학생 정보 수정
    public Student updateStudent(@PathVariable Long stuNum, @RequestBody Student stu) {
        studentService.updateStudent(stuNum, stu);
        return studentService.showStudent(stuNum);
    }
```

*전송 데이터*
```json
    {
        "stuName": "LEE",
        "stuGrade": 4
    }
```

*결과*
```json
{
    "stuName": "LEE",
    "stuNum": null,
    "stuGrade": 4,
    "stuMajor": null
}
```

- 지정하지 않은 데이터에서 `null`이 되었다. 

`StudentRepository 수정`
```java
    public Student updateStu(Long stuNum, Student stu) {
        Student existData = store.get(stuNum);
        if(stu.getStuNum() == null)
            stu.setStuNum(existData.getStuNum());
        if (stu.getStuName() == null)
            stu.setStuName(existData.getStuName());

        Integer stuGrade = stu.getStuGrade();
        if (stuGrade == null)
            stu.setStuGrade(existData.getStuGrade());

        if (stu.getStuMajor() == null)
            stu.setStuMajor(existData.getStuMajor());


        return store.replace(stuNum, stu);
    }
```

- `null`이 들어온 데이터에 대해서는 기존 데이터의 값을 새로운 데이터에 참조하게 수정했다.
- `stuGrade` 같은 경우 `int` 형이기에 `null`을 처리하지 못한다. 따라서 `Integer`로 형변환하였다.

![단일 학생 수정.png](img%2F%EB%8B%A8%EC%9D%BC%20%ED%95%99%EC%83%9D%20%EC%88%98%EC%A0%95.png)

- 학번 2100000의 데이터의 Name과 Grade만 변경된것을 확인할 수 있다.



### 총 학생 수 조회 - GET http://localhost:8080/students/count

```java
    @GetMapping("/students/count") // 총 학생 조회
    public int countStudent() {
        return studentService.countAllStudent();
    }
```

![총 학생 수.png](img%2F%EC%B4%9D%20%ED%95%99%EC%83%9D%20%EC%88%98.png)












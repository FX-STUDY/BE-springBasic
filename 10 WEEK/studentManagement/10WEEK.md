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








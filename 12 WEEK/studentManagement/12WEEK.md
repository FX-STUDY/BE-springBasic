# 12 WEEK


## Form 생성
- 기존 `Student` 로 통일했던 폼을 `EditStudentForm`과 `SignUpForm`으로 분리 작업 진행

*SignUpForm*
```java
public class SignUpForm {
//학번, 이름, 생년월일, 학과, 학기, 주소, 학년
    private Long studentNumber;
    private String studentName;
    private String studentBirth;
    private Major studentMajor;
    private int studentSemester;
    private String studentAddress;
    private int studentGrade;
}
```

*EditStudentForm*
```java
public class EditStudentForm {
    private String StudentName;
    private String StudentAddress;
}
```

*Student 필드 추가*
```java
public class Student {
//학번, 학년, 이름, 생년,생월,생일,학과, 학기,주소
    private Long studentNumber; //학번
    private int studentGrade;
    private String studentName;
    private int studentBirthYear;
    private int studentBirthMonth;
    private int studentBirthDay;
    private Major studentMajor;
    private int studentSemester;
    private String studentAddress;
}
```

## ENUM
- 학과 명칭은 ENUM으로 처리했다.
```java
public enum Major {
    SoftwareDept("소프트웨어학과"),
    ComputerEngineeringDept("컴퓨터공학과"),
    IndustrialDesignDept("산업디자인학과");
    private String dept;
    Major(String dept) {this.dept = dept;}
    public String getDept() {return dept;}
}
```
위와 같은 방식을 사용했지만, 한글로 요청이 들어오는 상황에서 SoftwareDept같이 영어로 지정해놓으면
자바에서 한글을 다시 영문으로 바꿔야하는 복잡한 문제가 있어 아래와 같이 변경하였다.

```java
public enum Major {
    소프트웨어학과,
    컴퓨터공학과,
    산업디자인학과
}
```


## 예외처리

*ResponseMessage*
```java
public enum ResponseMessage {
    NOT_FOUND_STUDENT("학생을 조회할 수 없습니다."),
    ALREADY_EXIST_STUDENT("이미 존재하는 학생입니다."),
    SUCCESS_REGISTERED_STUDENT("학생 등록 성공"),
    SUCCESS_READ_STUDENT("학생 정보 조회 성공"),
    SUCCESS_EDIT_STUDENT_INFO("학생 정보 수정 성공"),
    SUCCESS_DELETE_ALL_STUDENT("학생 정보 전체 삭제 성공"),
    SUCCESS_DELETE_STUDENT("학생 단일 정보 삭제 성공");
    private final String message;
    ResponseMessage(String message) {this.message = message;}
    public String getMessage() {return message;}
}
```
- 각종 예외 메시지를 담는다.

*DuplicateStudentException*
```java
public class DuplicateStudentException extends RuntimeException {
    public DuplicateStudentException() {}
    public DuplicateStudentException(String message) {super(message);}
    public DuplicateStudentException(String message, Throwable cause) {super(message, cause);}
    public DuplicateStudentException(Throwable cause) {super(cause);}
    public DuplicateStudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {super(message, cause, enableSuppression, writableStackTrace);}
}

```

*NotFoundStudentException*

```java
public class NotFoundStudentException extends RuntimeException {
    public NotFoundStudentException() {}
    public NotFoundStudentException(String message) {super(message);}
    public NotFoundStudentException(String message, Throwable cause) {super(message, cause);}
    public NotFoundStudentException(Throwable cause) {super(cause);}
    public NotFoundStudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {super(message, cause, enableSuppression, writableStackTrace);}
}
```

*ExceptionAdvice*
```java
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(DuplicateStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity duplicateStudentException(DuplicateStudentException e) {
        log.info("이미 존재하는 회원입니다.");
        return new ResponseEntity<>(ResponseMessage.ALREADY_EXIST_STUDENT.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity notFoundStudentException(NotFoundStudentException e) {
        log.info("학생을 조회할 수 없습니다.");
        return new ResponseEntity<>(ResponseMessage.NOT_FOUND_STUDENT.getMessage(), HttpStatus.NOT_FOUND);
    }
}
```
- API 호출하며 생기는 에러에 대한 공통 처리를 해주기 위해 `ExceptionAadvice`를 사용했다.


## 유효성검증

- Validation
```
1. 학번 
- 7자리, 숫자 ( 영문 , 특수 기호 금지 )
2. 이름
- 2자리 ~ 7자리 , 한글 ( 영문, 특수기호, 숫자 금지)
3. 생년월일
- format : YYYY_mm_dd
4. 학과 
- 소프트웨어, 컴퓨터공학, 산업디자인학과 외 금지
5. 학기
- 1학기 , 2학기 외 금지
6. 주소
 - null 허용
7. 학년
- 1 학년 ~ 5학년 외 금지
```

요구사항을 충족시키기 위해 다음과 같이 gradle 추가



*build.gradle*
```
	implementation 'org.springframework.boot:spring-boot-starter-validation'
```

*SignUpForm 유효성검증*

```java
public class SignUpForm {
//학번, 이름, 생년월일, 학과, 학기, 주소, 학년
    //https://coderanch.com/t/586580/java/validate-length-number
//    @Range(min=7, max=7, message = "- 7자리, 숫자 ( 영문 , 특수 기호 금지 )")
    private Long studentNumber;

    @Size(min =2, max = 7)
    @Pattern(regexp = "^[가-힣]*$", message = "- 2자리 ~ 7자리 , 한글 ( 영문, 특수기호, 숫자 금지)")
    private String studentName;

    @Pattern(regexp = "\\d{4}_\\d{2}_\\d{2}", message = "- format : YYYY_mm_dd")
    private String studentBirth;

    @ValidEnum(enumClass = Major.class, message = "- 소프트웨어, 컴퓨터공학, 산업디자인학과 외 금지")
    private Major studentMajor;

    @Digits(integer = 1, fraction = 0)
    @Min(value = 1) @Max(value = 2, message = "- 1학기 , 2학기 외 금지")
    private int studentSemester;

    // - null 허용
    private String studentAddress;

    @Digits(integer = 1, fraction = 0)
    @Min(value = 1) @Max(value = 5,message = "- 1 학년 ~ 5학년 외 금지")
    private int studentGrade;
}
```
- `Long Type`에 대한 유효성 검증을 어떻게 처리해야하는지 아직까지 모르겠다.......

*EditStudentForm*
```java
public class EditStudentForm {
    @Size(min =2, max = 7)
    @Pattern(regexp = "^[가-힣]*$", message = "- 2자리 ~ 7자리 , 한글 ( 영문, 특수기호, 숫자 금지)")
    private String StudentName;

    // - null 허용
    private String StudentAddress;
}

```

### ENUM 유효성 검증을 위한 EnumValidator

- `https://jsy1110.github.io/2022/enum-class-validation/` 참고


*EnumValidator*
```java
public class EnumValidator implements ConstraintValidator<ValidEnum, Enum> {
    private ValidEnum annotation;
    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }
    @Override
    public boolean isValid(Enum value, ConstraintValidatorContext context) {
        boolean result = false;
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (value == enumValue) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
```

*ValidEnum*
```java
@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnum {
    String message() default "Invalid value. This is not permitted.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends java.lang.Enum<?>> enumClass();
}
```

*적용*
```java
    @ValidEnum(enumClass = Major.class, message = "- 소프트웨어, 컴퓨터공학, 산업디자인학과 외 금지")
    private Major studentMajor;
```



## SignUp 학생등록 Refactoring

```java
    @PostMapping //학생 등록
    public ResponseEntity signUp(@Valid @RequestBody SignUpForm signUpForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        studentService.signUp(signUpForm);
        return new ResponseEntity<>(ResponseMessage.SUCCESS_REGISTERED_STUDENT.getMessage(), HttpStatus.OK); // 학생 등록 확인을 위해 전체 출력
    }
```
- 유효성 검증 오류가 발생되면 `ResponseEntity`로 오류 내역을 같이 응답으로 보낸다.

```java
    public void signUp(SignUpForm signUpForm) { //학생 입력

        if(studentRepository.findByStudentNumber(signUpForm.getStudentNumber()) != null)
            throw new DuplicateStudentException();

        Student student = saveStudent(signUpForm);

        studentRepository.save(student);
    }

```
- 학생이 존재하지 않으면 `DuplicateStudentException()`을 호출하여 오루메시지가 `ResponseEntity`로 출력된다.


```java
    private static Student saveStudent(SignUpForm signUpForm) {
        String[] studentBirth = signUpForm.getStudentBirth().split("_");
        Student student = Student.builder()
                .studentNumber(signUpForm.getStudentNumber())
                .studentGrade(signUpForm.getStudentGrade())
                .studentName(signUpForm.getStudentName())
                .studentBirthYear(Integer.parseInt(studentBirth[0]))
                .studentBirthMonth(Integer.parseInt(studentBirth[1]))
                .studentBirthDay(Integer.parseInt(studentBirth[2]))
                .studentMajor(signUpForm.getStudentMajor())
                .studentSemester(signUpForm.getStudentSemester())
                .studentAddress(signUpForm.getStudentAddress())
                .build();
        return student;
    }
```
- 실제로 DB에 저장되는 `builder`는 메서드를 따로 작성하였다.


## editStudentInformation 학생 정보 수정 Refactoring
```java
    @PatchMapping("/{studentNumber}") // 단일 학생 정보 수정
    public ResponseEntity editStudentInformation(@PathVariable Long studentNumber,@Valid @RequestBody EditStudentForm editStudentForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        Student updateStudent = studentService.editStudentInformation(studentNumber, editStudentForm);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }
```

```java
    public Student editStudentInformation(Long studentNumber, EditStudentForm editStudentForm) { //단일 학생 정보 수정
        Student existsStudent = studentRepository.findByStudentNumber(studentNumber);
        if(existsStudent == null)
            throw new NotFoundStudentException();

        Student student = editStudentInfo(editStudentForm, existsStudent);

        return studentRepository.updateStudent(studentNumber, student);
    }
```
학생이 존재하지 않으면 `NotFoundStudentException`을 호출하고, `ResponseEntity`로 응답한다.


```java
    private static Student editStudentInfo(EditStudentForm editStudentForm, Student existsStudent) {
        Student student = Student.builder()
                .studentNumber(existsStudent.getStudentNumber())
                .studentGrade(existsStudent.getStudentGrade())
                .studentName(editStudentForm.getStudentName())
                .studentBirthYear(existsStudent.getStudentBirthYear())
                .studentBirthMonth(existsStudent.getStudentBirthMonth())
                .studentBirthDay(existsStudent.getStudentBirthDay())
                .studentMajor(existsStudent.getStudentMajor())
                .studentSemester(existsStudent.getStudentSemester())
                .studentAddress(editStudentForm.getStudentAddress())
                .build();
        return student;
    }
```
- `editStudentForm`으로 학생의 정보(이름, 주소)를 받고 기존의 데이터에 덮어 씌우기 위해서 기존 학생 데이터에 `Form`으로 받은 
학생 정보를 참조하였다.



## POSTMAN
학생등록

![1등록.png](img%2F1%EB%93%B1%EB%A1%9D.png)

학생등록 유효성 검증

![1등록실패.png](img%2F1%EB%93%B1%EB%A1%9D%EC%8B%A4%ED%8C%A8.png)

학생 정보 수정

![1수정.png](img%2F1%EC%88%98%EC%A0%95.png)

학생 정보 수정 유효성 검증

![1수정실패.png](img%2F1%EC%88%98%EC%A0%95%EC%8B%A4%ED%8C%A8.png)

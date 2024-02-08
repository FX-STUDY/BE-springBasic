package fx.studentManagement.controller.form;

import fx.studentManagement.entity.enums.Major;
import fx.studentManagement.entity.enums.ValidEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {
//학번, 이름, 생년월일, 학과, 학기, 주소, 학년

    @Min(value = 1000000) @Max(value = 9999999, message = "- 7자리, 숫자 ( 영문 , 특수 기호 금지 )")
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

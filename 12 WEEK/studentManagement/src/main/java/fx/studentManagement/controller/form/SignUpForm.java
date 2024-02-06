package fx.studentManagement.controller.form;

import fx.studentManagement.entity.enums.Major;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class SignUpForm {
//학번, 이름, 생년월일, 학과, 학기, 주소, 학년
    private Long studentNumber;
    private String studentName;
    private LocalDate studentBirth; //yyyy-mm-dd
    private Major studentMajor;
    private int studentSemester;
    private String studentAddress;
    private int studentGrade;
}

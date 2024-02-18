package fx.studentManagement.entity;

/*
*   이름, 학번, 학년, 전공
*/

import fx.studentManagement.entity.enums.Major;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
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

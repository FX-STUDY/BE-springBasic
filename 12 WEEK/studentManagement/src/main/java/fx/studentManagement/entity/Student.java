package fx.studentManagement.entity;

/*
*   이름, 학번, 학년, 전공
*/

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Student {

    private String studentName;
    private Long studentNumber;
    private Integer studentGrade;
    private String studentMajor; //ENUM처리 고려

}

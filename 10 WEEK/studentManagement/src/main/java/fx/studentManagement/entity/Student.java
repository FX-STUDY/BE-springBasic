package fx.studentManagement.entity;

/*
*   이름, 학번, 학년, 전공
*/

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {

    private String stuName;
    private Long stuNum;
    private int stuGrade;
    private String stuMajort; //ENUM처리 고려

}

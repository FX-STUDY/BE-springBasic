package fx.studentManagement.entity;

/*
*   이름, 학번, 학년, 전공
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private String stuName;
    private String stuNum;
    private String stuGrade;
    private String stuMajort; //ENUM처리 고려

}

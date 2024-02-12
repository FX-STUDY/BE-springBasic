package student.student.validation;

import student.student.ENUM.Grade;
import student.student.ENUM.Major;
import student.student.ENUM.Semester;
import student.student.Student;

import java.util.regex.Pattern;

public class ValidateStudent {
    public boolean validateStudentId(Long studentId){
        if(studentId.SIZE != 7){
            return false;
        }
        if(!Pattern.matches("[0-9+]",String.valueOf(studentId))){
            return false;
        }

        return true;
    }

    public boolean validateStudentName(String studentName){
        if(!Pattern.matches("^[ㄱ-ㅎ가-힣]*$",studentName)){
            return false;
        }
        if(studentName.length() >= 2 && studentName.length() <= 7){
            return false;
        }
        return true;
    }

    public boolean validateMajor(Major studentMajor){
        //  [ 소프트웨어, 컴퓨터공학, 산업디자인학과 외 금지 ] 이 의도를 표현하고 싶었으나 이렇게 하는건 아닌것 같은데..
        if (studentMajor != Major.산업디자인학과 || studentMajor != Major.컴퓨터공학과 || studentMajor != Major.소프트웨어학과){
            return false;
        }
        return true;
    }

    public boolean validateSemester(Semester studentSemester){
        // 위와 동일,,
        if(studentSemester != Semester.first || studentSemester != Semester.second){
            return false;
        }
        return true;
    }

}

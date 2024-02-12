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

}

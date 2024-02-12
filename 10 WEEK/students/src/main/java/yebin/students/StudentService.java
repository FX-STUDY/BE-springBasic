package yebin.students;

import java.util.List;

public interface StudentService {
    public void join(Students student);
    public Students showStudentInForById(Long studentId);
    public void deleteStudentInFortById(Long studentId);
    public void updateStudentInForById(Long studentId, Students updateStudent);
    public boolean multiDeleteStudentInForById(List<Long> studentId);
}

package yebin.students;

import java.util.List;

public interface StudentService {
    public void join(Students student);
    public Students findStudent(Long studentId);
    public void deleteStudent(Long studentId);
    public void updateStudent(Long studentId, Students updateStudent);
    public void multiDeleteStudent(List<Long> studentId);
}

package fx.studentManagement.repository;

import fx.studentManagement.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepository {

    private static Map<Long, Student> store = new ConcurrentHashMap<>();

    public Student save(Student student) {
        store.put(student.getStudentNumber(), student);
        return student;
    }

    public Student findByStudentNumber(Long studentNumber) {
        return store.get(studentNumber);
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delteByStudentNumber(Long studentNumber) {
        store.remove(studentNumber);
    }

    public void deleteAll() {
        store.clear();
    }

    public Student updateStu(Long stuNum, Student stu) {
        Student existData = store.get(stuNum);
        if(stu.getStuNum() == null)
            stu.setStuNum(existData.getStuNum());
        if (stu.getStuName() == null)
            stu.setStuName(existData.getStuName());

        Integer stuGrade = stu.getStuGrade();
        if (stuGrade == null)
            stu.setStuGrade(existData.getStuGrade());

        if (stu.getStuMajor() == null)
            stu.setStuMajor(existData.getStuMajor());


        return store.replace(stuNum, stu);
    }

    public int countStu() {
        return store.size();
    }
}

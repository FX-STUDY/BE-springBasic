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

    public Student updateStudent(Student student) {
        return store.replace(student.getStudentNumber(), student);
    }

    public int countStudent() {
        return store.size();
    }

    public Boolean existsStudent(Long studentNumber) {
        return store.get(studentNumber) != null;
    }
}

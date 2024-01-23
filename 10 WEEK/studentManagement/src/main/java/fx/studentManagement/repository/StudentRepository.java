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

    public Student save(Student stu) {
        store.put(stu.getStuNum(), stu);
        return stu;
    }

    public Student findByStuNum(Long stuNum) {
        return store.get(stuNum);
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delteByStuNum(Long stuNum) {
        store.remove(stuNum);
    }

    public void deleteAll() {
        store.clear();
    }

    public Student updateStu(Long stuNum, Student stu) {
        return store.replace(stuNum, stu);
    }

    public int countStu() {
        return store.size();
    }
}

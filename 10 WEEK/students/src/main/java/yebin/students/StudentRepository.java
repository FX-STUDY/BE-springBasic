package yebin.students;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    private static final Map<Long, Students> store = new HashMap<>();
    private static long SEQUENCE = 0L;

    public Students save(Students student){
        student.setId(++SEQUENCE);
        store.put(student.getId(), student);
        return student;
    }

    public Students findById(Long id){
        return store.get(id);
    }


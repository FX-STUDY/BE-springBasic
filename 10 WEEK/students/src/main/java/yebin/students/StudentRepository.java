package yebin.students;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    private static final Map<Long, Students> store = new HashMap<>();


    public Students save(Students student){
        store.put(student.getId(), student);
        return student;
    }

    public Students findById(Long id){
        return store.get(id);
    }

    public List<Students> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Students updateStudent){
        //DB 에서 회원 정보를 찾는다 [ 이미 별도 메서드로 구분 되어 있음 ] 추상화 레벨 2
        Students findStudent = store.get(id);
        //Entity 정보를 수정한다. : 추상화 레벨 2
        findStudent.setName(updateStudent.getName());
        findStudent.setGrade(updateStudent.getGrade());
        findStudent.setMajor(updateStudent.getMajor());
        //DB 에 새로운 정보를 저장한다. : [ 이미 별도 메서드로 구분 되어 있음 ] 추상화 레벨 2
        save(findStudent);
    }


    public void delete(Long id){
        store.remove(id);
    }

    public void clearStore(){
        store.clear();
    }
}

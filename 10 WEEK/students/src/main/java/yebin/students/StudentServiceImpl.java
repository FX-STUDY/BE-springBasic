package yebin.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    @Override
    public void join(Students student) {
        studentRepository.save(student);
    }

    @Override
    public Students findStudent(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.delete(studentId);
    }

    @Override
    public void updateStudent(Long studentId, Students updateStudent) {
        studentRepository.update(studentId, updateStudent);
    }

    @Override
    public void multiDeleteStudent(List<Long> studentId){
        for (Long id : studentId) {
            studentRepository.delete(id);
        }
    }
}

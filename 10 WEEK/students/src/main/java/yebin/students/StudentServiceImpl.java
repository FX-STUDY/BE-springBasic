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
    public Students showStudentInForById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public void deleteStudentInFortById(Long studentId) {
        studentRepository.delete(studentId);
    }

    @Override
    public void updateStudentInForById(Long studentId, Students updateStudent) {
        studentRepository.update(studentId, updateStudent);
    }

    @Override
    public boolean multiDeleteStudentInForById(List<Long> studentId){
        for (Long id : studentId) {
            studentRepository.delete(id);
            return true;
        }
        return false;
    }
}

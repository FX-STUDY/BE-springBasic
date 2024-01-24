package fx.studentManagement.controller;

import fx.studentManagement.entity.Student;
import fx.studentManagement.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping("/students") //학생 등록
    public List<Student> signUp(@RequestBody Student student) {
        studentService.signUp(student);
        return studentService.showAllStudent(); //학생 등록 확인을 위해 전체 출력
    }

    @GetMapping("/students/{stuNum}") // 단일 학생 조회
    public Student showStudent(@PathVariable Long stuNum) {
        return studentService.showStudent(stuNum);
    }

    @GetMapping("/students") // 모든 학생 조회
    public List<Student> showAllStudent() {
        return studentService.showAllStudent();
    }

    @DeleteMapping("/students/{stuNum}") // 단일 학생 삭제
    public List<Student> deleteStudent(@PathVariable Long stuNum) {
        studentService.deleteStudent(stuNum);
        return studentService.showAllStudent(); //확인을 위해 전체 출력
    }

    @DeleteMapping("/students") // 다중 학생 삭제
    public List<Student> deleteAllStudent() {
        studentService.deleteAllStudent();
        return studentService.showAllStudent();
    }

    @PatchMapping("/students/{stuNum}") // 단일 학생 정보 수정
    public Student updateStudent(@PathVariable Long stuNum, @RequestBody Student stu) {
        studentService.updateStudent(stuNum, stu);
        return studentService.showStudent(stuNum);
    }

    @GetMapping("/students/count") // 총 학생 조회
    public int countStudent() {
        return studentService.countAllStudent();
    }

    @PostConstruct
    public void post() {
        Student stu1 = Student.builder()
                .stuNum(2100000L)
                .stuName("PARK")
                .stuGrade(2)
                .stuMajor("SoftwareDept")
                .build();
        studentService.signUp(stu1);


        Student stu2 = Student.builder()
                .stuNum(1900000L)
                .stuName("KIM")
                .stuGrade(4)
                .stuMajor("SoftwareDept")
                .build();
        studentService.signUp(stu2);
    }
}

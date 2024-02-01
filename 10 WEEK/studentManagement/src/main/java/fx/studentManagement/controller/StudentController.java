package fx.studentManagement.controller;

import fx.studentManagement.entity.Student;
import fx.studentManagement.service.StudentService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @PostMapping("/students") //학생 등록
    public ResponseEntity signUp(@RequestBody Student student) {
        try {
            studentService.signUp(student);
            return new ResponseEntity<>("학생 등록 성공", HttpStatus.OK); // 학생 등록 확인을 위해 전체 출력
        } catch (Exception e) {
            return new ResponseEntity<>("이미 존재하는 학생입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students/{studentNumber}") // 단일 학생 조회
    public ResponseEntity showStudent(@PathVariable Long studentNumber) {
        try {
            return new ResponseEntity<>(studentService.showStudent(studentNumber), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("존재하지 않는 학생입니다.", HttpStatus.BAD_REQUEST);
        }
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

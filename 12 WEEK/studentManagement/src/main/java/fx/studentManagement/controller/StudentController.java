package fx.studentManagement.controller;

import fx.studentManagement.controller.form.EditStudentForm;
import fx.studentManagement.controller.form.SignUpForm;
import fx.studentManagement.entity.Student;
import fx.studentManagement.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;


    @PostMapping //학생 등록
    public ResponseEntity signUp(@Valid @RequestBody SignUpForm signUpForm) {
        try {
            studentService.signUp(signUpForm);
            return new ResponseEntity<>("학생 등록 성공", HttpStatus.OK); // 학생 등록 확인을 위해 전체 출력
        } catch (Exception e) {
            return new ResponseEntity<>("이미 존재하는 학생입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{studentNumber}") // 단일 학생 조회
    public ResponseEntity showStudent(@PathVariable Long studentNumber) {
        try {
            return new ResponseEntity<>(studentService.showStudent(studentNumber), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("존재하지 않는 학생입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping // 모든 학생 조회
    public ResponseEntity showAllStudent() {
        try {
            return new ResponseEntity<>(studentService.showAllStudent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("존재하는 학생이 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{studentNumber}") // 단일 학생 삭제
    public ResponseEntity deleteStudent(@PathVariable Long studentNumber) {
        try {
            studentService.deleteStudent(studentNumber);
            return new ResponseEntity<>("학번 : " + studentNumber + " 정보 삭제 성공", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("존재하지 않는 학생입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping // 다중 학생 삭제
    public ResponseEntity deleteAllStudent() {
        studentService.deleteAllStudent();
        return new ResponseEntity<>("학생 전체 정보가 삭제되었습니다.", HttpStatus.OK);
    }

    @PatchMapping("/{studentNumber}") // 단일 학생 정보 수정
    public ResponseEntity updateStudent(@PathVariable Long studentNumber, @RequestBody EditStudentForm editStudentForm) {
        try{
            Student updateStudent = studentService.updateStudent(studentNumber, editStudentForm);
            return new ResponseEntity<>(updateStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("학생 정보 수정에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/count") // 총 학생 조회
    public int countStudent() {
        return studentService.countAllStudent();
    }

    @PostConstruct
    public void post() {
        Student stu1 = Student.builder()
                .studentNumber(2100000L)
                .studentName("PARK")
                .studentGrade(2)
                .studentMajor("SoftwareDept")
                .build();
        studentService.signUp(stu1);


        Student stu2 = Student.builder()
                .studentNumber(1900000L)
                .studentName("KIM")
                .studentGrade(4)
                .studentMajor("SoftwareDept")
                .build();
        studentService.signUp(stu2);
    }
}

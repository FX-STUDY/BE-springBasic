package fx.studentManagement.controller;

import fx.studentManagement.common.ResponseMessage;
import fx.studentManagement.controller.form.EditStudentForm;
import fx.studentManagement.controller.form.SignUpForm;
import fx.studentManagement.entity.Student;
import fx.studentManagement.service.StudentService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;


    @PostMapping //학생 등록
    public ResponseEntity signUp(@Valid @RequestBody SignUpForm signUpForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        studentService.signUp(signUpForm);
        return new ResponseEntity<>(ResponseMessage.SUCCESS_REGISTERED_STUDENT.getMessage(), HttpStatus.OK); // 학생 등록 확인을 위해 전체 출력
    }

    @GetMapping("/{studentNumber}") // 단일 학생 조회
    public ResponseEntity showStudent(@PathVariable Long studentNumber) {
        return new ResponseEntity<>(studentService.showStudent(studentNumber), HttpStatus.OK);
    }

    @GetMapping // 모든 학생 조회
    public ResponseEntity showAllStudent() {
        return new ResponseEntity<>(studentService.showAllStudent(), HttpStatus.OK);
    }

    @DeleteMapping("/{studentNumber}") // 단일 학생 삭제
    public ResponseEntity deleteStudent(@PathVariable Long studentNumber) {
        studentService.deleteStudent(studentNumber);
        return new ResponseEntity<>(studentNumber + ResponseMessage.SUCCESS_DELETE_STUDENT.getMessage(), HttpStatus.OK);
    }

    @DeleteMapping // 다중 학생 삭제
    public ResponseEntity deleteAllStudent() {
        studentService.deleteAllStudent();
        return new ResponseEntity<>(ResponseMessage.SUCCESS_DELETE_ALL_STUDENT.getMessage(), HttpStatus.OK);
    }

    @PatchMapping("/{studentNumber}") // 단일 학생 정보 수정
    public ResponseEntity editStudentInformation(@PathVariable Long studentNumber,@Valid @RequestBody EditStudentForm editStudentForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        Student updateStudent = studentService.editStudentInformation(studentNumber, editStudentForm);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
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

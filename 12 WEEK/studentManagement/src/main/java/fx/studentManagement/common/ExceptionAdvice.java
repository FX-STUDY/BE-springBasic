package fx.studentManagement.common;

import fx.studentManagement.common.exception.DuplicateStudentException;
import fx.studentManagement.common.exception.NotFoundStudentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DuplicateStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity duplicateStudentException(DuplicateStudentException e) {
        log.info("이미 존재하는 회원입니다.");
        return new ResponseEntity<>(ResponseMessage.ALREADY_EXIST_STUDENT.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity notFoundStudentException(NotFoundStudentException e) {
        log.info("학생을 조회할 수 없습니다.");
        return new ResponseEntity<>(ResponseMessage.NOT_FOUND_STUDENT.getMessage(), HttpStatus.NOT_FOUND);
    }

}

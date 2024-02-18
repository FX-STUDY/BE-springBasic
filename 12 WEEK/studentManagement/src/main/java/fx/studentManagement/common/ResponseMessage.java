package fx.studentManagement.common;

public enum ResponseMessage {
    NOT_FOUND_STUDENT("학생을 조회할 수 없습니다."),
    ALREADY_EXIST_STUDENT("이미 존재하는 학생입니다."),
    SUCCESS_REGISTERED_STUDENT("학생 등록 성공"),
    SUCCESS_READ_STUDENT("학생 정보 조회 성공"),
    SUCCESS_EDIT_STUDENT_INFO("학생 정보 수정 성공"),
    SUCCESS_DELETE_ALL_STUDENT("학생 정보 전체 삭제 성공"),
    SUCCESS_DELETE_STUDENT("학생 단일 정보 삭제 성공");
    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

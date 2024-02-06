package fx.studentManagement.common;

public enum ResponseMessage {
    NOT_FOUND_STUDENT("학생을 조회할 수 없습니다."),
    ALREADY_EXIST_STUDENT("이미 존재하는 학생입니다.");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

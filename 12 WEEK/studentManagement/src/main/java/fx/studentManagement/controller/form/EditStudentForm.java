package fx.studentManagement.controller.form;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditStudentForm {

    @Size(min =2, max = 7)
    @Pattern(regexp = "^[가-힣]*$", message = "- 2자리 ~ 7자리 , 한글 ( 영문, 특수기호, 숫자 금지)")
    private String StudentName;

    // - null 허용
    private String StudentAddress;
}

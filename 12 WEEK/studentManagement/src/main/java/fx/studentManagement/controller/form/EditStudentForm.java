package fx.studentManagement.controller.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditStudentForm {

    @Min(value = 1000000) @Max(value = 9999999, message = "- 7자리, 숫자 ( 영문 , 특수 기호 금지 )")
    private Long studentNumber;

    @Size(min =2, max = 7)
    @Pattern(regexp = "^[가-힣]*$", message = "- 2자리 ~ 7자리 , 한글 ( 영문, 특수기호, 숫자 금지)")
    private String studentName;

    // - null 허용
    private String studentAddress;
}

package FinalProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class InsuranceTypeForm {
    @NonNull
    @Size(max = 255, min = 10, message = "Минимальная длина 10")
    private String type;
    private String code;
    private long id;
    private boolean status;
    private String errorMessage;
}

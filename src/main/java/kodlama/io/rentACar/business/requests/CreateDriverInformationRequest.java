package kodlama.io.rentACar.business.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateDriverInformationRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String surName;
    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String tckmlkNo;

    @Min(value = 22)//22 yaşından küçük araba kiralayamaz
    private int yas;
    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String telNo;
    @Email
    private String email;
    private int carId;

}

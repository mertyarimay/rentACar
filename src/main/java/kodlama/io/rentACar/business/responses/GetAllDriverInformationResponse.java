package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class GetAllDriverInformationResponse {

    private int id;
    private String name;
    private String surName;
    private String tckmlkNo;
    private int yas;
    private String telNo;
    private String email;
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private String modelName;

}

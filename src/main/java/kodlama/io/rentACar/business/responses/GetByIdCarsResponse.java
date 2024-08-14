package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetByIdCarsResponse {
    private int id;
    private String plate;
    private double dailyPrice;
    private int modelYear;
    private String state;
    private String modelName;
    private String brandName;
}

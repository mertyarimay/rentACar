package kodlama.io.rentACar.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetAllModelsResponse {
    private int id;
    private String name;
    private String brandName;  //model entitynin içinde brand name yok ama dönücez burda model entitiysinin içinde brand olduğu için branda  gidip name alıcak


}

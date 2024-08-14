package kodlama.io.rentACar.business.responses;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllResponseBrands {  //son kullanıcıya neleri verimek istiyorum

    private int id;
    private String name;



}

package kodlama.io.rentACar.coreCommon.unitilities.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashMap;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ValidationProblemsDetails extends ProblemDetails{
    private Map<String,String> validationErors;//hangi alanda ne hatası var bunun içine koyucaz çünkü hangi fieldda ne hatası var bunu frontendçi gelen datayı rahat yerleştirsin diye yapılıyor
//Yani fieldlarda ki kullanılıcak harf sınırlaması veya benzeri hatalar için
}

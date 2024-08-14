package kodlama.io.rentACar;

import kodlama.io.rentACar.coreCommon.unitilities.exceptions.BusinessException;
import kodlama.io.rentACar.coreCommon.unitilities.exceptions.ProblemDetails;
import kodlama.io.rentACar.coreCommon.unitilities.exceptions.ValidationProblemsDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@RestControllerAdvice  //exception handlerın çalışabilmesi için ekledik
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	@Bean
 public ModelMapper getModelMapper(){
		return new ModelMapper();
 }




 @ExceptionHandler //hata yanıtlarını sağlamak için kullanılır response statuste http durum kodunu otomatik olarak döndürmeyi sağlar
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)  //HATA MESEJI VERDİĞİNDE BENİM BÜTÜN YAPIMI VERMESİN DİYE KULLANILIYOR
     public ProblemDetails handleBusinessException(BusinessException businessException){  //handle diye verdiğimiz metot ismi bir anlamı yok istediğimizi verebiliyoruz işi yapan exception handler
          //business exception alırsan çalış
	 ProblemDetails problemDetails=new ProblemDetails();
	 problemDetails.setMessage(businessException.getMessage());  //problem detailsen mesajı o an oluşan hatanın mesajıdır
         return problemDetails;

 }
	@ExceptionHandler
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){   //giridiğimiz harf sınırlaması düşük olduğunda veya yüksek olduğunda benim bütün yapımı vermesin diye konuldu

		ValidationProblemsDetails validationProblemsDetails=new ValidationProblemsDetails();
		validationProblemsDetails.setMessage("VALİDATİON EXCEPTİON");
		validationProblemsDetails.setValidationErors(new HashMap<String,String>());
		// gelen hataları tek tek listeye koyucaz
		//String[]meyveler=new String[]{çilek,portakal,elma} //foreach yapısı için yazıldıA
		//for(String meyve:meyveler){
		//system.out.println(meyve)

		for(FieldError fieldError:methodArgumentNotValidException.getBindingResult().getFieldErrors()){//gelen hatalar method argument leri yani  tek tek dolaşıyorum tek tek dolaştığım elemanda field erorr oluyor
             validationProblemsDetails.getValidationErors().put(fieldError.getField(),fieldError.getDefaultMessage());
		}
		return validationProblemsDetails;





	}
}

package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.coreCommon.unitilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service  //enjekte ederek kullanıcağım için bunu anatationu koymam gerekiyor

public class BrandBusinessRules {  // iş kurallarını yazdığım yer
    private BrandRepository brandRepository;   //Marka isminin tekrarına bakarken brand repositorye ihtiyacım var
    public void checkIfBrandNameExists(String name){ //marka ismi tekrar ediyormu diye kontrol ediyoruz
  if(brandRepository.existsByName(name)){
      throw new BusinessException("Aynı isimde kayıt yapılamaz");//buna business dememizin sebebi hata ordan gelicek
  }



    }

}

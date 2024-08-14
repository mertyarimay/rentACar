package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.coreCommon.unitilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service

public class ModelBusinessRules {
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    public void checkIfModelNameExists(String name){
        if (modelRepository.existsByName(name)){
            throw new BusinessException("Aynı isimde kayıt yapılamaz");
        }
    }
    public void checkBrandId(int brandId){
     Brand brand=brandRepository.findById(brandId).orElse(null);
     if (brand==null){
         throw new BusinessException("Bu ıdye Ait Marka Kaydı Yoktur");

     }
    }
}

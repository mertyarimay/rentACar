package kodlama.io.rentACar.business.rules;

import kodlama.io.rentACar.coreCommon.unitilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Car;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service

public class CarBusinessRules {
    private CarRepository carRepository;
    private ModelRepository modelRepository;

    public void checkIfCarPlateExists(String plate) {
        if (carRepository.existsByPlate(plate)) {
            throw new BusinessException("Aynı plaka kodunda  kayıt atılamaz");
        }
    }
    public void checkModelId(int modelId){
        Model model=modelRepository.findById(modelId).orElse(null);
        if(model==null){
            throw new BusinessException("Bu Id ye Ait Model Mevcut Değildir");
        }
    }




    }


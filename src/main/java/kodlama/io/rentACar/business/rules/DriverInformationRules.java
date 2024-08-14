package kodlama.io.rentACar.business.rules;



import jakarta.transaction.Transactional;
import kodlama.io.rentACar.coreCommon.unitilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.dataAccess.abstracts.DriverInformationRepository;
import kodlama.io.rentACar.entities.concretes.Car;
import kodlama.io.rentACar.entities.concretes.DriverInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor

public class DriverInformationRules {
    private DriverInformationRepository driverInformationRepository;
    private  CarRepository carRepository;




         //Aynı tc ile kayıt atmama durumu
    public void checkIfDriverTckmlkNoExists(String tckmlkNo){
        if (driverInformationRepository.existsByTckmlkNo(tckmlkNo)){
            throw new BusinessException("Aynı TC Kimlilk No ile kayıt yapılamaz");
        }
    }





    //Car ıd ye göre car ın statusu pasifse kiralayamama durumu

        public  DriverInformation saveDriverWithActiveCar(DriverInformation driverInformation) {
        int carId = driverInformation.getCar().getId();
        if (carRepository.findPassiveDriverByCarId(carId).isPresent()){
            throw new BusinessException("Araba aktif değil Kiralayamazssınız");
        }else
          return driverInformation;
    }


    //Aracın pasife dönmesi
    @Transactional
     public void carPasif (DriverInformation driverInformation){
        int carId=driverInformation.getCar().getId();
        carRepository.updateCarState(carId,"PASİF");

     }

     //Aracın aktife dönmesi

     @Transactional
     public void carAktif(DriverInformation driverInformation){
        int carId=driverInformation.getCar().getId();
        carRepository.deleteCarState(carId,"AKTİF");
     }

     public void checkCarId(int carId){
         Car car=carRepository.findById(carId).orElse(null);
         if (car==null){
             throw new BusinessException("Bu Id Ye Ait Araç Mevcut Değildir");
         }
     }

}






















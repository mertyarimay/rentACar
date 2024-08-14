package kodlama.io.rentACar.business.rules;

import jakarta.transaction.Transactional;
import kodlama.io.rentACar.coreCommon.unitilities.exceptions.BusinessException;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.dataAccess.abstracts.DriverInformationDiscountRepository;
import kodlama.io.rentACar.entities.concretes.Car;
import kodlama.io.rentACar.entities.concretes.DriverInformationDiscount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DriverInformationDiscountRules {
    private CarRepository carRepository;
    private DriverInformationDiscountRepository driverInformationDiscountRepository;


     //Aynı tc ile kayıt atamama durumu
    public void checkIfDriverTckmlkNoExists(String tckmlkNo){
        if (driverInformationDiscountRepository.existsByTckmlkNo(tckmlkNo)){
            throw new BusinessException("Aynı TC Kimlilk No ile kayıt yapılamaz");
        }
    }

     //İndirim kodu sorgulama
    public void code(String code){
        if(!code.equals("indirim")){
            throw new BusinessException("Kodunuz geçersiz");
        }
    }

     //Araba aktifse kiralayamam durumu
    public DriverInformationDiscount saveDriverWithActiveCar(DriverInformationDiscount driverInformationDiscount) {

        int carId = driverInformationDiscount.getCar().getId();
        if (carRepository.findPassiveDriverByCarId(carId).isPresent()){
            throw new BusinessException("Araba aktif değil Kiralayamazssınız");
        }else
            return driverInformationDiscount;
    }

     //Aracın Pasife Dönmesi
    @Transactional
    public void carPasif (DriverInformationDiscount driverInformationDiscount){
        int carId=driverInformationDiscount.getCar().getId();
        carRepository.updateCarState(carId,"PASİF");

    }

       //fiyat heesaplama ayrı kod
 /*   public ResponseEntity<Object> fiyatHesaplama(int carId){
        Optional<Car> car=carRepository.findById(carId);
        if (car.isPresent()){
            double orginalPrice=car.get().getDailyPrice();
            double discountPrice=orginalPrice*0.9;
            return ResponseEntity.ok("İNDİRİMLİ FİYATINIZ:"+" "+discountPrice+" "+"Kiralama işleminiz başarıyla gerçekleşmiştir");

        }else {
            return   ResponseEntity.status(HttpStatus.NOT_FOUND).body("HATALI İŞLEM YAPILDI");
        }
    }*/



        //İndirimli araçlar için fiyat hesaplama
    public double fiyatHesaplama(int carId){
        Optional<Car>car=carRepository.findById(carId);
        if(car.isPresent());
        double orginalPrice=car.get().getDailyPrice();
        double discountPrice=orginalPrice*0.9;
        return discountPrice;
    }
    public String indirimliFiyatHesaplama(int carId){
      String ilkCumle="İndirimli Fiyatınız:";
      String ikinciCumle="Kiralama İşleminiz Başarılı Bir Şekilde Gerçekleşmiştir";
      return ilkCumle+" "+fiyatHesaplama(carId)+" "+ikinciCumle;
    }


       //Aracın Aktife Dönmesi
    @Transactional
    public void carAktif(DriverInformationDiscount driverInformationDiscount){
        int carId=driverInformationDiscount.getCar().getId();
        carRepository.deleteCarState(carId,"AKTİF");
    }

    public void checkCarId(int carId){
        Car car=carRepository.findById(carId).orElse(null);
        if (car==null){
            throw new BusinessException("Bu Id Ye Ait Araç Mevcut değildir");
        }
    }


}

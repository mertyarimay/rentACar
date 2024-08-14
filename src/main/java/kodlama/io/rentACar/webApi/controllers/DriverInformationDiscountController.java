package kodlama.io.rentACar.webApi.controllers;

import jakarta.validation.Valid;
import kodlama.io.rentACar.business.abstracts.DriverInformationDiscountService;
import kodlama.io.rentACar.business.requests.CreateDriverInformationDiscountRequest;

import kodlama.io.rentACar.business.responses.GetAllDriverInformationDiscountResponse;
import kodlama.io.rentACar.business.responses.GetByIdDriverInformationDiscountResponse;
import kodlama.io.rentACar.business.rules.DriverInformationDiscountRules;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver/discount")
@AllArgsConstructor
public class DriverInformationDiscountController {
    private DriverInformationDiscountService driverInformationDiscountService;
    private DriverInformationDiscountRules driverInformationDiscountRules;


    @PostMapping("/add")
    public ResponseEntity<Object>create(@RequestBody @Valid CreateDriverInformationDiscountRequest createDriverInformationDiscountRequest){
        CreateDriverInformationDiscountRequest createDriverInformationDiscountRequest1=driverInformationDiscountService.create(createDriverInformationDiscountRequest);
        if(createDriverInformationDiscountRequest1!=null){
            return ResponseEntity.ok(driverInformationDiscountRules.indirimliFiyatHesaplama(createDriverInformationDiscountRequest1.getCarId()));

        }else {
          return   ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kiralama işleminiz başarısız");
        }
    }

    @GetMapping("getAll")
    public List<GetAllDriverInformationDiscountResponse>getAll(){
        List<GetAllDriverInformationDiscountResponse>getAllDriverInformationDiscountResponses=driverInformationDiscountService.getAll();
        return getAllDriverInformationDiscountResponses;
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id")int id){
        GetByIdDriverInformationDiscountResponse getByIdDriverInformationDiscountResponse=driverInformationDiscountService.getById(id);
        if (getByIdDriverInformationDiscountResponse!=null){
            return ResponseEntity.ok(getByIdDriverInformationDiscountResponse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu idye ait bir sürücü kaydınız yoktur");
        }
    }



    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id") int id){
      Boolean delete=driverInformationDiscountService.delete(id);
      if (delete==true){
          return ResponseEntity.ok("Sürücü aracı teslim etti kaydı silindi araç aktif hale dönüştürüldü");
        }
      else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BU ıd ye ait sürücü kaydınız yoktur silme işlemi başarısızdır");
      }

    }



}

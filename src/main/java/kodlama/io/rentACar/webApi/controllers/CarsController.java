package kodlama.io.rentACar.webApi.controllers;

import jakarta.validation.Valid;
import kodlama.io.rentACar.business.abstracts.CarService;
import kodlama.io.rentACar.business.requests.CreateCarRequest;
import kodlama.io.rentACar.business.requests.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.GetByIdCarsResponse;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor

public class CarsController {
    private CarService carService;

    @PostMapping("/add")
        public CreateCarRequest create(@RequestBody @Valid CreateCarRequest createCarRequest){
        CreateCarRequest carRequest=carService.create(createCarRequest);
        return carRequest;
    }
    @GetMapping
    public List<GetAllCarsResponse> getAll(@RequestParam Optional<Integer> modelId){
        List <GetAllCarsResponse> getAllCarsResponse=carService.getAll(modelId);
        return getAllCarsResponse;
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id") int id){
        GetByIdCarsResponse getByIdCarsResponse=carService.getById(id);
        if(getByIdCarsResponse!=null){
            return ResponseEntity.ok(getByIdCarsResponse);
        }
       else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BÖYLE BİR IDLİ CAR KAYDINIZ YOKTUR");
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable ("id") int id,@RequestBody  UpdateCarRequest updateCarRequest){
        UpdateCarRequest carRequest=carService.update(id,updateCarRequest);
        if(carRequest!=null){
            return ResponseEntity.ok("Güncelleme İşlemi Başarılı Bir Şekilde Gerçekleşti");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Güncelleme İşlemi BAŞARISIZ");
        }
    }


    @DeleteMapping("/remove/{id}")
    public  ResponseEntity<Object> remove(@PathVariable("id") int id){
        Boolean delete=carService.remove(id);
        if (delete==true){
           return ResponseEntity.ok("Car Kaydınız Başarılı Bir Şekilde Silindi");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu IDye ait car kaydı olmadığı için silme işlemi başarısız oldu");
        }

    }



}


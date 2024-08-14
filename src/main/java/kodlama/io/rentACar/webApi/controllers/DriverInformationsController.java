package kodlama.io.rentACar.webApi.controllers;

import jakarta.validation.Valid;
import kodlama.io.rentACar.business.abstracts.DriverInformationService;
import kodlama.io.rentACar.business.requests.CreateDriverInformationRequest;
import kodlama.io.rentACar.business.responses.GetAllDriverInformationResponse;
import kodlama.io.rentACar.business.responses.GetByIdDriverInformationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")

@AllArgsConstructor

public class DriverInformationsController {
    private DriverInformationService driverInformationService;

    @PostMapping("/add")
    public ResponseEntity<Object>create(@RequestBody @Valid CreateDriverInformationRequest createDriverInformationRequest){
        CreateDriverInformationRequest request= driverInformationService.create(createDriverInformationRequest);
        if (request!=null){
            return ResponseEntity.ok("Araba kiralama işlemeniz başarılı bir şekilde gerçekleşti iyi sürüşler");
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("kiralama işlemi başarısız");
    }

    @GetMapping("/getAll")
    public List<GetAllDriverInformationResponse> getAll(){
        List<GetAllDriverInformationResponse> getAllDriverInformationResponse=driverInformationService.getALL();
        return getAllDriverInformationResponse;
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Object>getById(@PathVariable("id")int id){
        GetByIdDriverInformationResponse getByIdDriverInformationResponse=driverInformationService.getById(id);
        if(getByIdDriverInformationResponse!=null){
            return ResponseEntity.ok(getByIdDriverInformationResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bu idye ait bir sürücü kaydınız yoktur");
        }

    }

    @DeleteMapping("/remove/{id}")
    public  ResponseEntity<Object> delete(@PathVariable ("id") int id){
        Boolean delete=driverInformationService.delete(id);
        if(delete==true){
           return ResponseEntity.ok("Sürücü aracı teslim etti kaydı silindi araç aktif hale dönüştürüldü.");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BU ıd ye ait sürücü kaydınız yoktur silme işlemi başarısızdır");

        }



    }



}

package kodlama.io.rentACar.webApi.controllers;

import jakarta.validation.Valid;
import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetByIdModelsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor

public class ModelsController {
    private ModelService modelService;


    @PostMapping("/add")
    public void create(@RequestBody @Valid CreateModelRequest createModelRequest){
        modelService.add(createModelRequest);
    }


  /*  @GetMapping("/getAll")
    public List<GetAllModelsResponse>getALL(){
        List<GetAllModelsResponse>getAllModelsResponses=modelService.getAll();
        return getAllModelsResponses;
    }*/

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id")int id){
        GetByIdModelsResponse getByIdModelsResponse=modelService.getById(id);
        if(getByIdModelsResponse!=null){
            return ResponseEntity.ok(getByIdModelsResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BU ID YE AİT BİR MODEL YOK.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object>update(@PathVariable("id") int id, @RequestBody @Valid UpdateModelRequest updateModelRequest){
    UpdateModelRequest modelRequest= modelService.update(id,updateModelRequest );
    if(modelRequest!=null){
     return ResponseEntity.ok("Güncelleme İşlemi Başarılı Bir Şekilde Gerçekleşti");
    }else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Güncelleme İşlemi Başarısız Girdiğiniz Id Mevcut Değil...");

    }

    }


    @DeleteMapping("/remove/{id}")
        public ResponseEntity<Object> remove(@PathVariable ("id") int id){
         Boolean delete=modelService.remove(id);
         if (delete==true){
             return ResponseEntity.ok("BAŞARILI BİR ŞEKİLDE SİLİNDİ KAYDINIZ");
         }else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BÖYLE BİR IDYE AİT KAYDINIZ YOK SİLME İŞLEMİ BAŞARISIZ");
         }


        }

        @GetMapping
     public List<GetAllModelsResponse>getAll(@RequestParam Optional<Integer>brandId){
            List<GetAllModelsResponse> getAllModelsResponses=modelService.getAll(brandId);
            return getAllModelsResponses;
    }




}

package kodlama.io.rentACar.webApi.controllers;

import jakarta.validation.Valid;
import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequsest;
import kodlama.io.rentACar.business.responses.GetAllResponseBrands;
import kodlama.io.rentACar.business.responses.GetByIdResponseBrand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")


@AllArgsConstructor
public class BrandsController {
    private BrandService brandService;

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateBrandRequest createBrandRequest) { //bana bir request ver @valid requestte  koyduğumuz harf kuralları vs çalışssın diye kullanılıyor
        brandService.add(createBrandRequest);//bende bunu service gönderim
    }

    @GetMapping("/getAll")
    public List<GetAllResponseBrands>getAll(){
        return brandService.getAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") int id){
        GetByIdResponseBrand getByIdResponseBrand=brandService.getById(id);
        if (getByIdResponseBrand!=null){
            return ResponseEntity.ok(getByIdResponseBrand);
        }
        else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Böyle bir Id ye Ait Marka Kaydı mevcut değildir");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object>update(@PathVariable("id") int id,@RequestBody  @Valid UpdateBrandRequsest updateBrandRequsest){
       UpdateBrandRequsest brandRequsest= brandService.update(id,updateBrandRequsest);
       if(brandRequsest!=null){
        return  ResponseEntity.ok("Güncelleme işlemi Başarılı Bir Şekilde Gerçekleşti ");
       }
       else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Güncelleme İşlemi BAŞARISIZ Id Bulunamadı");
       }
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") int id){
       Boolean delete=brandService.delete(id);
       if(delete==true){
         return   ResponseEntity.ok("KAYIT BAŞARILI BİR ŞEKİLDE SİLİNDİ");
       }else {
        return   ResponseEntity.status(HttpStatus.NOT_FOUND).body("BU IDYE AİT KAYIT OLMADIĞI İÇİN SİLME İŞLEMİ BAŞARISIZ OLDU");
       }
    }












}

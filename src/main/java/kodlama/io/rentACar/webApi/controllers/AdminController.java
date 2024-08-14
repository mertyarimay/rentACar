package kodlama.io.rentACar.webApi.controllers;

import kodlama.io.rentACar.business.abstracts.AdminService;
import kodlama.io.rentACar.business.requests.AdminRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@NoArgsConstructor
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<Object> getAll(@RequestBody AdminRequest adminRequest){
        List<GetAllCarsResponse>getAllCarsResponses=adminService.getAll(adminRequest);
        if(adminRequest.getKullaniciAdi().equals("admin")&&adminRequest.getSifre().equals("admin")){
        return ResponseEntity.ok(getAllCarsResponses);

    }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ŞİFRENİZ YADA KULLANICI ADINIZ YANLIŞ TEKRAR DENEYİNİZ");
        }

        }


}

package kodlama.io.rentACar.webApi.controllers;

import kodlama.io.rentACar.business.abstracts.CarRentalService;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/carRentals")
@AllArgsConstructor

public class CarRentalController {
   private CarRentalService carRentalService;

    @GetMapping("/getAll")
    public List<GetAllCarsResponse>getAll(){
        List<GetAllCarsResponse> getAllCarsResponse=carRentalService.getAll();
        return getAllCarsResponse;
    }
}

package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.AdminService;
import kodlama.io.rentACar.business.requests.AdminRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.coreCommon.unitilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AdminManager implements AdminService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCarsResponse> getAll(AdminRequest adminRequest) {
        List<Car>cars=carRepository.findAll();
        List<GetAllCarsResponse>getAllCarsResponses=cars.stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car,GetAllCarsResponse.class)).collect(Collectors.toList());
        return getAllCarsResponses;



    }
}

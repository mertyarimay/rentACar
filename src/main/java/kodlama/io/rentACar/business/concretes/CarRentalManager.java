package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.CarRentalService;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.coreCommon.unitilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarRentalManager implements CarRentalService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetAllCarsResponse> getAll() {
       List<Car>cars=carRepository.findAll();
        List<GetAllCarsResponse>getAllCarsResponses= cars.stream()
                .filter(car -> car.getState().contains("AKTİF"))
                .map(car -> modelMapperService.forResponse().
                        map(car,GetAllCarsResponse.class)).collect(Collectors.toList());

       return getAllCarsResponses;






    }
}

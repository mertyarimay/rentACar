package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.CarService;
import kodlama.io.rentACar.business.requests.CreateCarRequest;
import kodlama.io.rentACar.business.requests.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.GetByIdCarsResponse;
import kodlama.io.rentACar.business.rules.CarBusinessRules;
import kodlama.io.rentACar.business.rules.ModelBusinessRules;
import kodlama.io.rentACar.coreCommon.unitilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;


    @Override
    public CreateCarRequest create(CreateCarRequest createCarRequest) {
        carBusinessRules.checkIfCarPlateExists(createCarRequest.getPlate());
        carBusinessRules.checkModelId(createCarRequest.getModelId());
        Car car=modelMapperService.forRequest().map(createCarRequest,Car.class);
        carRepository.save(car);
        CreateCarRequest carRequest=modelMapperService.forRequest().map(car,CreateCarRequest.class);
        return carRequest;
    }

    @Override
    public List<GetAllCarsResponse> getAll(Optional<Integer> modelId) {
    if (modelId.isPresent()){
        List<Car>cars=carRepository.findByModelId(modelId.get());
        List<GetAllCarsResponse>getAllCarsResponses=cars.stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, GetAllCarsResponse.class)).collect(Collectors.toList());
        return getAllCarsResponses;

    }else {
        List<Car>cars=carRepository.findAll();
      List<GetAllCarsResponse>getAllCarsResponse=cars.stream()
                .map(car -> modelMapperService.forResponse().map(car,GetAllCarsResponse.class))
                .collect(Collectors.toList());
        return getAllCarsResponse;
    }


    }

    @Override
    public GetByIdCarsResponse getById(int id) {
      Optional<Car>car=carRepository.findById(id);
      if(car.isPresent()){
          GetByIdCarsResponse getByIdCarsResponse=modelMapperService.forResponse().map(car,GetByIdCarsResponse.class);
          return getByIdCarsResponse;
      }
      else {
          return null;
      }
    }

    @Override
    public UpdateCarRequest update(int id, UpdateCarRequest updateCarRequest) {
        Optional<Car>car=carRepository.findById(id);
        if(car.isPresent()){
            car.get().setDailyPrice(updateCarRequest.getDailyPrice());
            car.get().setState(updateCarRequest.getState());
            carRepository.save(car.get());
            UpdateCarRequest carRequest=modelMapperService.forRequest().map(car,UpdateCarRequest.class);
            return carRequest;
        }
        else{
            return  null;
        }
    }

    @Override
    public Boolean remove(int id) {
        Optional<Car>car=carRepository.findById(id);
        if (car.isPresent()){
            carRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}

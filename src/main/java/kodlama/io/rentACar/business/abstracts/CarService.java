package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.requests.CreateCarRequest;
import kodlama.io.rentACar.business.requests.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.GetByIdCarsResponse;

import java.util.List;
import java.util.Optional;

public interface CarService {
    CreateCarRequest create(CreateCarRequest createCarRequest);
    List<GetAllCarsResponse>getAll(Optional<Integer> modelId);
    GetByIdCarsResponse getById(int id);
    UpdateCarRequest update(int id,UpdateCarRequest updateCarRequest);
    Boolean remove(int id);
}

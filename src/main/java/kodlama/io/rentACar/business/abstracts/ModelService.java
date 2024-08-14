package kodlama.io.rentACar.business.abstracts;


import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetByIdModelsResponse;



import java.util.List;
import java.util.Optional;

public interface ModelService {

    void add(CreateModelRequest createModelRequest);
    //List<GetAllModelsResponse> getAll();
    GetByIdModelsResponse getById(int id);
    UpdateModelRequest update(int id , UpdateModelRequest updateModelRequest);
    Boolean remove(int id);
    List<GetAllModelsResponse> getAll(Optional<Integer> brandId);

}

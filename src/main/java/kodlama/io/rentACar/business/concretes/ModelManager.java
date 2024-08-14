package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.requests.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.GetByIdModelsResponse;
import kodlama.io.rentACar.business.rules.ModelBusinessRules;
import kodlama.io.rentACar.coreCommon.unitilities.exceptions.BusinessException;
import kodlama.io.rentACar.coreCommon.unitilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;
    private BrandRepository brandRepository;



    @Override
    public void add(CreateModelRequest createModelRequest) {
        modelBusinessRules.checkIfModelNameExists(createModelRequest.getName());
        modelBusinessRules.checkBrandId(createModelRequest.getBrandId());
        Model model=modelMapperService.forRequest().map(createModelRequest,Model.class);
        modelRepository.save(model);
    }
 /*   @Override
    public List<GetAllModelsResponse> getAll() {

        List<Model>models=modelRepository.findAll();
        List<GetAllModelsResponse>modelsResponse=models.stream()
                .map(model ->modelMapperService.forResponse()
                        .map(model,GetAllModelsResponse.class)).collect(Collectors.toList());
        return modelsResponse;
    }*/

    @Override
    public GetByIdModelsResponse getById(int id) {
        Optional<Model>model=modelRepository.findById(id);
        if(model.isPresent()){
            GetByIdModelsResponse getByIdModelsResponse=modelMapperService.forResponse().map(model,GetByIdModelsResponse.class);
            return getByIdModelsResponse;
        }
        else {
            return null;
        }
    }

    @Override
    public UpdateModelRequest update(int id, UpdateModelRequest updateModelRequest) {
        Optional<Model>model=modelRepository.findById(id);
        if(model.isPresent()){
            model.get().setName(updateModelRequest.getName());
            Brand brand=brandRepository.findById(updateModelRequest.getBrandId()).orElse(null);
            if(brand==null){
                throw new BusinessException("Girdiğiniz Markaya Ait Kayıt Bulunamadı");
            }else {
                model.get().setBrand(brand);
            }
            UpdateModelRequest modelRequest=modelMapperService.forRequest().map(modelRepository.save(model.get()),UpdateModelRequest.class);
            return modelRequest;
        }
        else {
            return null;
        }

    }

    @Override
    public Boolean remove(int id) {
        Optional<Model>model=modelRepository.findById(id);
        if(model.isPresent()){
            modelRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public List<GetAllModelsResponse> getAll(Optional<Integer> brandId) {
        if (brandId.isPresent()){
    List<Model>models=modelRepository.findByBrandId(brandId.get());
    List<GetAllModelsResponse>getAllModelsResponses=models.stream()
            .map(model -> modelMapperService.forResponse()
                    .map(model,GetAllModelsResponse.class)).collect(Collectors.toList());
    return getAllModelsResponses;
        }
        else {
            List<Model>models=modelRepository.findAll();
            List<GetAllModelsResponse>getAllModelsResponses=models.stream()
                    .map(model -> modelMapperService.forResponse()
                            .map(model,GetAllModelsResponse.class)).collect(Collectors.toList());
            return  getAllModelsResponses;
        }

    }


}

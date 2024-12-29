package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequsest;
import kodlama.io.rentACar.business.responses.GetAllResponseBrands;
import kodlama.io.rentACar.business.responses.GetByIdResponseBrand;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.coreCommon.unitilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;


    @Override
    public CreateBrandRequest add(CreateBrandRequest createBrandRequest) {
        brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
        Brand brand=modelMapperService.forRequest().map(createBrandRequest,Brand.class);
        brandRepository.save(brand);
        CreateBrandRequest brandRequest=modelMapperService.forRequest().map(brand,CreateBrandRequest.class);
        return brandRequest;
    }


    @Override
    public List<GetAllResponseBrands> getAll() {
        List<Brand>brands=brandRepository.findAll();
        List<GetAllResponseBrands>getAllResponseBrands=brands.stream().map(brand -> modelMapperService.forResponse().map(brand, GetAllResponseBrands.class)).collect(Collectors.toList());
        return getAllResponseBrands;
    }


    //model mappersız kullanımı
  /*  @Override
    public List<GetAllResponseBrands> getAll() {
        List<Brand>brands=brandRepository.findAll();
        List<GetAllResponseBrands>getAllResponseBrands=new ArrayList<>();
        for (Brand brand:brands){
            GetAllResponseBrands responseBrand=new GetAllResponseBrands();
            responseBrand.setId(brand.getId());
            responseBrand.setName(brand.getName());
            getAllResponseBrands.add(responseBrand);
        }
        return getAllResponseBrands;

    }*/

    @Override
    public GetByIdResponseBrand getById(int id) {
       Optional<Brand>brand=brandRepository.findById(id);
       if (brand.isPresent()){
           GetByIdResponseBrand getByIdResponseBrand=modelMapperService.forResponse().map(brand.get(),GetByIdResponseBrand.class);
           return getByIdResponseBrand;
       }else {
           return null;
       }
    }

    @Override
    public UpdateBrandRequsest update(int id,UpdateBrandRequsest updateBrandRequsest) {
        brandBusinessRules.checkIfBrandNameExists(updateBrandRequsest.getName());
        Optional<Brand>brand=brandRepository.findById(id);
        if(brand.isPresent()){
            brand.get().setName(updateBrandRequsest.getName());
            UpdateBrandRequsest brandRequest=modelMapperService.forRequest().map(brandRepository.save(brand.get()),UpdateBrandRequsest.class);
           return brandRequest;
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean delete(int id) {
     Optional<Brand>brand=brandRepository.findById(id);
     if (brand.isPresent()){
         brandRepository.deleteById(id);
         return true;
        }else{
         return false;
        }

    }

}

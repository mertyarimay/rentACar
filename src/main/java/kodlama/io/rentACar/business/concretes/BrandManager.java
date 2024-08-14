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
    public List<GetAllResponseBrands> getAll() { //burda mapping yapmamız lazım çünkü entity dekileri response atmam lazım ordaki ıd leri ve nameleri

                                 //model mapperle kullanımı

        List<Brand>brands=brandRepository.findAll();//repositeryden aldığımı brands instance atıyorum
        List<GetAllResponseBrands>brandsResponse=brands.stream()
                .map(brand ->modelMapperService.forResponse()
                        .map(brand,GetAllResponseBrands.class)).collect(Collectors.toList());
          return brandsResponse;
    }




                               //model mappersız kullanımı

    //  List<Brand>brands=brandRepository.findAll();//repositeryden aldığımı brands instance atıyorum
    //List<GetAllResponseBrands>brandsResponse=new ArrayList<GetAllResponseBrands>();//RESPONSE ADI ALTINDA BOŞ BİR LİSTE OLUŞTURUYORUZ
    // for (Brand brand:brands) {//tüm brandleri dolaşıp
    //     GetAllResponseBrands resposeItem=new GetAllResponseBrands(); //her dolaştığımda bir tane eleman oluşturuyorum
    //      resposeItem.setId(brand.getId());//response ıtemın ıd si listede gezdiğim bradın ıd si
    //      resposeItem.setName(brand.getName());//responseıtemin name listede gezdiğim brandın name dir

    //     brandsResponse.add(resposeItem);//sonuç olarak listemize ekliyoruz
    // }
    //return brandsResponse;



                    //model mapperle kullanımı
    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());//kuralı buraya ekliyoruz nerden alıyoruz requstimin name inden
     Brand brand=modelMapperService.forRequest().map(createBrandRequest,Brand.class);
     brandRepository.save(brand);



                    //MODEL MAPPERSIZ KULLANIMI
      //  Brand brand=new Brand();  //bir brand oluşturuyoruz
       // brand.setName(createBrandRequest.getName());//bu brandın name createn gelen name dir  repository kayıt etmek için entitye atıyoruz
       // this.brandRepository.save(brand);
    }




    @Override
    public GetByIdResponseBrand getById(int id) {
       Optional<Brand>brand=brandRepository.findById(id);
       if (brand.isPresent()){
           GetByIdResponseBrand getByIdResponseBrand=modelMapperService.forResponse().map(brand,GetByIdResponseBrand.class);
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
            UpdateBrandRequsest brandRequsest=modelMapperService.forRequest().map(brandRepository.save(brand.get()),UpdateBrandRequsest.class);
           return brandRequsest;
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

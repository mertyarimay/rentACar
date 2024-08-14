package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequsest;
import kodlama.io.rentACar.business.responses.GetAllResponseBrands;
import kodlama.io.rentACar.business.responses.GetByIdResponseBrand;


import java.util.List;

public interface BrandService {
    List<GetAllResponseBrands>getAll();
    GetByIdResponseBrand getById(int id);
    void add(CreateBrandRequest createBrandRequest);
    UpdateBrandRequsest update(int id,UpdateBrandRequsest updateBrandRequsest);
    Boolean delete(int id);

}

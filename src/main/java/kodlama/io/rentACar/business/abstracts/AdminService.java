package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.requests.AdminRequest;
import kodlama.io.rentACar.business.responses.GetAllCarsResponse;

import java.util.List;

public interface AdminService {
    List<GetAllCarsResponse>getAll(AdminRequest adminRequest);
}

package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.responses.GetAllCarsResponse;

import java.util.List;

public interface CarRentalService {
    List<GetAllCarsResponse>getAll();
}

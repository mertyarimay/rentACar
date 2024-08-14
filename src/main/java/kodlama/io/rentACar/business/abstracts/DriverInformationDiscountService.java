package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.requests.CreateDriverInformationDiscountRequest;
import kodlama.io.rentACar.business.responses.GetAllDriverInformationDiscountResponse;
import kodlama.io.rentACar.business.responses.GetByIdDriverInformationDiscountResponse;


import java.util.List;

public interface DriverInformationDiscountService {
    CreateDriverInformationDiscountRequest create(CreateDriverInformationDiscountRequest createDriverInformationDiscountRequest);
    List<GetAllDriverInformationDiscountResponse>getAll();
    GetByIdDriverInformationDiscountResponse getById(int id);
    Boolean delete(int id);

}

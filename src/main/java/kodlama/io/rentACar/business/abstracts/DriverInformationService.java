package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.requests.CreateDriverInformationRequest;
import kodlama.io.rentACar.business.responses.GetAllDriverInformationResponse;
import kodlama.io.rentACar.business.responses.GetByIdDriverInformationResponse;

import java.util.List;

public interface DriverInformationService {
    CreateDriverInformationRequest create(CreateDriverInformationRequest createDriverInformationRequest);
    List<GetAllDriverInformationResponse>getALL();
    GetByIdDriverInformationResponse getById(int id);
    Boolean delete(int id);
}

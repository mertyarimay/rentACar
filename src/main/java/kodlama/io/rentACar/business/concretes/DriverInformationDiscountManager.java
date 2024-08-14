package kodlama.io.rentACar.business.concretes;

import kodlama.io.rentACar.business.abstracts.DriverInformationDiscountService;
import kodlama.io.rentACar.business.requests.CreateDriverInformationDiscountRequest;
import kodlama.io.rentACar.business.responses.GetAllDriverInformationDiscountResponse;
import kodlama.io.rentACar.business.responses.GetByIdDriverInformationDiscountResponse;
import kodlama.io.rentACar.business.rules.DriverInformationDiscountRules;
import kodlama.io.rentACar.business.rules.DriverInformationRules;
import kodlama.io.rentACar.coreCommon.unitilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.DriverInformationDiscountRepository;
import kodlama.io.rentACar.entities.concretes.DriverInformationDiscount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverInformationDiscountManager implements DriverInformationDiscountService {
    private DriverInformationDiscountRepository driverInformationDiscountRepository;
    private DriverInformationDiscountRules driverInformationDiscountRules;
    private DriverInformationRules driverInformationRules;
    private ModelMapperService modelMapperService;


    @Override
    public CreateDriverInformationDiscountRequest create(CreateDriverInformationDiscountRequest createDriverInformationDiscountRequest) {
        driverInformationDiscountRules.checkCarId(createDriverInformationDiscountRequest.getCarId());
        driverInformationDiscountRules.checkIfDriverTckmlkNoExists(createDriverInformationDiscountRequest.getTckmlkNo());
        driverInformationRules.checkIfDriverTckmlkNoExists(createDriverInformationDiscountRequest.getTckmlkNo());
        driverInformationDiscountRules.code(createDriverInformationDiscountRequest.getCode());
        DriverInformationDiscount driverInformationDiscount = modelMapperService.forRequest().map(createDriverInformationDiscountRequest, DriverInformationDiscount.class);
        driverInformationDiscountRules.saveDriverWithActiveCar(driverInformationDiscount);
        driverInformationDiscountRepository.save(driverInformationDiscount);
        driverInformationDiscountRules.carPasif(driverInformationDiscount);
        CreateDriverInformationDiscountRequest createDriverInformationDiscountRequest1 = modelMapperService.forRequest().map(driverInformationDiscount, CreateDriverInformationDiscountRequest.class);
        return createDriverInformationDiscountRequest1;

    }


    @Override
    public List<GetAllDriverInformationDiscountResponse> getAll() {
        List<DriverInformationDiscount> driverInformationDiscounts = driverInformationDiscountRepository.findAll();
        List<GetAllDriverInformationDiscountResponse> getAllDriverInformationDiscountResponses = driverInformationDiscounts.stream()
                .map(driverInformationDiscount -> modelMapperService.forResponse()
                        .map(driverInformationDiscount, GetAllDriverInformationDiscountResponse.class))
                .collect(Collectors.toList());
        return getAllDriverInformationDiscountResponses;
    }


    @Override
    public GetByIdDriverInformationDiscountResponse getById(int id) {
        DriverInformationDiscount driverInformationDiscount=driverInformationDiscountRepository.findById(id).orElse(null);
        if (driverInformationDiscount!=null){
            GetByIdDriverInformationDiscountResponse getByIdDriverInformationDiscountResponse=modelMapperService.forResponse().map(driverInformationDiscount,GetByIdDriverInformationDiscountResponse.class);
            return getByIdDriverInformationDiscountResponse;
        }
        return null;
    }




    @Override
    public Boolean delete(int id) {
    DriverInformationDiscount driverInformationDiscount=driverInformationDiscountRepository.findById(id).orElse(null);
    if (driverInformationDiscount!=null){
        driverInformationDiscountRepository.deleteById(id);
        driverInformationDiscountRules.carAktif(driverInformationDiscount);
        return true;
    }else {
        return false;
    }

    }
}
package kodlama.io.rentACar.business.concretes;

import jakarta.transaction.Transactional;
import kodlama.io.rentACar.business.abstracts.DriverInformationService;
import kodlama.io.rentACar.business.requests.CreateDriverInformationRequest;
import kodlama.io.rentACar.business.responses.GetAllDriverInformationResponse;
import kodlama.io.rentACar.business.responses.GetByIdDriverInformationResponse;
import kodlama.io.rentACar.business.rules.DriverInformationDiscountRules;
import kodlama.io.rentACar.business.rules.DriverInformationRules;
import kodlama.io.rentACar.coreCommon.unitilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.dataAccess.abstracts.DriverInformationRepository;

import kodlama.io.rentACar.entities.concretes.DriverInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class DriverInformationManager implements DriverInformationService {
    private DriverInformationRepository driverInformationRepository;
    private ModelMapperService modelMapperService;
    private DriverInformationRules driverInformationRules;
    private CarRepository carRepository;
    private DriverInformationDiscountRules driverInformationDiscountRules;


    @Override
    public CreateDriverInformationRequest create(CreateDriverInformationRequest createDriverInformationRequest) {
        driverInformationRules.checkCarId(createDriverInformationRequest.getCarId());
        driverInformationRules.checkIfDriverTckmlkNoExists(createDriverInformationRequest.getTckmlkNo());
        driverInformationDiscountRules.checkIfDriverTckmlkNoExists(createDriverInformationRequest.getTckmlkNo());
        DriverInformation driverInformation=modelMapperService.forRequest().map(createDriverInformationRequest,DriverInformation.class);
        driverInformationRules.saveDriverWithActiveCar(driverInformation);
        driverInformationRepository.save(driverInformation);
        driverInformationRules.carPasif(driverInformation);
        CreateDriverInformationRequest driverInformationRequest=modelMapperService.forRequest().map(driverInformation,CreateDriverInformationRequest.class);
        return driverInformationRequest;
    }

    @Override
    public List<GetAllDriverInformationResponse> getALL() {
        List<DriverInformation> driverInformations=driverInformationRepository.findAll();
        List<GetAllDriverInformationResponse> getAllDriverInformationResponse=driverInformations.stream()
                .map(driverInformation -> modelMapperService.forResponse()
                        .map(driverInformation,GetAllDriverInformationResponse.class))
                .collect(Collectors.toList());
        return getAllDriverInformationResponse;

    }



    @Override
    public GetByIdDriverInformationResponse getById(int id) {
        Optional<DriverInformation>driverInformation=driverInformationRepository.findById(id);
        if(driverInformation.isPresent()){
            GetByIdDriverInformationResponse getByIdDriverInformationResponse=modelMapperService.forResponse().map(driverInformation,GetByIdDriverInformationResponse.class);
            return getByIdDriverInformationResponse;
        }else {
            return null;
        }
    }

    @Transactional
    @Override
    public Boolean delete(int id) {
        DriverInformation driverInformation=driverInformationRepository.findById(id).orElse(null);
        if(driverInformation!=null){
            driverInformationRules.carAktif(driverInformation);
            driverInformationRepository.delete(driverInformation);
            return true;
        }
        else {
            return false;
        }













    }
}

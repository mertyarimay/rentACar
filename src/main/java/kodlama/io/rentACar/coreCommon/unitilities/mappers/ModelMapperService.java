package kodlama.io.rentACar.coreCommon.unitilities.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponse();   //response nesneleri için
    ModelMapper forRequest();  //request nesneleri için
}

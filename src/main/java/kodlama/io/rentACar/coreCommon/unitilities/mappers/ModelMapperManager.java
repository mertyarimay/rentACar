package kodlama.io.rentACar.coreCommon.unitilities.mappers;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service //Her seferinde bir model mapper üretilsin istemiyoruz bunun ıoc yerleşmesini istiyorum bu yüzden kullanılıyor
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService{
    private ModelMapper modelMapper;  //BUNU EKLEMEK İÇİN APPLİCATİON SINIFINA NEW Lİ EKLENDİ YOKSA CONSTRUCTORU OLUŞTURAMIYORUZ IOC BULAMIYOR ÇÜNKÜ MODEL MAPPER SINIFINDA SERVİCE ANATASYONU YOK NESNE ÜRETEMİYOR BİZ ONU APLLİCATİON SINIFINDA YAPIYORUZ
    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
        .setAmbiguityIgnored(true)
        .setMatchingStrategy(MatchingStrategies.LOOSE);  //RESPONSE LA REQUEST AYNI KOD VAR BURDA LOOSE KULLANILDI REQUESTTE STANDART KULLANILIYOR
        return this.modelMapper;                           //LOOSE YERİNE STRNCT TE DİYEBİLİRİZ AMA O YÜZ YÜZ UYUMLULUK İSTİYOR BU YÜZDEN GEREK YOK
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
        .setAmbiguityIgnored(true)
        .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;


    }
    }


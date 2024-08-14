package kodlama.io.rentACar.coreCommon.unitilities.exceptions;

public class BusinessException extends RuntimeException{
  public BusinessException(String message){   //message döndürmek için yazıldı bana bir mesaj ver bende supere yani run time exceptiona  gönderim
      super(message);
  }
}

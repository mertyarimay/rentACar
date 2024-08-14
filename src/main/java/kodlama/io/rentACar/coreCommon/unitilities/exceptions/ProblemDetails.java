package kodlama.io.rentACar.coreCommon.unitilities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProblemDetails {//HATA MESAJI VERİLDİĞİNDE BÜTÜN YAPIYI VERMESİN DİYE OLUŞTURULDU
    String message;
}

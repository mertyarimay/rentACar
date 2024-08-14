package kodlama.io.rentACar.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateModelRequest {
    @NotNull//bir model kayıt edilmek isteniyorsa onun ismini girmek zorundasın
    @NotBlank//BOŞLUK OLMASIN VERİRKEN İSMİNİ
    @Size(min = 3,max = 20)//yazı boyutunuda bu şekilde olsun burda ve üstekkilerde validation seçiyoruz
    private String name;
    private int id;
    @NotNull
    private int brandId;  //brand ıd de almak zorundayız çünkü yeni bir model eklediğimizde bunun marka ıdsi ne olucak


}

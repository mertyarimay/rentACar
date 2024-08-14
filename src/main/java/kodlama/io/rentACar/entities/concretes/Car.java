package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="cars") //burası  postgresql deki brands tablosuna karşılık gelicek

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Car {
    @Id  //bir tane primery key olucağı için yine ıd oluyor plaka kodu olmuyor primerykey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="plate",unique = true)//plaka veritabanında unique yani tek değer olur
    private String plate;
    @Column(name="dailyPrice")
    private double dailyPrice;
    @Column(name="modelYear")
    private int modelYear;
    @Column(name="state")
    private String state;   //bu arabanın durumu nedir örn kiralamaya müsait bakımda vb


    //burda sadece modeli vermem yeterli çünkü modelin içinde markası var zaten
    @ManyToOne
    @JoinColumn(name="model_id")
    private Model model;

    @OneToMany(mappedBy = "car")
   private List<DriverInformation>driverInformations;

    @OneToMany(mappedBy = "car")
    private List<DriverInformationDiscount>driverInformationDiscounts;
}

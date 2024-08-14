package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name="models") //burası  postgresql deki brands tablosuna karşılık gelicek

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @ManyToOne   //BİRDEN FAZLA MODELE BİR MARKA KARŞILIĞI İLİŞKİLENDİRME
    @JoinColumn(name="brand_id") //modelin hangi brande ait olduğunu göstermek için bir brand ıd si olması lazım
    //yani veritabanında model tablomuza brand id koyucak ve brand tablosuyla fiziksel ilişki kurucak
    private Brand brand;

    @OneToMany(mappedBy = "model")//bir modele sahip birden fazla araba olabilir o yüzden içine ıd li yazmıyoruz
    private List<Car> cars;



}


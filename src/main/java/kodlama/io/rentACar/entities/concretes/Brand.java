package kodlama.io.rentACar.entities.concretes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="brands") //burası  postgresql deki brands tablosuna karşılık gelicek

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;



    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)  //BİRE ÇOK İLİŞKİ ORM  BİR TANE MARKAYA BİR ÇOK MODEL OLDUĞU İÇİN
    //bir brand nesnesi silindiğinde yada güncelleğinde otomatik olarak ona ait olan modellerde silinsin cashcadetype onun için kullanıldı.
   private List<Model>models;















}

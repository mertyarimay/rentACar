package kodlama.io.rentACar.dataAccess.abstracts;

import kodlama.io.rentACar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository <Brand,Integer> { //jpa veritabanı sorgularını çoğunu hallediyor
  //içinede entity kullanıcağım ve primery key i yazıyorum



  // List<Brand>getAll();  //markaları liste olarak getirmek için kullandık  bu sınıfı bir sınıfta implementasyonunu yazıp bu metodun altını doldurup döndürdük jpa kullanmadan bu şekilde
    // ama jpa kullanınca siliyoruz çünkü spring jpa bu ve bunun gibi bir çok şeyi sağlıyor


    Boolean existsByName(String name);  //iş kuralı servisi için yazdık jpa bunu sağlıyor extist gördüğü anda name alanına göre sorgu oluşturuyor
}

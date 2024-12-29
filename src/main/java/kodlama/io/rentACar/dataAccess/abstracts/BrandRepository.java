package kodlama.io.rentACar.dataAccess.abstracts;

import kodlama.io.rentACar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends JpaRepository <Brand,Integer> { //jpa veritabanı sorgularını çoğunu hallediyor
  //içinede entity kullanıcağım ve primery key i yazıyorum

    Boolean existsByName(String name);  //iş kuralı servisi için yazdık jpa bunu sağlıyor extist gördüğü anda name alanına göre sorgu oluşturuyor
}

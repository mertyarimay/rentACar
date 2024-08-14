package kodlama.io.rentACar.dataAccess.abstracts;


import kodlama.io.rentACar.entities.concretes.Car;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car,Integer> {
    Boolean existsByPlate(String plate);


    @Query("SELECT c FROM Car c WHERE c.id = :carId AND c.state = 'PASİF'")
    Optional<Car> findPassiveDriverByCarId(int carId);


    @Modifying
    @Query("UPDATE Car c SET c.state = :newState WHERE c.id = :carId")
    void updateCarState(@Param("carId") int carId, @Param("newState") String newState);


    @Modifying
    @Query("UPDATE Car c SET c.state = :newState WHERE c.id = :carId")
    void deleteCarState(@Param("carId") int carId, @Param("newState") String newState);



    //Girilen model Id ye göre car listesi dönmesi
    List<Car> findByModelId(Integer modelId);
}


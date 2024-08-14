package kodlama.io.rentACar.dataAccess.abstracts;

import kodlama.io.rentACar.entities.concretes.DriverInformationDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverInformationDiscountRepository extends JpaRepository<DriverInformationDiscount,Integer> {

    Boolean existsByTckmlkNo(String tckmlkNo);
}

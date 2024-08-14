package kodlama.io.rentACar.dataAccess.abstracts;


import kodlama.io.rentACar.entities.concretes.DriverInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DriverInformationRepository extends JpaRepository<DriverInformation,Integer> {



    Boolean existsByTckmlkNo(String tckmlkNo);




}

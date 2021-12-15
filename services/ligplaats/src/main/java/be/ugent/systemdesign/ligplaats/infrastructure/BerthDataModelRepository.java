package be.ugent.systemdesign.ligplaats.infrastructure;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BerthDataModelRepository extends JpaRepository<BerthDataModel, Integer> {
    @Query(value = "SELECT * FROM berth_data_model b WHERE b.size >= ?1 and b.state == ?2 ")
    List<BerthDataModel> findAllBySizeAndState(Double size, String state);
    //BerthDataModel findByBerthNumber(int berth_number);
    BerthDataModel findByBerthId(Integer id);
    List<BerthDataModel> findByState(String state);
    List<BerthDataModel> findAll();

    @Query(value = "SELECT * FROM berth_data_model b WHERE b.vesseld_id == ?1 ")
    BerthDataModel findByVesselId(String vesselId);

}


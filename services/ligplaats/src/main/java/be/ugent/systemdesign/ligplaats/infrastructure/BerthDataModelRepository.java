package be.ugent.systemdesign.ligplaats.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BerthDataModelRepository extends JpaRepository<BerthDataModel, Integer> {
    List<BerthDataModel> findBySizeAndState(Double size, String state);
    //BerthDataModel findByBerthNumber(int berth_number);
    BerthDataModel findByBerthId(Integer id);
    List<BerthDataModel> findByState(String state);
    List<BerthDataModel> findAll();
    BerthDataModel findByVesselId(String vesselId);
}

package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRouteDataModelRepository extends JpaRepository<RouteDataModel, Integer> {

    List<RouteDataModel> findBySizeCompatibility(Size size);
}

package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IRouteDataModelRepository extends JpaRepository<RouteDataModel, Integer> {
    List<RouteDataModel> findBySizeCompatibilityAndDestination(Size size, Integer destination);
}

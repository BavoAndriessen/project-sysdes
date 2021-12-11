package be.ugent.systemdesign.vesseltrafficcontrol.domain;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import java.util.List;

/**
 * A route is declared by List<Integer> = a list of gateId's
 */
public interface IRouteRepository {
    public List<Integer> findOne(Integer vesselId, Size size);
    public void save(Route route);
}

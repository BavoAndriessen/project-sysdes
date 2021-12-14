package be.ugent.systemdesign.vesseltrafficcontrol.domain;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;

/**
 * A route is declared by List<Integer> = a list of gateId's
 */
public interface IRouteRepository {
    public String findOne(Size size, Integer destination);
    public void save(Route route);
}

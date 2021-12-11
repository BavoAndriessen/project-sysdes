package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.IRouteRepository;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RouteRepository implements IRouteRepository {

    @Autowired
    IRouteDataModelRepository routeDMRepo;

    @Override
    public List<Integer> findOne(Integer vesselId, Size size) {
        List<RouteDataModel> routeDMs = routeDMRepo.findBySizeCompatibilityAndCapacityGreaterThan(size, 0);
        Integer cost = routeDMs.get(0).getCost();
        RouteDataModel routeDM = routeDMs.get(0);
        for (RouteDataModel rdm: routeDMs) {
            if(rdm.getCost() < cost){
                cost = rdm.getCost();
                routeDM = rdm;
            }
        }

        List<Integer> route = new ArrayList<>();
        for (String id: routeDM.getRoute().split(";")) {
            route.add(Integer.parseInt(id));
        }
        return route;
    }

    @Override
    public void save(Route route) {
        routeDMRepo.save(toRouteDM(route));
    }

    private RouteDataModel toRouteDM(Route route){
        return new RouteDataModel(
                route.getRouteId(),
                castRouteToString(route.getRoute()),
                route.getSizeCompatiblity(),
                route.getRouteLength(),
                route.getCapacity()
        );
    }

    private String castRouteToString(List<Integer> route) {
        StringBuilder routeString = new StringBuilder(route.get(0).toString());
        for(int i = 1; i<route.size(); i++) {
            routeString.append(";").append(route.get(i).toString());
        }
        return routeString.toString();
    }
}

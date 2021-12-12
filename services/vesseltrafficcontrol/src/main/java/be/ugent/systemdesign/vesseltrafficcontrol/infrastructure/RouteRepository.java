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
    public String findOne(Size size) {
        List<RouteDataModel> routeDMs = routeDMRepo.findBySizeCompatibilityAndCapacityGreaterThan(size, 0);
        Integer cost = routeDMs.get(0).getCost();
        RouteDataModel routeDM = routeDMs.get(0);
        for (RouteDataModel rdm: routeDMs) {
            if(rdm.getCost() < cost){
                cost = rdm.getCost();
                routeDM = rdm;
            }
        }
        return routeDM.getRoute();
    }

    @Override
    public void save(Route route) {
        routeDMRepo.save(toRouteDM(route));
    }

    private RouteDataModel toRouteDM(Route route){
        return new RouteDataModel(
                route.getRouteId(),
                route.getRoute(),
                route.getSizeCompatiblity(),
                route.getRouteLength(),
                route.getCapacity()
        );
    }
}
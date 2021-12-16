package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.IRouteRepository;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.exceptions.RouteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RouteRepository implements IRouteRepository {

    @Autowired
    IRouteDataModelRepository routeDMRepo;

    Logger log = LoggerFactory.getLogger(RouteRepository.class);

    @Override
    public String findOne(Size size, Integer destination) {
        List<RouteDataModel> routeDMs = routeDMRepo.findBySizeCompatibility(size);
        if (routeDMs.isEmpty()) {
            log.warn("no routes in database");
            routeDMs.add(new RouteDataModel(0, "1;2", Size.SMALL, 1200, "4", 1));
        }
        RouteDataModel routeDM = routeDMs.get(0);
        /*Integer cost = routeDMs.get(0).getCost();
        RouteDataModel routeDM = routeDMs.get(0);
        for (RouteDataModel rdm: routeDMs) {
            if(rdm.getCost() < cost){
                cost = rdm.getCost();
                routeDM = rdm;
            }
        } */
        if(routeDM == null){
            throw new RouteNotFoundException();
        }
        // to lower the route capacity when ship is sent out
        // routeDM.setCapacity(routeDM.getCapacity() - 1);
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
                route.getCapacity().toString(),
                route.getDestination()
        );
    }
}

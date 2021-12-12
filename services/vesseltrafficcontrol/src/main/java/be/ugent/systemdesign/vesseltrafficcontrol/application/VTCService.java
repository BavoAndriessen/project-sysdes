package be.ugent.systemdesign.vesseltrafficcontrol.application;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.IRouteRepository;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Gate;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateState;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateType;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class VTCService implements IVTCService{

    @Autowired
    IRouteRepository routeRepo;

    /*
    final List<Gate> gates = new ArrayList<>() {
        {
            add(new Gate(1, GateType.BRIDGE, GateState.CLOSE, Size.SMALL));
            add(new Gate(2, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.MEDIUM));
            add(new Gate(3, GateType.BRIDGE, GateState.CLOSE, Size.LARGE));
            add(new Gate(4, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.LARGE));
            add(new Gate(5, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.SMALL));
            add(new Gate(6, GateType.BRIDGE, GateState.CLOSE, Size.MEDIUM));
        }
    };
    */

    public VTCService() {
        routeRepo.save(new Route(
                1,"1;5", Size.SMALL, 130, 5
        ));
        routeRepo.save(new Route(
                2,"2;6", Size.MEDIUM, 101, 3
        ));
        routeRepo.save(new Route(
                3,"3;4", Size.LARGE, 180, 1
        ));
    }

    @Override
    public Response findRoute(Integer vesselId, Size size) {
        String routePath = routeRepo.findOne(size);
        // if(routePath.isEmpty()) {
        //    Route route = calculateRoute(size);
        // }
        return new Response(ResponseStatus.SUCCESS, "Found a route for vessel with id: " + vesselId);
    }

    /*
    private Route calculateRoute(Size size) {
        // implement dijkstra
    }
    */
}

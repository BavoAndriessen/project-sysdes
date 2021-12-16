package be.ugent.systemdesign.vesseltrafficcontrol.application;

import be.ugent.systemdesign.vesseltrafficcontrol.application.event.EventDispatcher;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.IRouteRepository;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Gate;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Vessel;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateState;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.GateType;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.VesselState;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.events.NavigateShipEvent;
import be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.exceptions.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class VTCService implements IVTCService{

    @Autowired
    IRouteRepository routeRepo;

    @Autowired
    EventDispatcher eventDispatcher;

    List<Vessel> vessels;
    final List<Gate> gates;
    Set<Integer> freeDocks;

    public VTCService() {
        vessels = new ArrayList<>();
        gates = fillGates();
        freeDocks = new HashSet<>();
        for(int i = 1; i<11; i++) {
            freeDocks.add(i);
        }
    }

    @Override
    public Response findRoute(String vesselId, Integer destination) {
        Optional<Vessel> vessel = vessels.stream().filter(v -> v.getVesselId().equals(vesselId)).findFirst();
        if(vessel.isPresent()) {
            try {
                String routePath = routeRepo.findOne(vessel.get().getSize(), destination);
                eventDispatcher.publishNavigateShipEvent(new NavigateShipEvent(vesselId, routePath));
            } catch(RouteNotFoundException exception) {
                return new Response(ResponseStatus.FAILED, "no route found");
            }
        }
        return new Response(ResponseStatus.SUCCESS, "Found a route for vessel with id: " + vesselId);
    }

    @Override
    public Response registerVessel(Vessel vessel) {
        vessels.add(vessel);
        if(freeDocks.isEmpty()){
            return new Response(ResponseStatus.FAILED, "no dock ready now, try again later!");
        }
        Optional<Integer> dock = freeDocks.stream().findFirst();
        return findRoute(vessel.getVesselId(), dock.get());
    }

    @Override
    public Response changeGateState(int gateId) {
        gates
                .stream()
                .filter(gate -> gate.getGateId() == gateId)
                .forEach(Gate::toggleState);
        return new Response(ResponseStatus.SUCCESS, "thank u for changing the state of the gate");
    }

    @Override
    public Response vesselArrived(String vesselId, Integer destination) {
        Optional<Vessel> vessel = vessels.stream().filter(v -> v.getVesselId().equals(vesselId)).findFirst();
        if(vessel.isPresent() && destination.equals(0)) {
            vessels.remove(vessel.get());
        } else vessel.ifPresent(value -> value.setState(VesselState.IDLE));
        return new Response(ResponseStatus.SUCCESS, "Vessel has successfully arrived");
    }

    @Override
    public Response freeDock(Integer dockNumber) {
        freeDocks.add(dockNumber);
        return new Response(ResponseStatus.SUCCESS, "dock " + dockNumber + " became available");
    }

    private List<Gate> fillGates(){
        List<Gate> allGates = new ArrayList<>();
        allGates.add(new Gate(0, GateType.BRIDGE, GateState.CLOSE, Size.SMALL));
        allGates.add(new Gate(1, GateType.BRIDGE, GateState.CLOSE, Size.SMALL));
        allGates.add(new Gate(2, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.MEDIUM));
        allGates.add(new Gate(3, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.MEDIUM));
        allGates.add(new Gate(4, GateType.BRIDGE, GateState.CLOSE, Size.MEDIUM));
        allGates.add(new Gate(5, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.LARGE));
        allGates.add(new Gate(6, GateType.BRIDGE, GateState.CLOSE, Size.LARGE));
        allGates.add(new Gate(7, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.SMALL));
        allGates.add(new Gate(8, GateType.BRIDGE, GateState.CLOSE, Size.LARGE));
        allGates.add(new Gate(9, GateType.BRIDGE, GateState.CLOSE, Size.SMALL));
        allGates.add(new Gate(10, GateType.BRIDGE, GateState.CLOSE, Size.MEDIUM));
        allGates.add(new Gate(11, GateType.BRIDGE, GateState.CLOSE, Size.SMALL));
        allGates.add(new Gate(12, GateType.HARBOUR_LOCK, GateState.CLOSE, Size.MEDIUM));
        allGates.add(new Gate(13, GateType.BRIDGE, GateState.CLOSE, Size.SMALL));
        return allGates;
    }

    /*
    private Route calculateRoute(Size size) {
        // implement dijkstra
    }
    */
}

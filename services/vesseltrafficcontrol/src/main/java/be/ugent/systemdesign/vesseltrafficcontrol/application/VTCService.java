package be.ugent.systemdesign.vesseltrafficcontrol.application;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.IRouteRepository;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Gate;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Route;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Vessel;
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

    List<Vessel> vessels;

    final List<Gate> gates;

    public VTCService() {
        vessels = new ArrayList<>();
        gates = fillGates();
    }

    @Override
    public Response findRoute(String vesselId, Size size, Integer destination) {
        String routePath = routeRepo.findOne(size, destination);
        return new Response(ResponseStatus.SUCCESS, "Found a route for vessel with id: " + vesselId);
    }

    @Override
    public Response registerVessel(Vessel vessel) {
        vessels.add(vessel);
        return new Response(ResponseStatus.SUCCESS, "your vessel has been registered");
    }

    @Override
    public Response changeGateState(int gateId) {
        gates
                .stream()
                .filter(gate -> gate.getGateId() == gateId)
                .forEach(Gate::toggleState);
        return new Response(ResponseStatus.SUCCESS, "thank u for changing the state of the gate");
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

package be.ugent.systemdesign.vesseltrafficcontrol.application;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Vessel;

public interface IVTCService {
    Response findRoute(String vesselId, Integer destination);
    Response registerVessel(Vessel vessel);
    Response changeGateState(int gateId);
    Response vesselArrived(String VesselId, Integer destination);
    Response freeDock(Integer dockNumber);
}

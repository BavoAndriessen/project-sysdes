package be.ugent.systemdesign.vesseltrafficcontrol.application;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Vessel;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;

public interface IVTCService {
    Response findRoute(Integer vesselId, Size size, Integer destination);
    Response registerVessel(Vessel vessel);
    Response changeGateState(Integer gateId);
}

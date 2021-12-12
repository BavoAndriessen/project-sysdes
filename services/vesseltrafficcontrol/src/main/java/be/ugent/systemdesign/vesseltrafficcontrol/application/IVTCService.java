package be.ugent.systemdesign.vesseltrafficcontrol.application;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.enums.Size;

public interface IVTCService {
    Response findRoute(Integer vesselId, Size size);
}

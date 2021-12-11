package be.ugent.systemdesign.vesseltrafficcontrol.application;

import be.ugent.systemdesign.vesseltrafficcontrol.domain.IRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VTCService implements IVTCService{

    @Autowired
    IRouteRepository routeRepo;
}

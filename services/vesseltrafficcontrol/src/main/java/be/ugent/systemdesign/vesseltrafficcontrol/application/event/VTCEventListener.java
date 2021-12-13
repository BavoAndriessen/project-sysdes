package be.ugent.systemdesign.vesseltrafficcontrol.application.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VTCEventListener {

    @Autowired
    EventDispatcher eventDispatcher;

    private static final Logger log = LoggerFactory.getLogger(VTCEventListener.class);
}

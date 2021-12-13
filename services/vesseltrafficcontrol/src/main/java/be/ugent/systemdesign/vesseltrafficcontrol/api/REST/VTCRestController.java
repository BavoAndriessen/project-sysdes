package be.ugent.systemdesign.vesseltrafficcontrol.api;

import be.ugent.systemdesign.vesseltrafficcontrol.application.Response;
import be.ugent.systemdesign.vesseltrafficcontrol.application.ResponseStatus;
import be.ugent.systemdesign.vesseltrafficcontrol.application.VTCService;
import be.ugent.systemdesign.vesseltrafficcontrol.application.query.CommandQuery;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.aggregates.Vessel;
import be.ugent.systemdesign.vesseltrafficcontrol.domain.command.ChangeGateStateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "api/vtc/")
public class VTCRestController {

    @Autowired
    VTCService vtcService;

    @Autowired
    private CommandQuery commandQuery;

    @PostMapping
    public ResponseEntity<String> registerShip(Vessel vessel) {
        Response response = vtcService.registerVessel(vessel);
        if(response.status == ResponseStatus.FAIL) {
            return new ResponseEntity<>(response.message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response.message, HttpStatus.OK);
    }

    @PutMapping("gate/{id}")
    public ResponseEntity<String> gateStateChanged(@PathVariable("id") Integer gateId) {
        Response response = vtcService.changeGateState(gateId);
        if(response.status == ResponseStatus.FAIL) {
            return new ResponseEntity<>(response.message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response.message, HttpStatus.OK);
    }

    @GetMapping("/commands/change_gate")
    public ResponseEntity<List<ChangeGateStateCommand>> getGates() {
        return new ResponseEntity<>(commandQuery.findAllChangeGateStateCommands(), HttpStatus.OK);
    }


}

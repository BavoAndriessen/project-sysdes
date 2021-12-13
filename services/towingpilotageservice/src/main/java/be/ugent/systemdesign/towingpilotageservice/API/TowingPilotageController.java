package be.ugent.systemdesign.towingpilotageservice.API;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/towingpilotage")
@CrossOrigin(origins="*")
public class TowingPilotageController {


    @GetMapping("/towvessel/{id}")
    public ResponseEntity<String> askForService(@PathVariable("id") String vesselId) {
        return new ResponseEntity<>("towing request for vessel " + vesselId + " received", HttpStatus.OK);
    }

}

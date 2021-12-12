package be.ugent.systemdesign.maintenanceservice.API.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/maintenanceservice")
@CrossOrigin(origins="*")
public class MaintenanceServiceController {



    @GetMapping("/askforservice/{id}")
    public ResponseEntity<String> askForService(@PathVariable("id") String vesselId){
        getLocationOfBoat(vesselId);
        return new ResponseEntity<>("request maintenance for vessel "+vesselId+" received", HttpStatus.OK);
    }

    private void getLocationOfBoat(String vesselId){
        //TODO correcte uri
        String uri = "http://localhost:2222/api/berth/?vesselId="+vesselId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);

    }
}

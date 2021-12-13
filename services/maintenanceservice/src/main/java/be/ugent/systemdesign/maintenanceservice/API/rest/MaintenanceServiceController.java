package be.ugent.systemdesign.maintenanceservice.API.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/maintenanceservice")
@CrossOrigin(origins="*")
public class MaintenanceServiceController {

    @Value("${kapiteinsdienstservice}")
    private String kapiteinsdienstservice;

    @GetMapping("/askforservice/{id}")
    public ResponseEntity<String> askForService(@PathVariable("id") String vesselId){
        getLocationOfBoat(vesselId);
        return new ResponseEntity<>("request maintenance for vessel "+vesselId+" received", HttpStatus.OK);
    }

    private void getLocationOfBoat(String vesselId){
        String uri = "http://" + kapiteinsdienstservice + "/api/berth/?vesselId="+vesselId;
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);

    }
}

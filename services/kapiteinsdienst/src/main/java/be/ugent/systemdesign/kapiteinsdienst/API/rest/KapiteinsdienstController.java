package be.ugent.systemdesign.kapiteinsdienst.API.rest;

import be.ugent.systemdesign.kapiteinsdienst.application.Response;
import be.ugent.systemdesign.kapiteinsdienst.application.VesselService;
import be.ugent.systemdesign.kapiteinsdienst.application.query.OfferQuery;
import be.ugent.systemdesign.kapiteinsdienst.application.ResponseStatus;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.infrastructure.OfferNotFoundException;
import be.ugent.systemdesign.kapiteinsdienst.infrastructure.VesselNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kapiteinsdienst")
@CrossOrigin(origins="*")
public class KapiteinsdienstController {


    @Autowired
    OfferQuery offerQuery;

    @Autowired
    VesselService vesselService;

    @GetMapping("/offer")
    public ResponseEntity<OfferViewModel> findOfferForVessel(@RequestParam("id") String vesselId){
        try{
            return new ResponseEntity<OfferViewModel>(new OfferViewModel(offerQuery.getOfferByVesselId(vesselId)), HttpStatus.OK);

        } catch (VesselNotFoundException | OfferNotFoundException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registerVessel")
    public ResponseEntity<String> registerNewVessel(@RequestBody Vessel vessel){
        Response response = vesselService.registerNewVessel(vessel);
        return createResponseEntity(response.status, response.message);

    }

    @PostMapping("/{id}/offerConfirmation")
    public ResponseEntity<String> offerConfirmation(@PathVariable("id") String vesselId, @RequestParam("confirmation") Boolean isConfirmed){
            Response response = vesselService.handleOfferConfirmation(vesselId, isConfirmed);
        return createResponseEntity(response.status, response.message);
    }


    private ResponseEntity<String> createResponseEntity(ResponseStatus status, String message){
        if(status == ResponseStatus.FAIL)
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
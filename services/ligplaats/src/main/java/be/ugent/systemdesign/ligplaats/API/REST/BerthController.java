package be.ugent.systemdesign.ligplaats.API.REST;

import java.util.List;
import java.util.stream.Collectors;

import be.ugent.systemdesign.ligplaats.application.BerthService;
import be.ugent.systemdesign.ligplaats.application.ResponseStatus;
import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.command.UnloadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.query.BerthQuery;
import be.ugent.systemdesign.ligplaats.domain.Berth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="api/berth/")
@CrossOrigin(origins="*")
public class BerthController {

    @Autowired
    BerthService berthService;

    @Autowired
    BerthQuery berthQuery;

    @GetMapping()
    public BerthViewModel findWithVesselId(@RequestParam("vesselId") String vesselId){
        return new BerthViewModel(berthQuery.generateLocationOfBoat(vesselId));
    }


    @GetMapping("/unload/{berthId}")
    public String unloadContainers(@PathVariable("berthId") Integer berthId) throws Exception {
        berthService.handelUnloadContainersREST(new UnloadContainersCommand(berthId));
        return "the containers are unloaded(thanks to Popeye), ship ready event is sent to VTC. ship has left and the berth is now available again";
    }

    @GetMapping("/load/{berthId}")
    public String loadContainers(@PathVariable("berthId") Integer berthId) throws Exception {
        berthService.handelLoadContainersREST(new LoadContainersCommand(berthId));
        return "the containers are loaded(thanks to Popeye), ship ready event is sent to VTC. ship has left and the berth is now available again.";
    }
    @GetMapping("/all")
    public List<Berth> getAll() throws Exception {

        return berthService.findAll();
    }

    private ResponseEntity<String> createResponseEntity(ResponseStatus status, String happyMessage, HttpStatus happyStatus, String sadMessage, HttpStatus sadStatus){
        if(status == ResponseStatus.FAIL)
            return new ResponseEntity<>(sadMessage, sadStatus);
        return new ResponseEntity<>(happyMessage,happyStatus);
    }



}
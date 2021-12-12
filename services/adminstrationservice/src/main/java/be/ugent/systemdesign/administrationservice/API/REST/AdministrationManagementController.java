package be.ugent.systemdesign.administrationservice.API.REST;

import be.ugent.systemdesign.administrationservice.application.AdministrationService;
import be.ugent.systemdesign.administrationservice.application.Response;
import be.ugent.systemdesign.administrationservice.application.ResponseStatus;
import be.ugent.systemdesign.administrationservice.application.query.DocumentQuery;
import be.ugent.systemdesign.administrationservice.application.query.StaffQuery;
import be.ugent.systemdesign.administrationservice.infrastructure.staff.StaffNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/administration")
@CrossOrigin("*")
public class AdministrationManagementController {

    @Autowired
    DocumentQuery documentQuery;

    @Autowired
    StaffQuery staffQuery;

    @Autowired
    AdministrationService service;

    @GetMapping("documents/{id}")
    public ResponseEntity<List<DocumentViewModel>> getDocumentsForVessel(@PathVariable("id") String id) {
        try {
            List<DocumentViewModel> result = documentQuery.generateDocumentsForVessel(id).stream()
                    .map(d -> new DocumentViewModel(d))
                    .collect(Collectors.toList());

            return new ResponseEntity<List<DocumentViewModel>>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("staff/{id}")
    public ResponseEntity<StaffViewModel> getPayCheck(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<StaffViewModel>(new StaffViewModel(staffQuery.generatePayCheck(id)), HttpStatus.OK);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("staff/{id}")
    public ResponseEntity<String> timeBadging(@PathVariable("id") Integer id, @RequestParam("time") String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime t = LocalDateTime.parse(time, formatter);
        Response response = service.registerNewTimeBadge(id, t);
        return createResponseEntity(response.getStatus(), response.getMessage());
    }

    private ResponseEntity<String> createResponseEntity(ResponseStatus status, String message) {
        if (status == ResponseStatus.FAIL)
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

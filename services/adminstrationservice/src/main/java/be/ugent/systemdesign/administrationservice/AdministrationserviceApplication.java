package be.ugent.systemdesign.administrationservice;

import be.ugent.systemdesign.administrationservice.API.messaging.Channels;
import be.ugent.systemdesign.administrationservice.application.AdministrationServiceImpl;
import be.ugent.systemdesign.administrationservice.application.Response;
import be.ugent.systemdesign.administrationservice.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAsync
@EnableBinding(Channels.class)
public class AdministrationserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdministrationserviceApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(AdministrationserviceApplication.class);

    @Bean
    public CommandLineRunner populateDatabase(DocumentRepository repo, StaffRepository staffRepo) {
        return (args) -> {
            //DMrepo.deleteAll();
            Container c1 = new Container(1, "bananen");
            Container c2 = new Container(2, "appels");
            Container c3 = new Container(3, "wortels");

            List<Container> containerList = new ArrayList<>();
            containerList.add(c1);
            containerList.add(c2);
            containerList.add(c3);

            Offer offer = new Offer(1, 200.7, 2, null, 5.0, 6.0);

            Vessel vessel = new Vessel("ellen", null);

            Document document = new Document(1, vessel, offer, containerList);

            repo.save(document);

            List<Document> documents = repo.findAllDocumentsForVessel(vessel.getVesselId()); //Dit werkt nog niet
            Document docs = documents.get(0);
//            Document docs = repo.findOne(document.getDocumentId());

            log.warn("Document found with id {} and offer {}", docs.getDocumentId(), docs.getOffer().getPrice());

            WorkedHours wh1 = new WorkedHours(LocalDateTime.now(), LocalDateTime.now());
            WorkedHours wh2 = new WorkedHours(LocalDateTime.now(), LocalDateTime.now());
            WorkedHours wh3 = new WorkedHours(LocalDateTime.now(), LocalDateTime.now());
            List<WorkedHours> workedHours = new ArrayList<>();
            workedHours.add(wh1);
            workedHours.add(wh2);
            workedHours.add(wh3);

            Staff s = new Staff(1, "mullie", "ellen", StaffType.PILOT, 10.2, workedHours);

            staffRepo.save(s);

            Staff staff = staffRepo.findOne(1);

            log.warn("Staff found with id {} and wage {}", staff.getStaffId(), staff.getWage());
        };
    }

    @Bean
    public CommandLineRunner testStaff(){
        return (args -> {
            Staff s = new Staff(1, "mullie", "ellen", StaffType.PILOT, 10.2, null);
            s.addWorkedHour(LocalDateTime.of(2021, Month.JUNE, 23, 8,0));
            s.addWorkedHour(LocalDateTime.of(2021, Month.JUNE, 23, 17,0));
            s.addWorkedHour(LocalDateTime.of(2021, Month.JUNE, 24, 8,0));
            s.addWorkedHour(LocalDateTime.of(2021, Month.JUNE, 24, 17,0));
            s.addWorkedHour(LocalDateTime.of(2021, Month.JUNE, 25, 8,0));

            try{
                log.warn("Ellen needs to be paid {} euros", s.getPay());
            }
            catch(shiftNotOverExecption e){
                log.warn("Ellen can not be payed if their shift is not over yet");
            }
            s.addWorkedHour(LocalDateTime.of(2021, Month.JUNE, 25, 17,0));
            try{
                log.warn("Ellen needs to be paid {} euros", s.getPay());
            }
            catch(shiftNotOverExecption e){
                log.warn("Ellen can not be payed if their shift is not over yet");
            }
        });
    }

    @Bean
    public CommandLineRunner testApplicationService(AdministrationServiceImpl service){
        return (args -> {
            service.addStaff(new Staff(5, "Mullie", "Ellen", StaffType.DOCKWORKER, 13.5, null));
            Response r1 = service.registerNewTimeBadge(5, LocalDateTime.of(2021, Month.JUNE, 25, 17,0));
            log.warn(r1.getMessage());

            Response r2 = service.registerNewVesselWithOffer("vessel3", LocalDateTime.now(), 50, 80.0, 60.5, null);
            log.warn(r2.getMessage());
            Response r3 = service.registerNewVesselWithOffer("vessel3", LocalDateTime.now(), 25, 80.0, 60.5, null);
            log.warn(r3.getMessage());

            Response r4 = service.handleOfferDeleted("vessel3", 1);
            log.warn(r4.getMessage());

            Response verkeerdeVessel = service.handleOfferDeleted("vessel4", 1);
            Response verkeerdeOffer = service.handleOfferDeleted("vessel3", 2);
            Response verkeerdeStaff = service.registerNewTimeBadge(3, LocalDateTime.now());
            log.warn(verkeerdeOffer.getMessage());
            log.warn(verkeerdeVessel.getMessage());
            log.warn(verkeerdeStaff.getMessage());
        });
    }

}

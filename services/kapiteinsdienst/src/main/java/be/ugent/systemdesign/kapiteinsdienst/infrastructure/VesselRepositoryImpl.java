package be.ugent.systemdesign.kapiteinsdienst.infrastructure;

import be.ugent.systemdesign.kapiteinsdienst.domain.Container;
import be.ugent.systemdesign.kapiteinsdienst.domain.Crew;
import be.ugent.systemdesign.kapiteinsdienst.domain.Vessel;
import be.ugent.systemdesign.kapiteinsdienst.domain.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class VesselRepositoryImpl implements VesselRepository {

    @Autowired
    VesselDataModelRepository vesselRepo;


    @Override
    public void save(Vessel vessel) {
        vesselRepo.save(mapToVesselDataModel(vessel));
    }

    @Override
    public Vessel findById(Integer vesselNumber) {
        VesselDataModel vessel = vesselRepo.findById(vesselNumber).orElseThrow(VesselNotFoundException::new);
        return mapToVessel(vessel);
    }


    private VesselDataModel mapToVesselDataModel(Vessel vessel){
        return new VesselDataModel(
                vessel.getVesselNumber(),
                vessel.getStatus(),
                vessel.getArrivalDateTime(),
                vessel.getDepartureDateTime(),
                vessel.getVesselSize(),
                vessel.getAmountOfWaste(),
                vessel.getOfferId(),
                vessel.getPrice(),
                vessel.getBerthReserved(),
                vessel.getTowingPilotageReserved(),
                vessel.getServiceReserved(),
                vessel.getAdditionalServices(),
                vessel.getContainerList(),
                vessel.getCrewList());

    }

    private Vessel mapToVessel(VesselDataModel vessel){
        return Vessel.builder()
                    .vesselNumber(vessel.getVesselNumber())
                    .status(vessel.getStatus())
                    .arrivalDateTime(vessel.getArrivalDateTime())
                    .departureDateTime(vessel.getDepartureDateTime())
                    .vesselSize(vessel.getVesselSize())
                    .amountOfWaste(vessel.getAmountOfWaste())
                    .additionalServices(vessel.getAdditionalServices())
                    .offerId(vessel.getOfferId())
                    .price(vessel.getPrice())
                    .berthReserved(vessel.getBerthReserved())
                    .towingPilotageReserved((vessel.getTowingPilotageReserved()))
                    .serviceReserved(vessel.getServiceReserved())
                    .containerList(vessel.getContainerList()
                                    .stream()
                                    .map(e -> Container.builder()
                                            .containerId(e.getContainerId())
                                            .contents(e.getContents())
                                            .build())
                                    .collect(Collectors.toList()))
                    .crewList(vessel.getCrewList()
                                .stream()
                                .map(e -> Crew.builder()
                                        .crewId(e.getCrewId())
                                        .firstName(e.getFirstName())
                                        .lastName(e.getLastName())
                                        .dateOfBirth(e.getDateOfBirth())
                                        .type(e.getType())
                                        .build())
                                .collect(Collectors.toList()))
                    .build();
    }


}

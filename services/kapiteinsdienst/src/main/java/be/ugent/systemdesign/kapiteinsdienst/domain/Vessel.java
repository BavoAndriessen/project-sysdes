package be.ugent.systemdesign.kapiteinsdienst.domain;


import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Vessel {

    private String vesselId;
    private VesselStatus status;
    private LocalDateTime arrivalDateTime;
    private Integer lengthOfStay;
    private Double vesselSize;
    private Double amountOfWaste;
    private Integer offerId;
    private Double price;
    private Boolean berthReserved;
    private Boolean towingPilotageReserved;
    private Boolean serviceReserved;
    private List<String> additionalServices;
    private List<Container> containerList;

    public Vessel() {
        this.status = null;
        this.offerId = null;
        this.price = null;
        this.berthReserved = false;
        this.towingPilotageReserved = false;
        this.serviceReserved = false;
        this.additionalServices = new ArrayList<String>();
        this.containerList = new ArrayList<Container>();
    }

    public Vessel(String vesselId,
                  LocalDateTime arrivalDateTime,
                  Integer lengthOfStay,
                  Double vesselSize,
                  Double amountOfWaste
        ) {
        this.vesselId = vesselId;
        this.status = null;
        this.arrivalDateTime = arrivalDateTime;
        this.lengthOfStay = lengthOfStay;
        this.vesselSize = vesselSize;
        this.amountOfWaste = amountOfWaste;
        this.offerId = null;
        this.price = null;
        this.berthReserved = false;
        this.towingPilotageReserved = false;
        this.serviceReserved = false;
        this.additionalServices = new ArrayList<String>();
        this.containerList = new ArrayList<Container>();
    }

    public void addContainerList(List<Container> containerList){
        this.containerList = containerList;
    }


    public void addAdditionalServices(List<String> additionalServices){
        this.additionalServices = additionalServices;
    }

    public void updateOfferInfo(Integer offerId, Double price){
        this.offerId = offerId;
        this.price = price;
        this.status = VesselStatus.OFFER_CREATED;
    }

    public void newRegistration(){
        this.status = VesselStatus.OFFER_REQUESTED;
    }

    public boolean checkOfferAvailability(){
        return berthReserved && towingPilotageReserved && serviceReserved && status == VesselStatus.OFFER_CREATED;
    }

    public void updateReservationStatus(ReservationServices service){
        if(service == ReservationServices.BERTH){
            this.berthReserved = true;
        } else if (service == ReservationServices.TOWINGPILOTAGE){
            this.towingPilotageReserved = true;
        } else {
            this.serviceReserved = true;
        }
    }

    public void failedReservation(){
        this.status = VesselStatus.RESERVATION_FAIL;
        unsetServiceReservationStatus();

    }

    public void offerConfirmation(Boolean isConfirmed){
        if(isConfirmed){
            this.status = VesselStatus.ACCEPTED;
        } else {
            this.status = VesselStatus.REFUSED;
            unsetServiceReservationStatus();
        }
    }

    private void unsetServiceReservationStatus(){
        this.berthReserved = false;
        this.towingPilotageReserved = false;
        this.serviceReserved = false;
    }

}

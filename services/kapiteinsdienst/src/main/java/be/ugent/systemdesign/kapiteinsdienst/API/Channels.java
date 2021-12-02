package be.ugent.systemdesign.kapiteinsdienst.API;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    static final String CREATE_OFFER = "create_offer";
    static final String OFFER_PROPOSAL = "offer_proposal";
    static final String OFFER_CONFIRMATION =  "offer_confirmation";
    static final String RESERVE_BERTH = "reserve_berth";
    static final String BERTH_RESERVED = "berth_reserved";
    static final String RESERVE_SERVICE = "reserve_service";
    static final String SERVICE_RESERVED = "service_reserved";
    static final String RESERVE_TOWING_PILOTAGE = "reserve_towing_pilotage";
    static final String TOWING_PILOTAGE_RESERVED = "towing_pilotage_reserved";
    static final String UNDO_BERTH_RESERVATION = "undo_berth_reservation";
    static final String UNDO_SERVICE_RESERVATION = "undo_service_reservation";
    static final String UNDO_TOWING_PILOTAGE_RESERVATION = "undo_towing_pilotage_reservation";
    static final String REQUEST_OFFER = "request_offer";
    static final String OFFER_CREATED = "offer_created";
    static final String DELETE_OFFER = "delete_offer";


    @Input(CREATE_OFFER)
    SubscribableChannel createOffer();

    @Input(OFFER_CONFIRMATION)
    SubscribableChannel offerConfirmation();

    @Input(BERTH_RESERVED)
    SubscribableChannel berthReserved();

    @Input(SERVICE_RESERVED)
    SubscribableChannel serviceReserved();

    @Input(TOWING_PILOTAGE_RESERVED)
    SubscribableChannel towingPilotageReserved();

    @Input(OFFER_CREATED)
    SubscribableChannel offerCreated();

    @Output(OFFER_PROPOSAL)
    MessageChannel offerProposal();

    @Output(RESERVE_BERTH)
    MessageChannel reserveBerth();

    @Output(RESERVE_SERVICE)
    MessageChannel reserveService();

    @Output(RESERVE_TOWING_PILOTAGE)
    MessageChannel reserveTowingPilotage();

    @Output(UNDO_BERTH_RESERVATION)
    MessageChannel undoBerthReservation();

    @Output(UNDO_SERVICE_RESERVATION)
    MessageChannel undoServiceReservation();

    @Output(UNDO_TOWING_PILOTAGE_RESERVATION)
    MessageChannel undoTowingPilotageReservation();

    @Output(REQUEST_OFFER)
    MessageChannel requestOffer();

    @Output(DELETE_OFFER)
    MessageChannel deleteOffer();

}

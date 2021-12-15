package be.ugent.systemdesign.ligplaats.application;

import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthResponse;
import be.ugent.systemdesign.ligplaats.application.command.UnloadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.event.ContainersReadyAtDockEvent;
import be.ugent.systemdesign.ligplaats.application.event.ShipArrivingEvent;
import be.ugent.systemdesign.ligplaats.domain.Berth;
import be.ugent.systemdesign.ligplaats.domain.BerthWorkerState;

import java.util.List;


public interface BerthService {


    ReserveBerthResponse reserveBerth(Double size, String vesselId) throws Exception;
    //Response loadContainers(Integer berthId);
    void undoReservation(String vesselId) throws Exception;
    //Response unloadContainers(Integer berthId);
    //Response BerthNumberforReservation();

    //setBerthReady handelt event shipArrivning af.
    void setBerthReady(String vesselId) throws Exception;
    void setWorkerStatusAtDock(BerthWorkerState state, Integer berthId) throws Exception;
    void handelLoadContainersREST(LoadContainersCommand command) throws Exception;
    void handelUnloadContainersREST(UnloadContainersCommand command) throws Exception;
    void fillRepository();
    void sendShipReady(Integer berthId) throws Exception;
    void sendDockReady(String vesselId) throws Exception;
    public List<Berth> findAll();
    void handelShipArriving(ShipArrivingEvent e) throws Exception;
    void handelContainersReadyAtDock(ContainersReadyAtDockEvent e) throws Exception;
}

package be.ugent.systemdesign.ligplaats.application;

import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthResponse;
import be.ugent.systemdesign.ligplaats.application.command.UnloadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.event.DockReadyEvent;
import be.ugent.systemdesign.ligplaats.application.event.EventDispatcher;
import be.ugent.systemdesign.ligplaats.application.event.ShipReadyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


public interface BerthService {


    ReserveBerthResponse reserveBerth(Double size, String vesselId);
    //Response loadContainers(Integer berthId);
    void undoReservation(String vesselId) throws Exception;
    //Response unloadContainers(Integer berthId);
    //Response BerthNumberforReservation();

    //setBerthReady handelt event shipArrivning af.
    void setBerthReady(String vesselId) throws Exception;
    void setWorkerAtBerthAvailable(Integer berthId) throws Exception;
    void handelLoadContainersREST(LoadContainersCommand command) throws Exception;
    void handelUnloadContainersREST(UnloadContainersCommand command) throws Exception;

}

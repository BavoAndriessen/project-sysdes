package be.ugent.systemdesign.ligplaats.application;

import be.ugent.systemdesign.ligplaats.application.command.LoadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.command.ReserveBerthResponse;
import be.ugent.systemdesign.ligplaats.application.command.UnloadContainersCommand;
import be.ugent.systemdesign.ligplaats.application.event.EventHandler;
import be.ugent.systemdesign.ligplaats.domain.BerthWorkerState;
import org.springframework.beans.factory.annotation.Autowired;


public interface BerthService {


    ReserveBerthResponse reserveBerth(Double size, String vesselId);
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
    void sendshipReady(Integer berthId) throws Exception;
    void sendDockReady(String vesselId) throws Exception;
}

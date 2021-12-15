package be.ugent.systemdesign.ligplaats.application.command;

import be.ugent.systemdesign.ligplaats.application.BerthService;
import be.ugent.systemdesign.ligplaats.application.ResponseStatus;
import ch.qos.logback.core.util.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandHandler {
    @Autowired
    BerthService berthService;

    public ReserveBerthResponse handelReserveBerth(Double size, String vesselId) throws Exception {
        try{
            //berthService.reserveBerth(size,vesselId);
            return berthService.reserveBerth(size, vesselId);
        }catch (Exception e){
            throw new Exception("could not reserve berth.");
        }

    }
    public void handelUndoReservation(String vesselId) throws Exception {
        try{
            berthService.undoReservation(vesselId);
        }catch (Exception e){
            throw new Exception("could not reserve berth.");
        }
    }


}

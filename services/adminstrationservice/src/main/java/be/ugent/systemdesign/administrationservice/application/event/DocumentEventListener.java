package be.ugent.systemdesign.administrationservice.application.event;

import be.ugent.systemdesign.administrationservice.domain.NewContainerListEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class DocumentEventListener {

    private static final Logger log = LoggerFactory.getLogger(DocumentEventListener.class);

    @Autowired
    EventDispatcher eventDispatcher;

//    @Async
////    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    @EventListener
//    public void handleNewContainerListEvent(NewContainerListEvent event){
//        log.info("handle newcontainerevent created at {} with {} containers", event.getCreatedAt(), event.getContainerList().size());
//        eventDispatcher.publishNewContainerListEvent(event);
//
//    }
}

package be.ugent.systemdesign.administrationservice.application.query;

import java.util.List;

public interface DocumentQuery {
    public List<DocumentReadModel> generateDocumentsForVessel(String vesselId);
}

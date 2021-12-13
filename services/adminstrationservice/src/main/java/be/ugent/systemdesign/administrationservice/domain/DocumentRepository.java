package be.ugent.systemdesign.administrationservice.domain;

import java.util.List;

public interface DocumentRepository {
    public Document findOne(Integer id);
    public Document save(Document _d);
    public List<Document> findAllDocumentsForVessel(String vesselId);
    public void delete(Document _d);
}

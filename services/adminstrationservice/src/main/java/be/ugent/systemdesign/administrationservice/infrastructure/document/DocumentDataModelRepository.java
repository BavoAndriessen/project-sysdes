package be.ugent.systemdesign.administrationservice.infrastructure.document;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentDataModelRepository extends JpaRepository<DocumentDataModel, Integer> {
    List<DocumentDataModel> findByVesselId(String vesselId);
}

package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CommandDataModelRepository<T extends DomainCommandDataModel> extends JpaRepository<T, Integer> {
}

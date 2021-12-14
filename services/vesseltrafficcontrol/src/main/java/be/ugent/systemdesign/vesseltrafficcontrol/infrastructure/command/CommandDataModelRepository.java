package be.ugent.systemdesign.vesseltrafficcontrol.infrastructure.command;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandDataModelRepository<T extends DomainCommandDataModel> extends JpaRepository<T, Integer> {
}

package be.ugent.systemdesign.ligplaats.domain;

import org.springframework.data.jdbc.repository.query.Query;

import java.util.List;

public interface BerthRepository {
    public Berth findById(Integer id) throws Exception;
    public void save(Berth _b) throws Exception;
    public List<Berth> findAllThatAreReady();
    public List<Berth> findAllThatAreAvailable();
    public List<Berth> findAllBySizeAndState(Double size, String state);
    public Berth findByVesselId(String vesselId);
    public void flushRepo();
    public void fillRepository();
    public List<Berth> findAll();
}

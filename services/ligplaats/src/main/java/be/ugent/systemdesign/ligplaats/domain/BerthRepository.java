package be.ugent.systemdesign.ligplaats.domain;

import java.util.List;

public interface BerthRepository {
    public Berth findById(Integer id) throws Exception;
    public void save(Berth _b) throws Exception;
    public List<Berth> findAllThatAreReady();
    public List<Berth> findAllThatAreAvailable();
    public List<Berth> findAllBySizeAndState(Double size, String state);
    public Berth findByVesselId(String vesselId);
}

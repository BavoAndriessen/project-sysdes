package be.ugent.systemdesign.kapiteinsdienst.domain;

public interface VesselRepository {

    public void save(Vessel vessel);
    public Vessel findById(String vesselId);
    public void deleteById(String vesselId);
    public void saveAndFlush(Vessel vessel);
}

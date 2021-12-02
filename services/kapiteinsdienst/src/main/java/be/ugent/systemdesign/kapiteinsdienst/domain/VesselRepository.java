package be.ugent.systemdesign.kapiteinsdienst.domain;

public interface VesselRepository {

    public void save(Vessel vessel);
    public Vessel findById(Integer vesselNumber);
}

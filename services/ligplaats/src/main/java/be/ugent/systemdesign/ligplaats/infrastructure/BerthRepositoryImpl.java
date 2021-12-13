package be.ugent.systemdesign.ligplaats.infrastructure;


import be.ugent.systemdesign.ligplaats.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BerthRepositoryImpl implements BerthRepository {

    @Autowired
    BerthDataModelRepository berthDMRepo;


    @Override
    public Berth findById(Integer id) throws Exception {
        //hulp methode voor debug doeleinde

        BerthDataModel dm;
        try {
            dm = (BerthDataModel) berthDMRepo.findByBerthId(id);
            System.out.println("found worker at berth with id -> "+id);
        }catch (Exception e){
            throw new Exception("could not find berth with id: " + id);
        }

        return mapToBerth(dm);
    }

    @Override
    public void save(Berth _b) throws Exception {

        try{
            berthDMRepo.save(mapToBerthDataModel(_b));
        }catch (Exception e){
            String message = "could not save berth with berthId{" + _b.getBerthId() + "}\n";
            throw new Exception(message);
        }

    }


    @Override
    public List<Berth> findAllThatAreReady() {

        return berthDMRepo.findByState(BerthState.READY.name())
                .stream()
                .map(elt -> mapToBerth(elt))
                .collect(Collectors.toList());
    }

    @Override
    public List<Berth> findAllThatAreAvailable() {


        return berthDMRepo.findByState(BerthState.AVAILABLE.name())
                .stream()
                .map(elt -> mapToBerth(elt))
                .collect(Collectors.toList());
    }

    @Override
    public List<Berth> findAllBySizeAndState(Double size, String state) {

        return berthDMRepo.findBySizeAndState(size, state)
                .stream()
                .map(elt -> mapToBerth(elt))
                .collect(Collectors.toList());
    }

    @Override
    public Berth findByVesselId(String vesselId)
    {

        return mapToBerth(berthDMRepo.findByVesselId(vesselId));
    }

    private Berth mapToBerth(BerthDataModel bdm){
        Berth b = new Berth(
                            bdm.getBerthId(),
                            bdm.getSize(),
                            BerthState.valueOf(bdm.getState()),
                            bdm.getBerthNumber(),
                            new BerthWorker(
                                            bdm.getWorkerId(),
                                            BerthWorkerState.valueOf(bdm.getWorkerState()),
                                            bdm.getBerthId()),
                            bdm.getDockReady(),
                            bdm.getVesselId()
                            );

        return b;
    }
    private BerthDataModel mapToBerthDataModel(Berth b){
        BerthDataModel bm = new BerthDataModel(
                b.getBerthId(),
                b.getSize(),
                b.getState(),
                b.getBerthNumber(),
                b.isDockReady(),
                b.getVesselId(),
                b.getWorker().getId(),
                b.getWorker().getState()
        );

        return bm;
    }
    public void flushRepo(){
        berthDMRepo.flush();
    }

}

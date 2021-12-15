package be.ugent.systemdesign.ligplaats.infrastructure;


import be.ugent.systemdesign.ligplaats.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        return berthDMRepo.findAllBySizeAndState(size, state)
                .stream()
                .map(elt -> mapToBerth(elt))
                .collect(Collectors.toList());
    }

    @Override
    public Berth findByVesselId(String vesselId)
    {
        System.out.println("found: " + vesselId);
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
                b.getState().name(),
                b.getBerthNumber(),
                b.isDockReady(),
                b.getVesselId(),
                b.getWorker().getId(),
                b.getWorker().getState().name()
        );

        return bm;
    }
    public void flushRepo(){
        berthDMRepo.flush();

    }

    @Override
    public void fillRepository() {

        berthDMRepo.flush();
        BerthDataModel b = new BerthDataModel(1, 4.0, BerthState.AVAILABLE.name(), 1, true,"",1, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b);
        BerthDataModel b2 = new BerthDataModel(2, 4.0, BerthState.AVAILABLE.name(), 2, true,"",2, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b2);
        BerthDataModel b3 = new BerthDataModel(3, 6.0, BerthState.AVAILABLE.name(), 3, true,"",3, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b3);
        BerthDataModel b4 = new BerthDataModel(4, 6.0, BerthState.AVAILABLE.name(), 4, true,"",4, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b4);
        BerthDataModel b5 = new BerthDataModel(5, 8.0, BerthState.AVAILABLE.name(), 5, true,"",5, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b5);
        BerthDataModel b6 = new BerthDataModel(6, 8.0, BerthState.AVAILABLE.name(), 6, true,"",6, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b6);
        BerthDataModel b7 = new BerthDataModel(7, 10.0, BerthState.AVAILABLE.name(), 7, true,"",7, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b7);
        BerthDataModel b8 = new BerthDataModel(8, 10.0, BerthState.AVAILABLE.name(), 8, true,"",8, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b8);
        BerthDataModel b9 = new BerthDataModel(9, 12.0, BerthState.AVAILABLE.name(), 9, true,"",9, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b9);
        BerthDataModel b10 = new BerthDataModel(10, 12.0, BerthState.AVAILABLE.name(), 10, true,"",10, BerthWorkerState.AVAILABLE.name());
        berthDMRepo.save(b10);
    }


    @Override
    public List<Berth> findAll() {
        return berthDMRepo.findAll().stream()
                .map(elt -> mapToBerth(elt))
                .collect(Collectors.toList());
    }
}

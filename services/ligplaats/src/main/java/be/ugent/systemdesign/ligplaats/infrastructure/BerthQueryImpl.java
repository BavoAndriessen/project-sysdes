package be.ugent.systemdesign.ligplaats.infrastructure;

import be.ugent.systemdesign.ligplaats.application.query.BerthQuery;
import be.ugent.systemdesign.ligplaats.application.query.BerthRealModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BerthQueryImpl implements BerthQuery {
    @Autowired
    BerthDataModelRepository repo;

    @Override
    public BerthRealModel generateLocationOfBoat(String vesselId) {
        System.out.println("methode invoked after being called via REST");
        return mapToBerthRealModel(repo.findByVesselId(vesselId));
    }

    private BerthRealModel mapToBerthRealModel(BerthDataModel b){
        BerthRealModel r = new BerthRealModel(
                b.getBerthId(),
                b.getBerthNumber(),
                b.getVesselId()
        );
        return r;
    }
}

package be.ugent.systemdesign.ligplaats;

import be.ugent.systemdesign.ligplaats.API.REST.BerthViewModel;
import be.ugent.systemdesign.ligplaats.API.messaging.Channels;
import be.ugent.systemdesign.ligplaats.application.BerthService;
import be.ugent.systemdesign.ligplaats.application.query.BerthQuery;
import be.ugent.systemdesign.ligplaats.application.query.BerthRealModel;
import be.ugent.systemdesign.ligplaats.domain.*;
import be.ugent.systemdesign.ligplaats.infrastructure.BerthDataModel;
import be.ugent.systemdesign.ligplaats.infrastructure.BerthDataModelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@EnableBinding(Channels.class)
@SpringBootApplication
public class LigplaatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LigplaatsApplication.class, args);
	}
	@Bean
	CommandLineRunner testBerthDataModelRepo(BerthDataModelRepository repo) {
		return (args) -> {
			BerthDataModel b;
			List<BerthDataModel> l = new ArrayList<BerthDataModel>();
			for(int i=0; i<10;i++){
				b = new BerthDataModel(
							i,
						(i + 1) *2.0,
							BerthState.AVAILABLE,
							i+1,
							true,
							"ship-"+i,
							i+2,
						BerthWorkerState.AVAILABLE);
				l.add(b);
			}
			System.out.println("started testing BerthDataModelRepository:");
			l.forEach(i -> repo.save(i));
			System.out.println("###testing save:###");
			List<BerthDataModel> testFindAll =  repo.findAll();
			testFindAll.forEach(elt -> System.out.println(elt.toString()));
			System.out.println("###testing findBySizeAndState():#####");

			List<BerthDataModel> testFindBySizeAndState = repo.findBySizeAndState(20.0, BerthState.AVAILABLE.name());
			System.out.println("found the following:");
			testFindBySizeAndState.forEach(elt-> System.out.println(elt.toString()));
			System.out.println("###testing findByBerthId(9):#####");
			BerthDataModel findById = repo.findByBerthId(9);
			System.out.println("found the following:");
			System.out.println(findById.toString());
			System.out.println("###testing findByVesselId(\"ship-7\"):#####");
			BerthDataModel findByVesselId = repo.findByVesselId("ship-7");
			System.out.println("found the following:");
			System.out.println(findByVesselId.toString());
			System.out.println("###testing findAll():#####");
			List<BerthDataModel> findAll = repo.findAll() ;
			System.out.println("found the following:" + findAll.size()+ " elements");
			System.out.println("###testing findByStateId(AVAILABLE):#####");
			List<BerthDataModel> findByState = repo.findByState(BerthState.AVAILABLE.name());
			System.out.println("found the following:" + findByState.size()+ " elements");
		};
	}
	@Bean
	CommandLineRunner testBerthRepository(BerthRepository repo) {
		return (args) -> {
			Berth b;
			List<Berth> l = new ArrayList<Berth>();
			for(int i=0; i<10;i++){
				b = new Berth(
						i,
						(i + 1) *2.0,
						BerthState.AVAILABLE,
						i+1,
						new BerthWorker(i+2,BerthWorkerState.AVAILABLE,i),
						true,
						"ship-"+i
						);
				l.add(b);
			}
			System.out.println("started testing BerthRepository:");
			System.out.println("###testing save:###");
			l.forEach(i -> {
				try {
					repo.save(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			Berth testFindById =  repo.findByVesselId("ship-1");
			System.out.println(testFindById.toString());

		};
	}
	@Bean
	CommandLineRunner testBerthService(BerthService service, BerthDataModelRepository repo){
		return (args -> {
			repo.flush();
			BerthDataModel b;
			List<BerthDataModel> l = new ArrayList<BerthDataModel>();
			for(int i=0; i<5;i++){
				b = new BerthDataModel(
						i,
						(i + 1) *2.0,
						BerthState.AVAILABLE,
						i+1,
						true,
						"",
						i+2,
						BerthWorkerState.AVAILABLE);
				l.add(b);
			}
			System.out.println("started testing BerthDataModelRepository:");
			l.forEach(i -> repo.save(i));
			l.forEach(i -> System.out.println(i.toString()));
			System.out.println("reserving the berth:");
			service.reserveBerth(10.0, "ship-10");
			System.out.println("after calling reserveBerth:================================");
			System.out.println(repo.findByVesselId("ship-10").toString());
			System.out.println("==============================================================");
			System.out.println("test setBerthReady:");
			System.out.println("before setting the berth ready, the status was: " + repo.findByVesselId("ship-10").getState());
			service.setBerthReady("ship-10");
			System.out.println("after setting the berth ready the status is:");
			System.out.println(repo.findByVesselId("ship-10").getState());
			//repo.flush();
			System.out.println("test setWorkerAtBerthReady:");
			BerthDataModel test =  repo.findByVesselId("ship-10");
			test.setWorkerState(BerthWorkerState.BUSY.name());
			repo.save(test);
			System.out.println("before setting the Worker ready, the status was: " + repo.findByVesselId("ship-10").getWorkerState());
			service.setWorkerAtBerthAvailable(repo.findByVesselId("ship-10").getBerthId());

			System.out.println("after setting the worker ready the status is:");
			System.out.println(repo.findByVesselId("ship-10").getWorkerState());

			System.out.println("test undo reservation for vessel with id ship-10");
			System.out.println("un-reserving the berth:");
			System.out.println("before calling undoReservation:================================");
			System.out.println(repo.findByVesselId("ship-10").toString());
			System.out.println("==============================================================");
			service.undoReservation("ship-10");
			System.out.println("after calling undoReservation:================================");
			System.out.println(repo.findByBerthId(4));
			System.out.println("==============================================================");

		});
	}
	/*
	@Bean
	CommandLineRunner testBerthQuery(BerthQuery query, BerthDataModelRepository repo){
		return (args -> {
			repo.flush();
			BerthDataModel b;
			List<BerthDataModel> l = new ArrayList<BerthDataModel>();
			for(int i=0; i<5;i++){
				b = new BerthDataModel(
						i,
						(i + 1) *2.0,
						BerthState.AVAILABLE,
						i+1,
						true,
						"",
						i+2,
						BerthWorkerState.AVAILABLE);
				l.add(b);
			}
			l.get(1).setVesselId("ship-10");
			System.out.println("started testing BerthQuery:");
			l.forEach(i -> repo.save(i));
			//l.forEach(i -> System.out.println(i.toString()));
			System.out.println("getting vessel location:");
			BerthRealModel model =  query.generateLocationOfBoat("ship-10");
			System.out.println("location is:================================");
			System.out.println("berth number -> " + model.getBerthNumber());
			System.out.println("==============================================================");

			//repo.flush();
		});
	} */
}

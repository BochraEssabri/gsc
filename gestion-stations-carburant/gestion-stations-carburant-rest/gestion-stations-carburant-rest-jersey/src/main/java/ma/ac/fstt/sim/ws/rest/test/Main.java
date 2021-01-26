package ma.ac.fstt.sim.ws.rest.test;

import java.util.List;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.generated.ws.client.CarburantCrud;
import ma.ac.fstt.sim.generated.ws.client.CarburantCrudImplService;
import ma.ac.fstt.sim.generated.ws.client.CarburantDto;


public class Main {
	private static final Logger logger = Log.getLogger(Main.class);
	
	public static void main(String[] args) {

//		Carb demoImplService = new DemoImplService();
//		Demo demo = demoImplService.getDemoImplPort();
//		
//		Carburant carburant = demo.findCarburantById(2);
		//System.out.println(carburant.getNomCarburant());
		try {
			CarburantCrudImplService carburantCrudImplService = new CarburantCrudImplService();
			CarburantCrud carCrudPort = carburantCrudImplService.getCarburantCrudImplPort();
			CarburantDto car = carCrudPort.findCarburantById(2);
			logger.info("||"+car.getIdCarburant() +" || "+car.getNomCarburant() + " || "+car.getDescription()+"||");
			/*List<Carburant> list = carCrudPort.getAllCarburants();
			logger.info("====================");

			logger.info("nom:"+ list.get(0).getNomCarburant());

			logger.info("nom:"+ list.get(0).getDescription());
			
			logger.info("====================");*/
				
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}

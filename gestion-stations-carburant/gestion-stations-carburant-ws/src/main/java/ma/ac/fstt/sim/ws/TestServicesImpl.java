package ma.ac.fstt.sim.ws;

import javax.jws.*;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;
import ma.ac.fstt.sim.commun.ressoureces.Messages;
@WebService(endpointInterface = "ma.ac.fstt.sim.ws.TestServices")
public class TestServicesImpl implements TestServices {

	private static final Logger logger = Log.getLogger(TestServicesImpl.class);
		
	@Override
	public String testerServices() {
		logger.info(Messages.MSG_INVOCATION_TEST);
		
		return Messages.MSG_SERVICE_OK;
	}


	

	

}

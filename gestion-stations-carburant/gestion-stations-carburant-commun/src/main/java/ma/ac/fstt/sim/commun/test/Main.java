package ma.ac.fstt.sim.commun.test;

import org.apache.log4j.Logger;

import ma.ac.fstt.sim.commun.log.Log;

public class Main {
	private static final Logger logger = Log.getLogger(Main.class);

	public static final String TestLog	= "Testing";
	
	public static void main(String[] args) {
		logger.info(TestLog);
		
	}

}

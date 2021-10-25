package logfile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j {
	
	static Logger logger= LogManager.getLogger(Log4j.class);
	
	public static void main(String[] args) {
		
		System.out.println("\n Hello \n");
		
		logger.info(" This is information message");
		logger.error(" This is an error message");
		logger.warn(" This is a warning message");
		logger.fatal(" This is a fatal message");
		logger.trace("This is a trace message");
		
		System.out.println("\n completed");
	}

}

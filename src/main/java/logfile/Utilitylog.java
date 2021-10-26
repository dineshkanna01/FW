package logfile;

import org.apache.log4j.Logger;

public class Utilitylog {

	//logger instance
		Logger log=null;
		
		//constructor
		public Utilitylog(String classname){
			log=Logger.getLogger(classname);
		}
		
		
		
		public void info(String msg) {
			log.info(msg);
		}

		public void warn(String msg) {
			log.warn(msg);
		}

		
		public void debug(String msg) {
			log.debug(msg);
		}
		
		public void error(String msg)
		{
			log.error(msg);
		}
		public void fatal(String msg)
		{
			log.fatal(msg);
		}
}

package logfile;

public class LogUtility {

	public static void main(String[] args) 
	{
		
		Utilitylog logutility = new Utilitylog(LogUtility.class.getName());
		
		logutility.info("info send");
		
		System.out.println("completd");
		
		
		logutility.warn("problems");
		System.out.println("over");
		
		logutility.error("errors");
		System.out.println("mistakes");
		
		logutility.fatal("like errors");
		System.out.println("problems");
		
		
		
	}
}

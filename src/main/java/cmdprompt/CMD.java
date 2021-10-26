package cmdprompt;

import java.io.IOException;
import java.io.PrintWriter;

public class CMD {
	
	public static void main(String[] args) throws Exception {
		String [] command = {"cmd"};
		Process p;
		try {
			p=Runtime.getRuntime().exec(command);
			
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.err)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("allure serve C:\\Users\\Dinesh.Kanna\\eclipse-workspace\\IGT\\allure-results");
			
			stdin.close();
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

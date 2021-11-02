package base;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hpsf.Date;



public class Mail {
	
	static String key,data,from,to,password;
	static String[] AllToAddress;
	static String host="smtp-mail.outlook.com";
	
	public void getData() throws Exception {
		File prop= new File("config\\credentials.properties");
		FileInputStream input=new FileInputStream(prop);
		Properties pro=new Properties();
		pro.load(input);
		
		Enumeration value = pro.keys();
		
		while (value.hasMoreElements()) {
			key=(String) value.nextElement();
			data=pro.getProperty(key);
			if (key.equals("to")) {
				to=data;
			}
			if (key.equals("from")) {
				from=data;
			}
			if (key.equals("password")) {
				password=data;
			}
		}
		AllToAddress = to.split(",");
	}

	public void mail() throws Exception {
		getData();
		
		try {
			Properties sysPro=System.getProperties();
			sysPro.put("mail.smtp.starttls.enable", "true");
			sysPro.put("mail.smtp.starttls.host", "host");
			sysPro.put("mail.smtp.starttls.user", "from");
			sysPro.put("mail.smtp.starttls.password", "password");
			sysPro.put("mail.smtp.starttls.port", "465");
			sysPro.put("mail.smtp.starttls.auth", "true");
			
			Session session= Session.getInstance(sysPro);
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			
			InternetAddress[] ia=new InternetAddress[AllToAddress.length];
			
			for (int i = 0; i < AllToAddress.length; i++) {
				ia[i]=new InternetAddress(AllToAddress[i]);
			}
			
			for (int j = 0; j < AllToAddress.length; j++) {
				message.addRecipient(Message.RecipientType.TO, ia[j]);
			}
			
			Date dt=new Date();
			SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
			String stDate=format.format(dt);
			
			message.setSubject("Execution report on"+stDate);
			BodyPart messageBodyPart=new MimeBodyPart();
			messageBodyPart.setText("Hi \n"
					+ "Updated reports are attached. Kindly check..");
			Multipart multipart= new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			File file=new File("C:\\Users\\Dinesh.Kanna\\eclipse-workspace\\IGT\\test-output\\old");
			
			messageBodyPart = new MimeBodyPart();
			DataSource source=new FileDataSource(file.getAbsolutePath());
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Report "+stDate+".json");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			
			Transport trans= session.getTransport("smtp");
			trans.connect(host, from, password);
			trans.sendMessage(message, message.getAllRecipients());
			
			for (String address : AllToAddress) {
				System.out.println("Mail sent "+address);
			}
			trans.close();
		} catch (AddressException ae) {
			System.out.println("Address exception"+ae);
		}catch (MessagingException me) {
			System.out.println("Messaging exception"+me);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}

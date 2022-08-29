package share;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import database.cached.UserDatabase;
import gui.frame.MainFrame;
import start.StartApp;

public class JavaMailUtil
{
	// --- Single Instance ---//
	
	private static JavaMailUtil singleInstance;
		
	public static JavaMailUtil getInstance()
	{
		if(singleInstance == null)
		{
			singleInstance = new JavaMailUtil();
		}
		return singleInstance;
	}
	
	private JavaMailUtil() 
	{
		super();
	}

	public void sendMail(String recepient)
	{
		System.out.println("Prepearing to send mail.");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = UserDatabase.getInstance().getEmail();
		String pass = UserDatabase.getInstance().getPasswrod();
		
		System.out.println("My Account : "+myAccountEmail);
		System.out.println("Password : "+pass);
		
		Session sission = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, pass);
			}
		});
		
		Message message = prepareMessage(sission, myAccountEmail, recepient);
		boolean flag = false;
		try {
			Transport.send(message);
			flag = true;
		}
		catch (AuthenticationFailedException e) {

			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Wrong Email Or Password\n Edit User Data Email Password");
//			e.printStackTrace();
		} 
		catch (MessagingException e) {

			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Intenet Connection Lost.\n Can't Send Mail");
//			e.printStackTrace();
		}
		if(flag)
		{

			JOptionPane.showMessageDialog(MainFrame.getInstance().frame, "Mail Sent SuccessFully. ");
		}
//		System.out.println("Message Sent Successfully");
	}

	private static Message prepareMessage(Session sission, String myAccountEmail, String recepient) {
		
		try {
			Message message = new MimeMessage(sission);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Time Table : The Class Schedule");
			
			Multipart emailContent = new MimeMultipart();
			
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("Hey there. \n Your new Time Table Has Schedulled. Have Look Into it.\n\n\nNote: This is System Generated Email");
			
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile("TimeTable.pdf");
			
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			
			message.setContent(emailContent);
			
			return message;
		} 
		catch (Exception e) 
		{
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, e);
		} 
		return null;
	}

}

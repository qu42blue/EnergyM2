
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

public class EmailSender {

	private static final Logger LOGGER = Logger.getLogger(EmailSender.class.getName());

	private final EmailConfiguration emailConfiguration = new EmailConfiguration();

	public void send(String emailTo, String subject, String bodyMessage, String filename) {

		readConfigurationParameters();

		Session session = Session.getDefaultInstance(new UserEmailProperties(emailConfiguration), new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConfiguration.getUserId(), emailConfiguration.getEmailFromPassword());
			}
		});

		Message message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(emailConfiguration.getEmailFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			message.setSubject(subject);
			
			
			BodyPart messageBodyPart = new MimeBodyPart();

	        messageBodyPart.setText(bodyMessage);

	        Multipart multipart = new MimeMultipart();

	        multipart.addBodyPart(messageBodyPart);

	        messageBodyPart = new MimeBodyPart();

	        messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(filename)));

	        messageBodyPart.setFileName(filename);

	        multipart.addBodyPart(messageBodyPart);

	        message.setContent(multipart);
	        
			Transport.send(message);
		}
		catch (MessagingException e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	private void readConfigurationParameters() {
		emailConfiguration.setAuth("true");
		emailConfiguration.setHost("smtp.gmail.com");
		emailConfiguration.setPort("587");
		emailConfiguration.setTls("true");
		emailConfiguration.setUserId("emailtester487");
		emailConfiguration.setEmailFrom("emailtester487@gmail.com");
		emailConfiguration.setEmailFromPassword("Tester123");
	}
}
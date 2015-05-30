

import java.util.Properties;

@SuppressWarnings("serial")
public class UserEmailProperties extends Properties {
	public UserEmailProperties(EmailConfiguration emailConfiguration) {

		put("mail.smtp.host", emailConfiguration.getHost());
		put("mail.smtp.port", emailConfiguration.getPort());
		put("mail.smtp.auth", emailConfiguration.getAuth());
		put("mail.smtp.starttls.enable", emailConfiguration.getTls());
		put("mail.smtp.ssl.trust", "smtp.gmail.com");
	}
}

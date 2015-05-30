

public class EmailConfiguration {
	private String host;
	private String port;
	private String auth;
	private String tls;
	private String userId;
	private String emailFrom;
	private String emailFromPassword;

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setTls(String tls) {
		this.tls = tls;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public void setEmailFromPassword(String emailFromPassword) {
		this.emailFromPassword = emailFromPassword;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getAuth() {
		return auth;
	}

	public String getTls() {
		return tls;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public String getEmailFromPassword() {
		return emailFromPassword;
	}
}

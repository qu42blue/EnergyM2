
public class Apps {

	private String title;
	private String developerContact;
	
	public Apps(String title, String developerContact) {
		this.title = title;
		this.developerContact = developerContact;
	}
	public String getTitle() {
		return title;
	}
	public String getDeveloperContact() {
		return developerContact;
	}
	
	@Override
	public String toString() {
		return "TITLE: "+getTitle()+". DEVELOPER CONTACT: "+getDeveloperContact();
	}
}

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class WebScraper {

	private static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean exit = false;
		WebParser webParser = new WebParser();
		
		
		while(true) {
			System.out.println("-------- MENU -------");
			System.out.println("1. Read links");
			System.out.println("2. Print apps data");
			System.out.println("3. Export apps data to csv");
			System.out.println("4. Send exported file to email");
			System.out.println("5. Exit");
			
			System.out.println();
			System.out.print("Enter option: ");
			
			String userInput = input.nextLine();
			
			switch(userInput) {
				case "1":
					try {
						// clear previous content
						webParser.getAppsData().clear();
						// run parser for category topselling_free
						webParser.run("https://play.google.com/store/apps/category/PRODUCTIVITY/collection/topselling_free");
						// run parser for category topselling_paid
						webParser.run("https://play.google.com/store/apps/category/PRODUCTIVITY/collection/topselling_paid");
						
					} catch (IllegalAccessException | InvocationTargetException
							| NoSuchMethodException | SecurityException | IOException e) {
						e.printStackTrace();
					}		
					System.out.println("Data parsed successfully");
					break;
				case "2":
					System.out.println();
					System.out.println("APPS DATA PRINTOUT");
					for(Apps apps : webParser.getAppsData()) {
						System.out.println(apps.toString());
					}
					System.out.println();
					break;
				case "3":
					webParser.exportToCSV();
					break;
				case "4":
					System.out.println("Enter email: ");
					String email = input.nextLine();
					webParser.exportToCSV();
					new EmailSender().send(email, "Web scraper", "", "output.csv");
					System.out.println("Email sent successfully");
					break;
				case "5":
					exit = true;
					break;
				default:
					System.out.println("Wrong input.");
					break;
				
			}
			
			if(exit) {
				System.out.println("Exiting program...");
				break;
			}
		}
	}
}

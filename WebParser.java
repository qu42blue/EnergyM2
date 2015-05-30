import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import au.com.bytecode.opencsv.CSVWriter;


public class WebParser {
		
		private List<Apps> appsData;

		public WebParser() {
			// initialize list of apps
			appsData = new ArrayList<>();
		}

		// run method will parse apps info from url and store it into list
		public void run(String url) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
			// parse data from url using jsoup
			getDataFromUrl(url);
		}

		// parse data from url using jsoup
		private void getDataFromUrl(String url) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
			System.out.println("Parsing url "+url);
			
			Document document = Jsoup.connect(url).get();

			// get all links
			Elements links = document.select("a.title"); 
			// iterate through links and connect to new site
			for (Element link : links) {
				String absUrl = link.absUrl("href");
				// connect to new link
				Document documentChild = Jsoup.connect(absUrl).get();
				// get title of the current link
				String title = link.attr("title");
				
				Elements contact = documentChild.select("a.dev-link");
				String developerEmail = "";
				
				for(Element element : contact) {
					String mail = element.absUrl("href");
					if(mail.contains("mailto")) {
						developerEmail = mail.split(":")[1];
					}
					
				}
				Apps apps = new Apps(title, developerEmail);
				System.out.println(apps.toString());
				appsData.add(apps);
				
			}
		}
		

		public void exportToCSV() {
			// prepare data to write to output csv
			List<String[]> data = new ArrayList<>();
			
			for(Apps apps : getAppsData()) {
				
				String[] dataRow = new String[2];
				dataRow[0] = apps.getTitle();
				dataRow[1] = apps.getDeveloperContact();
				
				data.add(dataRow);
			}
			String csv = "output.csv";
	    	CSVWriter writer;
			try {
				writer = new CSVWriter(new FileWriter(csv),',',CSVWriter.NO_QUOTE_CHARACTER);
				writer.writeAll(data);
		    	writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Finished data export");
		}
		
		public List<Apps> getAppsData() {
			return appsData;
		}
}
 

public class Main {

	public static void main(String[] args) {
		
		//Scrape Datas from the TSV-Website and Write it into the Database
		WebConnection connect = new WebConnection();
		connect.scrape();
		
		//Start Window
		MyController controller = new MyController();
		controller.startGUI();
	}

}

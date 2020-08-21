
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class WebConnection {
	
	final String url = "https://www.handball-bargteheide.de/index.php/2015-05-25-14-49-47/kampfgerichtsplan?start=1";
	
	public void scrape() {
		
		//DBConnection recreate = new DBConnection();
		//recreate.recreatetable();
		
		try {
			
			final Document document = Jsoup.connect(url).get();
			
			for(Element row: document.select("div.content.clearfix tr")) {
				if(row.select("td:nth-child(7)").text().equals("")) {
					continue;
				}
				else {
					
					final String kgclass = row.select("td:nth-child(1)").text();
					final String kgdate = row.select("td:nth-child(2)").text();
					final String kgtime = row.select("td:nth-child(3)").text();
					final String kghome = row.select("td:nth-child(4)").text();
					final String kgguest = row.select("td:nth-child(5)").text();
					final String kghall = row.select("td:nth-child(6)").text();
					final String kgteam = row.select("td:nth-child(7)").text();
					
					DBConnection newgame = new DBConnection();
					
					if(newgame.isnotinDatabase(kgclass, kgdate, kgtime)) {
						if(kgteam.contains("H")) {
							newgame.addGame(kgclass, kgdate, kgtime, kghome, kgguest, kghall, "Herren");
						}else {
							newgame.addGame(kgclass, kgdate, kgtime, kghome, kgguest, kghall, kgteam);
						}
					}
					
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	
}

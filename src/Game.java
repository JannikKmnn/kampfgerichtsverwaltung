
public class Game {
	
	private int game_id;
	private String pclass; 
	private String game_date; 
	private String game_time; 
	private String hometeam;
	private String guestteam; 
	private String hall;
	private String kg_team;
	
	private String kg1;
	private String kg2;
	
	public Game(int game_id, String pclass, String game_date, String game_time, 
			String hometeam, String guestteam, String hall, String kg_team, String kg1, String kg2) {
		
		this.game_id = game_id;
		this.pclass = pclass;
		this.game_date = game_date;
		this.game_time = game_time;
		this.hometeam = hometeam;
		this.guestteam = guestteam;
		this.hall = hall;
		this.kg_team = kg_team;
		
		this.kg1 = kg1;
		this.kg2 = kg2;
		
	}
	
	public int getID() {
		return this.game_id;
	}
	public String getpClass() {
		return this.pclass;
	}
	public String getgamedate() {
		return this.game_date;
	}
	public String getgametime() {
		return this.game_time;
	}
	public String gethometeam() {
		return this.hometeam;
	}
	public String getguestteam() {
		return this.guestteam;
	}
	public String gethall() {
		return this.hall;
	}
	public String getKGteam() {
		return this.kg_team;
	}
	public String getKG1() {
		return this.kg1;
	}
	public String getKG2() {
		return this.kg2;
	}
	
}

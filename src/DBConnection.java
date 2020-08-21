

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class DBConnection {
	
	private String dburl = "jdbc:mysql://localhost:3306/kgbargteheide?useJDBCCompliantTimezoneShift=True&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
	private String dbuser = "root";
	private String dbpassword = "b4L!3?2urTm%f10#P";
	
	public void recreatetable() {
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			String query = "DROP TABLE game";
			
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			
			stmt.close();
			
			System.out.println("Table dropped.");
			
			query = "create table game" + 
					"(" + 
					"	id int primary key auto_increment," + 
					"    pclass varchar(30), " + 
					"    game_date varchar(30), " + 
					"    game_time varchar(30), " + 
					"    hometeam varchar(30), " + 
					"    guestteam varchar(50), " + 
					"    hall varchar(30), " + 
					"    kg_team varchar(30)," + 
					"    kg_1 varchar(30)," +
					"    kg_2 varchar(30)" +
					")";
			
			stmt = conn.createStatement();
			stmt.execute(query);
			
			stmt.close();
			
			System.out.println("Table created.");
			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
			
	}
	
	public void addGame(String pclass, String game_date, String game_time, String hometeam, String guestteam, String hall, String kg_team) {
		
		String query = "INSERT INTO game (pclass, game_date, game_time, hometeam, guestteam, hall, kg_team) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			System.out.println("Successfully connected to Database.");
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			preparedStatement.setString(1, pclass);
			preparedStatement.setString(2, game_date);
			preparedStatement.setString(3, game_time);
			preparedStatement.setString(4, hometeam);
			preparedStatement.setString(5, guestteam);
			preparedStatement.setString(6, hall);
			preparedStatement.setString(7, kg_team);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
			
	}
	
	public void changeGame(int key, String newvalue) {
		
		String query = "UPDATE game SET kg_team = ? WHERE id = ?";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, newvalue);
			stmt.setInt(2, key);
			
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Changed Datas.");
		
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	public ArrayList<Game> getGames(String kg_team) {
		
		ArrayList<Game> games = new ArrayList<Game>();
		
		String query = "SELECT * FROM game WHERE kg_team = ?";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, kg_team);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Game game = new Game(Integer.parseInt(rs.getString(1)), rs.getString(2), 
						rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), 
						rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10));
				games.add(game);
			}
			
			rs.close();
			stmt.close();
			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return games;
	}
	
	public ArrayList<String> getTeams() {
		
		ArrayList<String> Teams = new ArrayList<String>();
		
		String query = "SELECT kg_team FROM game";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			boolean flag;
			
			while(rs.next()) {
				flag = true;
				for(int i = 0; i <= Teams.size() - 1; i++) {
					if(Teams.get(i).toString().equalsIgnoreCase(rs.getString(1)) || rs.getString(1).length() > 6) {
						flag = false;
						break;
					}
				}
				if(flag) {
					Teams.add(rs.getString(1));
				}
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		Collections.sort(Teams);
		
		return Teams;
	}
	
	public void addTimecontroller(String kg_team, String game_date, String game_time, String name) {
		
		String query = "UPDATE game SET kg_1 = ? WHERE kg_team = ? AND game_date = ? AND game_time = ?";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, name);
			stmt.setString(2, kg_team);
			stmt.setString(3, game_date);
			stmt.setString(4, game_time);
			
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Added Timecontroller.");
		
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	public void addSecretary(String kg_team, String game_date, String game_time, String name) {
		
		String query = "UPDATE game SET kg_2 = ? WHERE kg_team = ? AND game_date = ? AND game_time = ?";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, name);
			stmt.setString(2, kg_team);
			stmt.setString(3, game_date);
			stmt.setString(4, game_time);
			
			stmt.executeUpdate();
			stmt.close();
			
			System.out.println("Added Secretary.");
		
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	public boolean isnotinDatabase(String pclass, String game_date, String game_time) {
		
		String query = "SELECT pclass, game_date, game_time FROM game WHERE pclass = ? AND game_date = ? AND game_time = ?";
		
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword)) {
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, pclass);
			stmt.setString(2, game_date);
			stmt.setString(3, game_time);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return false;
			}
			
			stmt.close();
			rs.close();
			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		System.out.println("Add new Value");
		return true;
		
	}

}

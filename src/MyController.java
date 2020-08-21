import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyController implements ActionListener{
	
	private MyWindow window;
	private ListSelectionModel model;
	
	public void startGUI() {
		window = new MyWindow(this);
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		@SuppressWarnings("rawtypes")
		JComboBox cb = (JComboBox)e.getSource();
		convertDatas((String)cb.getSelectedItem());
		
		String command = e.getActionCommand();
		
		if(command.equals("Änderungen speichern")) {
			
			System.out.println("Änderungen gespeichert");
			model = window.table.getSelectionModel();
			
			model.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}//else if(e.getActionCommand().equals("Aktualisieren")) {
			
		//}
		
	}
	
	public void convertDatas(String command) {
		
		DBConnection dbconnect = new DBConnection();
		
		ArrayList<Game> games = dbconnect.getGames(command);
		
		Object[][] data = new Object[games.size()][9];
		
		for(int i = 0; i < games.size(); i++) {
			data[i][0] = games.get(i).getpClass();
			data[i][1] = games.get(i).getgamedate();
			data[i][2] = games.get(i).getgametime();
			data[i][3] = games.get(i).gethometeam();
			data[i][4] = games.get(i).getguestteam();
			data[i][5] = games.get(i).gethall();
			data[i][6] = games.get(i).getKGteam();
			data[i][7] = games.get(i).getKG1();
			data[i][8] = games.get(i).getKG2();
		}
		
		window.updateTable(data);
	}

}

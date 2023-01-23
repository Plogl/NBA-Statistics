// Name: Michael Bonfiglio
// Email: mabonfiglio@wisc.edu

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NBAPlayerMapper {
	
	/**
	 * NBA player mapper that will run the NBA playerMapperFrontend for the user
	 * to search player's by their stats.
	 * @param args0
	 */
	public static void main(String[] args0) {
		Scanner sc = new Scanner(System.in);
		NBAPlayerLoader _instance = new NBAPlayerLoader();
		List<INBAPlayer> NBAList;
		try {
			//loads in NBAPlayer data
			NBAList = _instance.loadPlayers("src/players.xml");
			NBAPlayerMapperBackend backend = new NBAPlayerMapperBackend();
			
			for(INBAPlayer player: NBAList) {
				//adds player to backend
				backend.addPlayer(player);
			}
			
			//creates frontend
			NBAPlayerMapperFrontend mp = new NBAPlayerMapperFrontend(sc, backend);
			
			//runs command loop
			mp.runCommandLoop();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("program failed to execute.  "+e);
		}
	}
}

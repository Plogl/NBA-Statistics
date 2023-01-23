import java.io.FileNotFoundException;
import java.util.List;

/**
     * This method loads the list of NBA players from a XML file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
public interface INBAPlayerLoader {
	
	
	 List<INBAPlayer> loadPlayers(String filepathToXTML) throws FileNotFoundException;

}

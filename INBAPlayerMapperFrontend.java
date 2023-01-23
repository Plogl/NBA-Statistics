import java.util.*;

public interface INBAPlayerMapperFrontend {

      public void runCommandLoop();
	
      // to help make it easier to test the functionality of this program,
      // the following helper methods will help support runCommandLoop():
      public void displayPlayers(List<INBAPlayer> players);
      public void displayMainMenu(); 

      public void displayPoints(); 

      public void displayRebounds();

      public void displayAssists();

      public void displayBlocks();

      public void displaySteals();

}

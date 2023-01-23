import java.util.*;

//Instances of this interface implement the search and filter functionality
//of the NBAPlayer Mapper
public interface INBAPlayerMapperBackend {

    //adds a new player to the RB Tree
    public void addPlayer(INBAPlayer player);
    
    //Returns the number of players stored in the backend's databse
    public int getNumberOfPlayers();
    
    //Will discuss this with frontend later, wanted to implement
    //some form of score/points/rebound filter of our project 
    //is currently lacking complexity, message Oliver Bai 
    //or leave comments/email obai2@wisc.edu, couldn't reach them
    //in time
    public void setScoreFilter(int filterScore, boolean isLess);
    public void setReboundFilter(int filterRebound, boolean isLess);
    public void setAssistFilter(int filterAssist, boolean isLess);
    public void setBlockFilter(int filterBlock, boolean isLess);
    public void setStealFilter(int filterSteal, boolean isLess);
    
    //gets any existing filters, returns as string
    public ArrayList<INBAPlayer> getFilter();
    
    //resets any existing filters
    public void resetFilter();
    
    //searches through all players in backend database
    public List<INBAPlayer> searchByPlayerName(String name);

    //release this code would be redundant
    public void setFilter(String setPlayer);
    
    
    //considered adding search by statistics, but with filters should be enough
    //also considering changing stat filters to "searches" rather than filters
    
}

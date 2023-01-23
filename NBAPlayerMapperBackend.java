import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class NBAPlayerMapperBackend implements INBAPlayerMapperBackend{
	
	public ArrayList<INBAPlayer> listOfNBAPlayers = new ArrayList();
	public ArrayList<INBAPlayer> FilteredlistOfNBAPlayers = new ArrayList();
	
	protected class statRange{
		public IterableRedBlackTree statRBT;
		public int low;
		public int high;
		public String statName;
		
		public statRange(String stat) {
			this.low = 0;
			this.high = Integer.MAX_VALUE;
			this.statName = stat;
			this.statRBT = new IterableRedBlackTree();
			
		}
	}
	statRange points = new statRange("Points");
	statRange rebounds = new statRange("Rebounds");
	statRange assists = new statRange("Assists");
	statRange blocks = new statRange("Blocks");
	statRange steals = new statRange("Steals");
	
	
	
	
	NBAPlayerMapperBackend(){
		
	}
	public boolean isFiltered() {
		if(FilteredlistOfNBAPlayers.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public void addPlayer(INBAPlayer player) {
		listOfNBAPlayers.add(player);
		rebounds.statRBT.insert(player.getRebounds(), player);
		points.statRBT.insert(player.getPoints(), player);
		steals.statRBT.insert(player.getSteals(), player);
		blocks.statRBT.insert(player.getBlocks(), player);
		assists.statRBT.insert(player.getAssists(), player);
		
	}

	@Override
	public int getNumberOfPlayers() {
		return rebounds.statRBT.size;
	}

	@Override
	public void setScoreFilter(int filterScore, boolean isLess) {
		if (!isLess)
		{
			points.low = filterScore;
		}
		else if(isLess)
		{
			points.high = filterScore;
		}
		filtering(points);
	}

	@Override
	public void setReboundFilter(int filterRebound, boolean isLess) {
		if (!isLess)
		{
			rebounds.low = filterRebound;
		}
		else if(isLess)
		{
			rebounds.high = filterRebound;
		}
		filtering(rebounds);
	}

	@Override
	public void setAssistFilter(int filterAssist, boolean isLess) {
		if (!isLess)
		{
			assists.low = filterAssist;
		}
		else if(isLess)
		{
			assists.high = filterAssist;
		}
		filtering(assists);
	}

	@Override
	public void setBlockFilter(int filterBlock, boolean isLess) {
		if (!isLess)
		{
			blocks.low = filterBlock;
		}
		else if(isLess)
		{
			blocks.high = filterBlock;
		}
		filtering(blocks);
	}

	@Override
	public void setStealFilter(int filterSteal, boolean isLess) {
		if (!isLess)
		{
			steals.low = filterSteal;
		}
		else if(isLess)
		{
			steals.high = filterSteal;
		}
		filtering(steals);
	}

	public ArrayList<INBAPlayer> getFilter() {
		// TODO Auto-generated method stub
		return FilteredlistOfNBAPlayers;
	}

	@Override
	public void resetFilter() {
		
		FilteredlistOfNBAPlayers = new ArrayList();
		statRange points = new statRange("Points");
		statRange rebounds = new statRange("Rebounds");
		statRange assists = new statRange("Assists");
		statRange blocks = new statRange("Blocks");
		statRange steals = new statRange("Steals");
		
	}

	@Override
	public List<INBAPlayer> searchByPlayerName(String name) {
		ArrayList listNBAPlayers = new ArrayList();
		boolean filtered = this.isFiltered();
		for(INBAPlayer player: listOfNBAPlayers) {
			if(name.toLowerCase().contains(player.getName().toLowerCase())) {
				if(filtered) {
					if(FilteredlistOfNBAPlayers.contains(player)) {
						listNBAPlayers.add(player);
					}
				}
				else {
					listNBAPlayers.add(player);
				}
			}
		}
		FilteredlistOfNBAPlayers = listNBAPlayers;

		return FilteredlistOfNBAPlayers;
	}

	@Override
	public void setFilter(String setPlayer) {
		//realized not necessary
	}
	
	public void filtering(statRange stat) {

		ArrayList<INBAPlayer> listNBAPlayers = new ArrayList();

		boolean filtered = this.isFiltered();
//		stat.range = "";
//		stat.low = 1500;
//		stat.high = 1600;
//
//		if(stat.range.contains("<")) {
//			stat.low = num;
//		}
//
//		if(stat.range.contains(">")) {
//			stat.high = num;
//		}
		listNBAPlayers = stat.statRBT.filter(stat.high, stat.low, stat.statRBT.root);
		if(filtered) {
            for(int x = 0; x < listNBAPlayers.size(); x++) {
                INBAPlayer player = listNBAPlayers.get(x);
                if(!(FilteredlistOfNBAPlayers.contains(player))) {
                    listNBAPlayers.remove(player);
                    x--;
                }
            }
            FilteredlistOfNBAPlayers = listNBAPlayers;
        }
        else {
            FilteredlistOfNBAPlayers = listNBAPlayers;
        }

        Collections.sort(FilteredlistOfNBAPlayers , new Comparator<INBAPlayer>() {

            public int compare(INBAPlayer p1, INBAPlayer p2) {

                return p1.getName().compareTo(p2.getName());
            }
        });
		
		Collections.sort(FilteredlistOfNBAPlayers , new Comparator<INBAPlayer>() {

            public int compare(INBAPlayer p1, INBAPlayer p2) {
                
                return p1.getName().compareTo(p2.getName());
            }
        });
		
	}
	
	public static void main(String[] args) {
    	NBAPlayerMapperBackend backend = new NBAPlayerMapperBackend();
    	NBAPlayerLoader _instance = new NBAPlayerLoader();
    	List<INBAPlayer> listNBAPlayers;
    	try {
			listNBAPlayers = _instance.loadPlayers("src/players.xml");

			for(INBAPlayer player: listNBAPlayers) {
	    		backend.addPlayer(player);
			}
			backend.setScoreFilter(1700, false);
			System.out.println("hello boys");



		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
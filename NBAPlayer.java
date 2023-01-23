// Name: Michael Bonfiglio
// Email: mabonfiglio@wisc.edu

public class NBAPlayer implements INBAPlayer{

		int rebounds;
		int assists;
		int blocks;
		int steals;
		int points;
		String name;
		
	@Override
	/**
	 * gets player's rebounds
	 * 
	 * @return rebounds
	 */
	public int getRebounds() {
		// TODO Auto-generated method stub
		return rebounds;
	}

	@Override
	/**
	 * gets player's points 
	 * 
	 * @return points
	 */
	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}

	@Override
	/**
	 * gets player's assists 
	 * 
	 * @return assists
	 */
	public int getAssists() {
		// TODO Auto-generated method stub
		return assists;
	}

	@Override
	/**
	 * gets player's steals 
	 * 
	 * @return steals
	 */
	public int getSteals() {
		// TODO Auto-generated method stub
		return steals;
	}

	@Override
	/**
	 * gets player's blocks 
	 * 
	 * @return blocks
	 */
	public int getBlocks() {
		// TODO Auto-generated method stub
		return blocks;
	}

	@Override
	/**
	 * gets player's name
	 * 
	 * @return name
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	/**
	 * Sets player's rebounds
	 * 
	 * @param num - number of rebounds
	 */
	public void setRebounds(int num) {
		this.rebounds = num;
	}
	
	/**
	 * Sets player's steals
	 * 
	 * @param num - number of steals
	 */
	public void setSteals(int num) {
		this.steals = num;
	}
	
	/**
	 * Sets player's blocks
	 * 
	 * @param num - number of blocks
	 */
	public void setBlocks(int num) {
		this.blocks = num;
	}
	
	/**
	 * Sets player's assists
	 * 
	 * @param num - number of assists 
	 */
	public void setAssists(int num) {
		this.assists = num;
	}
	
	/**
	 * Sets player's points
	 * 
	 * @param num - number of points
	 */
	public void setPoints(int num) {
		this.points = num;
	}
	
	/**
	 * Sets player's name
	 * 
	 * @param name - name of player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public NBAPlayer(int points, int assists, int rebounds, int blocks, int steals, String name) {
		if(points < 0 || assists <0 || rebounds < 0 || blocks < 0|| steals < 0) {
			throw new IllegalArgumentException("Some stat was less than 0.");
		}
		this.points = points;
		this.assists = assists;
		this.rebounds =rebounds;
		this.blocks = blocks;
		this.steals = steals;
		this.name = name;
		
	}

}

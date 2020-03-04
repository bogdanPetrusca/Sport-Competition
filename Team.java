import java.util.ArrayList;
/**
 * 
 * @author PETRUSCA BOGDAN-MIHAI 323CB
 *
 */
public abstract class Team implements Observer{
	private String teamType;
	private String teamName;
	private String gender;
	private int numberOfPlayers;
	private double score;
	private int points;
	private int loc;
	ArrayList<Player> players;
	
	
	public abstract String toString();
	public Team(String teamType, String teamName, String gender, int numberOfPlayers) {
		this.teamType = teamType;
		this.teamName = teamName;
		this.gender = gender;
		this.points = 0;
		this.loc = 0;
		this.numberOfPlayers = numberOfPlayers;
		players = new ArrayList<Player>();
	}
	public int getLoc() {
		return loc;
	}
	public void setLoc(int nr) {
		this.loc = nr;
	}
	public int getPoints() {
		return points;
	}
	public void addPoints(int nr) {
		this.points += nr;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	
}
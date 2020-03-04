import java.util.ArrayList;
import java.util.Comparator;
/**
 * 
 * @author PETRUSCA BOGDAN MIHAI 323CB
 */
public class FootballTeam extends Team implements Visitable, Observer{
	
	public FootballTeam(String teamType, String teamName, String gender, int nrPlayers) {
		super(teamType, teamName, gender, nrPlayers);
	}
	
	public String getGender() {
		return super.getGender();
	}
	
	public ArrayList<Player> getPlayers() {
		return super.getPlayers();
	}
	
	public String toString() {
		String s1 = new String("{teamName: " + super.getTeamName() + 
				", gender: " + super.getGender() +
				", numberOfPlayers: " + super.getNumberOfPlayers() +
				", players: [");
		String s2 = "";
		int cont = 0;
		for(Player p: super.getPlayers()) {
			if(cont != 0)
				s2 += ", ";
			s2 += p.toString();
			
			cont++;
		}
		return s1 + s2 + "]}";
	}
	
	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}


	public void updatePoints(int score) {
		super.addPoints(score);
		
	}
	public static Comparator<Object> scoreComparator = new Comparator<Object>() {
		@Override
		public int compare(Object team1, Object team2) {
			FootballTeam t1 = (FootballTeam) team1;
			FootballTeam t2 = (FootballTeam) team2;
			return t1.getScore() > t2.getScore() ? -1 : (t1.getScore() == t2.getScore() ? 0 : 1);
		}
			
	};



}
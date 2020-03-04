/**
 * 
 * @author PETRUSCA BOGDAN-MIHAI 323CB
 * Folosesc aceasta clasa pentru a implementa design pattern-ul Singleton
 */
public class TeamConstructor {
	private static TeamConstructor uniqueInstance;
	public TeamConstructor() {
		
	}
	public static TeamConstructor getInstance() {
		if(uniqueInstance == null)
			uniqueInstance = new TeamConstructor();
		return uniqueInstance;
	}
	public Team createTeam(String teamType, String teamName, String gender, int numberOfPlayers) {
		Team team = null;
		if(teamType.equals("football"))
			team = new FootballTeam(teamType, teamName, gender, numberOfPlayers);
		if(teamType.equals("basketball"))
			team = new BasketballTeam(teamType, teamName, gender, numberOfPlayers);
		if(teamType.equals("handball"))
			team = new HandballTeam(teamType, teamName, gender, numberOfPlayers);
		return team;
	}
}
/**
 * 
 * @author PETRUSCA BOGDAN MIHAI 323CB
 * Aceasta clasa reprezinta implementarea propiu-zisa a desig pattern-ului Singleton
 */
public class CompetitionConstructor {
	private static CompetitionConstructor uniqueInstance;
	
	public CompetitionConstructor() {
		
	}
	
	public static CompetitionConstructor getInstance() {
		if(uniqueInstance == null)
			uniqueInstance = new CompetitionConstructor();
		return uniqueInstance;
	}
	
	public Competition<?> createCompetition(String teamType, String gender) {
		Competition<?> comp = null;
		if(teamType.equals("football")) {
			if(gender.equals("masculin"))
				comp = new Competition<FootballTeam>("football", "masculin");
			else
				comp = new Competition<FootballTeam>("football", "feminin");
		}
			
		if(teamType.equals("basketball")) {
			if(gender.equals("masculin"))
				comp = new Competition<BasketballTeam>("basketball", "masculin");
			else
				comp = new Competition<BasketballTeam>("basketball", "feminin");
		}
			
		if(teamType.equals("handball")) {
			if(gender.equals("masculin"))
				comp = new Competition<HandballTeam>("handball", "masculin");
			else
				comp = new Competition<HandballTeam>("handball", "feminin");
		}
			
		return comp;	
	}
}
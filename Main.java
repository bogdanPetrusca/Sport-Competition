import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author PETRUSC BOGDAN-MIHAI 323CB
 *
 */
public class Main {
	/**
	 * 
	 * @param in referinta catre fisierul cu echipe
	 * @param competition referinta catre fisierul competitie
	 * @param teams lista de echipe
	 */
	public static void readTeam(Scanner in, TeamConstructor competition, ArrayList<Team> teams)
	{
		/**
		 * Citesc linie cu line si adaug echipele in lista "teams"
		 */
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] words = line.split(", ", 0);
			String teamType, teamName, gender, numberOfPlayers;
			teamType = words[0];
			teamName = words[1];
			gender = words[2];
			numberOfPlayers = words[3];
			Team t = competition.createTeam(teamType, teamName, gender, Integer.parseInt(numberOfPlayers));
			for(int i = 0; i < Integer.parseInt(numberOfPlayers); i++) {
				String auxLine = in.nextLine();
				String[] auxWords = auxLine.split(", ", 0);
				Player player = new Player(auxWords[0], Integer.parseInt(auxWords[1]));
				t.getPlayers().add(player);
			}
			teams.add(t);		
		}
	}
	/**
	 * 
	 * @param out referinta catre fisierul de iesire
	 * @param teams lista de echipe
	 * @throws IOException
	 * Cu ajutorul acestei metode adaug in fisierul de iesire echipele date
	 */
	public static void teamsToString(BufferedWriter out,  ArrayList<Team> teams) throws IOException {
		for(Team t: teams) {
			out.write(t.toString() + "\r\n");
		}
	}
	/**
	 * 
	 * @param inComp referinta catre fisireul competitiei
	 * @param teams lista de echipe
	 * @param footballMasculin Competitia de fotbal masculin
	 * @param basketballMasculin Competitia de fotbal feminin
	 * @param handballMasculin Competitia de basket masculin
	 * @param footballFeminin Competitia de basket feminin
	 * @param basketballFeminin Competitia de handbal masculin
	 * @param handballFeminin Comeptitia de handbal feminin
	 */
	public static void addToCompetition(Scanner inComp, ArrayList<Team> teams, Competition<FootballTeam> footballMasculin, 
			Competition<BasketballTeam> basketballMasculin, Competition<HandballTeam> handballMasculin, 
			Competition<FootballTeam> footballFeminin, Competition<BasketballTeam> basketballFeminin, Competition<HandballTeam> handballFeminin) {
		String line = inComp.nextLine();
		String[] words = line.split(", ", 0);
		String compType = words[0];
		String gender = words[1];
		
		while(inComp.hasNextLine()) {
			line = inComp.nextLine();
			/**
			 * Citesc linie cu linie si adaug echipele in functie de sex si tipul de competitie
			 */
			for(Team team: teams) {
				if(team.getTeamType().equals(compType) && team.getGender().equals(gender) && team.getTeamName().equals(line)) {
					if(team instanceof FootballTeam) {
						if(team.getGender().equals("masculin"))
							footballMasculin.getTeams().add((FootballTeam) team);
						else
							footballFeminin.getTeams().add((FootballTeam) team);
					}
						
					if(team instanceof BasketballTeam) {
						if(team.getGender().equals("masculin"))
							basketballMasculin.getTeams().add((BasketballTeam) team);
						else
							basketballFeminin.getTeams().add((BasketballTeam) team);
					}
						
					if(team instanceof HandballTeam) {
						if(team.getGender().equals("masculin"))
							handballMasculin.getTeams().add((HandballTeam) team);
						else
							handballFeminin.getTeams().add((HandballTeam) team);
					}
				}
			}
		}	
	}
	/**
	 * 
	 * @param comp reprezinta o competitie de orice tip
	 */
	public static void setScore(Competition<?> comp) {
		if(comp.getTeamType().equals("football"))
			for(Object team: comp.getTeams()) {
				FootballTeam t = (FootballTeam) team;
				double score = t.accept(comp);
				t.setScore(score);
			}
		if(comp.getTeamType().equals("basketball"))
			for(Object team: comp.getTeams()) {
				BasketballTeam t = (BasketballTeam) team;
				double score = t.accept(comp);
				t.setScore(score);
			}
		if(comp.getTeamType().equals("handball"))
			for(Object team: comp.getTeams()) {
				HandballTeam t = (HandballTeam) team;
				double score = t.accept(comp);
				t.setScore(score);
			}
	}
	/**
	 * 
	 * @param out referinta catre fisierul de iesire
	 * @param inCompAux referinta catre fisierul competitie
	 * @param footballMasculin
	 * @param basketballMasculin
	 * @param handballMasculin
	 * @param footballFeminin
	 * @param basketballFeminin
	 * @param handballFeminin
	 * @throws IOException
	 */
	public static void startCompetition(BufferedWriter out, Scanner inCompAux, Competition<FootballTeam> footballMasculin, 
			Competition<BasketballTeam> basketballMasculin, Competition<HandballTeam> handballMasculin, 
			Competition<FootballTeam> footballFeminin, Competition<BasketballTeam> basketballFeminin, Competition<HandballTeam> handballFeminin) throws IOException {
		
		String line = inCompAux.nextLine();
		String words[] = line.split(", ", 0);
		String competitionType = words[0];
		String gender = words[1];
		/**
		 * In functie de tipul coompetitie si de sex folosesc metoda "game" pentru a da start competitiei, iar metoda "showClasament" pentru a scrie in fisier
		 */
		if(competitionType.equals("football") && gender.equals("masculin"))
			{footballMasculin.game(); footballMasculin.showClasament(out);}
		if(competitionType.equals("football") && gender.equals("feminin"))
			{footballFeminin.game(); footballFeminin.showClasament(out);}
		if(competitionType.equals("basketball") && gender.equals("masculin"))
			{ basketballMasculin.game(); basketballMasculin.showClasament(out);}
		if(competitionType.equals("basketball") && gender.equals("feminin"))
			{basketballFeminin.game(); basketballFeminin.showClasament(out);}
		if(competitionType.equals("handball") && gender.equals("masculin"))
			{handballMasculin.game(); handballMasculin.showClasament(out);}
		if(competitionType.equals("handball") && gender.equals("feminin"))
			{handballFeminin.game(); handballFeminin.showClasament(out);}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		/**
		 * Pentru a crea echipele folosesc design patern-ul Singleton astfel: Instatiez clasa TeamConstructor, iar cu ajutorul ei creez echipele in functie de tipul Competitiei
		 */
		TeamConstructor teamConstructor = TeamConstructor.getInstance();
		ArrayList<Team> teams = new ArrayList<Team>();
		
		String type = args[0];
		String inName = args[1];
		String compName = args[2];
		String outName = args[args.length - 1];
		//System.out.println(type);
		
		Scanner in = new Scanner(new File(inName));
		readTeam(in, teamConstructor, teams);
		
		
		BufferedWriter out = new BufferedWriter(new FileWriter(outName));
		if(type.equals("inscriere") == true)
			teamsToString(out, teams);
		
		/**
		 * Utilizez iar design patern-ul Singleton pentru a crea competitiile
		 */
		CompetitionConstructor compConstructor = CompetitionConstructor.getInstance();
		
		Competition<FootballTeam> footballMasculin = (Competition<FootballTeam>) compConstructor.createCompetition("football", "masculin");
		Competition<FootballTeam> footballFeminin = (Competition<FootballTeam>) compConstructor.createCompetition("football", "feminin");
		Competition<BasketballTeam> basketballMasculin = (Competition<BasketballTeam>) compConstructor.createCompetition("basketball", "masculin");
		Competition<BasketballTeam> basketballFeminin = (Competition<BasketballTeam>) compConstructor.createCompetition("basketball", "feminin");
		Competition<HandballTeam> handballMasculin = (Competition<HandballTeam>) compConstructor.createCompetition("handball", "masculin");
		Competition<HandballTeam> handballFeminin = (Competition<HandballTeam>) compConstructor.createCompetition("handball", "feminin");
		
		Scanner inComp = null;
		try {
			inComp = new Scanner(new File(compName));
		} catch (FileNotFoundException e) {
			in.close();
			out.close();
			return;
		}
		addToCompetition(inComp, teams, footballMasculin, basketballMasculin, handballMasculin, footballFeminin, basketballFeminin, handballFeminin);
		
		
		setScore(footballMasculin);
		setScore(basketballMasculin);
		setScore(handballMasculin);
		setScore(footballFeminin);
		setScore(basketballFeminin);
		setScore(handballFeminin);
		
		
		Scanner inCompAux = null;
		try {
			inCompAux = new Scanner(new File(compName));
		} catch (FileNotFoundException e) {
			in.close();
			inComp.close();
			out.close();
			return;
		}
		if(type.equals("competitie") == true)
			startCompetition(out, inCompAux, footballMasculin, basketballMasculin, handballMasculin, footballFeminin, basketballFeminin, handballFeminin);

		
		in.close();
		inComp.close();
		out.close();
		inCompAux.close();
	}
}

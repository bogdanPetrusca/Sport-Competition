import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Competition<E> implements Visitor, Subject<E>{

	private String teamType, gender;
	private ArrayList<E> teams;
	private ArrayList<Object> clasament;
	
	public Competition(String teamType, String gender) {
		this.teamType = teamType;
		this.gender = gender;
		this.teams = new ArrayList<E>();
		this.clasament = new ArrayList<Object>();
	}
	
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public ArrayList<E> getTeams() {
		return teams;
	}
	public ArrayList<?> getClasament() {
		return clasament;
	}
	public double visit(FootballTeam footballItem) {
		int Max = -1, Min = 100000;
		double sum = 0;
		if(footballItem.getGender().equals("masculin")) {
			for(Player p: footballItem.getPlayers()) {
				if(Max > p.getScore())
					Max = p.getScore();
				sum += p.getScore();
			}
			sum += Max;
			
		}
		else {
			for(Player p: footballItem.getPlayers()) {
				if(Min < p.getScore())
					Min = p.getScore();
				sum += p.getScore();
			}
			sum += Min;
		}
		return sum;
	}
	public double visit(BasketballTeam basketBallItem) {
		double sum = 0;
		for(Player p: basketBallItem.getPlayers())
			sum += p.getScore();
		return sum / basketBallItem.getPlayers().size();
	}

	public double visit(HandballTeam handballItem) {
		double var1 = 0, var2 = 1;
		if(handballItem.getGender().equals("masculin")) {
			for(Player p: handballItem.getPlayers())
				var1 += p.getScore();
		} else {
			for(Player p: handballItem.getPlayers())
				var2 *= p.getScore();
		}
		if(handballItem.getGender().equals("masculin")) return var1;
		return var2;

	}
	public void game() {
		this.clasament.addAll(this.teams);
		if(teamType.equals("football")) {
			for(int i = 0; i < teams.size() - 1; i++) {
				FootballTeam t1 = (FootballTeam) teams.get(i);
				for(int j = i + 1; j < teams.size(); j++) {
					FootballTeam t2 = (FootballTeam) teams.get(j);
					this.updatePoints(t1, t2);
				}
			}
		}
		if(teamType.equals("basketball")) {
			for(int i = 0; i < teams.size() - 1; i++) {
				BasketballTeam t1 = (BasketballTeam) teams.get(i);
				for(int j = i + 1; j < teams.size(); j++) {
					BasketballTeam t2 = (BasketballTeam) teams.get(j);
					this.updatePoints(t1, t2);
				}
			}
		}
		if(teamType.equals("handball")) {
			for(int i = 0; i < teams.size() - 1; i++) {
				HandballTeam t1 = (HandballTeam) teams.get(i);
				for(int j = i + 1; j < teams.size(); j++) {
					HandballTeam t2 = (HandballTeam) teams.get(j);
					this.updatePoints(t1, t2);
				}
			}
		}
				
	}

	public void updatePoints(Object t1, Object t2) {
		if(teamType.equals("football")) {
			FootballTeam team1 = (FootballTeam) t1;
			FootballTeam team2 = (FootballTeam) t2;
			if(team1.getScore() - team2.getScore() > 0)
				team1.updatePoints(3);
			if(team1.getScore() - team2.getScore() < 0)
				team2.updatePoints(3);
			if(team1.getScore() - team2.getScore() == 0) {
				team1.updatePoints(1);
				team2.updatePoints(1);
			}
		}
		if(teamType.equals("basketball")) {
			BasketballTeam team1 = (BasketballTeam) t1;
			BasketballTeam team2 = (BasketballTeam) t2;
			if(team1.getScore() - team2.getScore() > 0)
				team1.updatePoints(3);
			if(team1.getScore() - team2.getScore() < 0)
				team2.updatePoints(3);
			if(team1.getScore() - team2.getScore() == 0) {
				team1.updatePoints(1);
				team2.updatePoints(1);
			}
		}
		if(teamType.equals("handball")) {
			HandballTeam team1 = (HandballTeam) t1;
			HandballTeam team2 = (HandballTeam) t2;
			if(team1.getScore() - team2.getScore() > 0)
				team1.updatePoints(3);
			if(team1.getScore() - team2.getScore() < 0)
				team2.updatePoints(3);
			if(team1.getScore() - team2.getScore() == 0) {
				team1.updatePoints(1);
				team2.updatePoints(1);
			}
		}
		this.updateClasament();
		
	}

	public void updateClasament() {
		if(teamType.equals("football")) {
			clasament.sort(FootballTeam.scoreComparator);
		}
		if(teamType.equals("basketball")) {
			clasament.sort(BasketballTeam.scoreComparator);
		}
		if(teamType.equals("handball")) {
			clasament.sort(HandballTeam.scoreComparator);
		}
	}
	
	public void setLoc() {
		if(teamType.equals("football")) {
			for(Object o: clasament) {
				int loc = clasament.indexOf(o);
				FootballTeam team = (FootballTeam) o;
				int index = teams.indexOf(team);
				E aux = teams.get(index);
				((FootballTeam)aux).setLoc(loc + 1);
				teams.set(index, aux);
				
			}
		}
		if(teamType.equals("basketball")) {
			for(Object o: clasament) {
				int loc = clasament.indexOf(o);
				BasketballTeam team = (BasketballTeam) o;
				int index = teams.indexOf(team);
				E aux = teams.get(index);
				((BasketballTeam)aux).setLoc(loc + 1);
				teams.set(index, aux);
				
			}
		}
		if(teamType.equals("handball")) {
			for(Object o: clasament) {
				int loc = clasament.indexOf(o);
				HandballTeam team = (HandballTeam) o;
				int index = teams.indexOf(team);
				E aux = teams.get(index);
				((HandballTeam)aux).setLoc(loc + 1);
				teams.set(index, aux);
				
			}
		}
	}
	
	public void showClasament(BufferedWriter out) throws IOException {
		this.setLoc();
		int cont = 0;
		if(teamType.equals("football")) {
			for(Object o: clasament) {
				cont++;
				FootballTeam team = (FootballTeam) o;
				out.write(team.getTeamName() + "\r\n");
				if(cont == 3)
					break;
			}
			for(E o: teams) {
				FootballTeam team = (FootballTeam) o;
				String s = new String("Echipa " + team.getTeamName() + " a ocupat locul " + team.getLoc() + "\r\n");
				out.write(s);
			}
		}
		if(teamType.equals("basketball")) {
			for(Object o: clasament) {
				cont++;
				BasketballTeam team = (BasketballTeam) o;
				out.write(team.getTeamName() + "\r\n");
				if(cont == 3)
					break;
			}
			for(E o: teams) {
				BasketballTeam team = (BasketballTeam) o;
				String s = new String("Echipa " + team.getTeamName() + " a ocupat locul " + team.getLoc() + "\r\n");
				out.write(s);
			}
		}
		if(teamType.equals("handball")) {
			for(Object o: clasament) {
				cont++;
				HandballTeam team = (HandballTeam) o;
				out.write(team.getTeamName() + "\r\n");
				if(cont == 3)
					break;
			}
			for(E o: teams) {
				HandballTeam team = (HandballTeam) o;
				String s = new String("Echipa " + team.getTeamName() + " a ocupat locul " + team.getLoc() + "\r\n");

				out.write(s);
			}
		}
	}

	
	
}
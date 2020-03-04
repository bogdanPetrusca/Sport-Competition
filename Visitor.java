/**
 * 
 * @author PETRUSCA BOGDAN-MIHAI 323CB
 * Utilizez aceasta clasa in implementarear design pattern-ului Visitor
 *
 */
public interface Visitor {

	public double visit(FootballTeam footbalItem);
	public double visit(BasketballTeam basketBallItem);
	public double visit(HandballTeam handballItem);
	
}
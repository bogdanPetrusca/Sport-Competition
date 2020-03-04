/**
 * 
 * @author PETRUSCA BOGDAN MIHAI 323CB
 * Aceasta interfata este folosita pentru a implementa design pattern-ul Observer
 * Pentru a folosi interfata Subject pentru toate tipuri de competitii am folosit E
 */
public interface Subject<E> {
	public void updatePoints(E t1, E t2);
	public void updateClasament();
}
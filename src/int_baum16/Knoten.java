package int_baum16;

class Knoten {
	public int x,y; // Datenelement
	public Knoten links, rechts; // Zeiger auf Nachfolgeknoten
	public String daten;
	/**
	 * @param n s the data to be added
	 * @param f is the x-achse position
	 * @param b is the y-achse position
	 */
	Knoten(String n,int f,int b) {
		daten = n;
		links = null;
		rechts = null;
		x = f;
		y = b;
	}
}
package Salbuespenak;

public class StringEzEgokiException extends Exception {
	boolean luze;
	
	public StringEzEgokiException(boolean pLuze) {
		luze = pLuze;
	}
	
	public void mezuaInprimatu() {
		if(luze) System.out.println("Sartu duzun String-a luzeegia da.");
		else System.out.println("Sartu duzun String-a hutsik dago.");
	}
}

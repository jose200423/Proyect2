  package co.edu.unbosque.view;

import java.sql.Date;
import java.util.Scanner;

public class View {
	
private Scanner s;
	
	public View() {
		s=new Scanner(System.in);
	}
	
	public int readInt() {
		return s.nextInt();
	}
	
	public long readLong() {
		return s.nextLong();
	}
	
	public float readFloat() {
		return s.nextFloat();
	}
	
	public double readDoble() {
		return s.nextDouble();
	}
	
	public char readCharacter() {
		return s.next().charAt(0);
	}
	
	public String readLine() {
		return s.next();
	}
	
	public String readAllLine() {
		return s.nextLine();
	}
	
	    
	public void burnLine() {
		s.nextLine();
	}
	
	public void printJump(String string) {
		System.out.println(string);
	}
	
	public void printNotJump(String mensaje) {
		System.out.print(mensaje);
	}


}

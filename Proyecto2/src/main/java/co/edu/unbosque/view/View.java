package co.edu.unbosque.view;

import java.sql.Date;
import java.util.Scanner;

import co.edu.unbosque.util.NumeroInvalidoException;
import co.edu.unbosque.util.PalabraInvalidaException;

public class View {

	private Scanner s;

	public View() {
		s = new Scanner(System.in);
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

	public int contieneNumeros(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			if (Character.isDigit(cadena.charAt(i))) {
				return 1;
			}
		}
		return 0;
	}

	public int contieneLetras(String numero) {
		for (int i = 0; i < numero.length(); i++) {
			if (!Character.isDigit(numero.charAt(i))) {
				return 1;
			}
		}
		return 0;
	}
}

package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.model.persistence.PersonDAO;
import co.edu.unbosque.view.View;

public class Controller {

	private View v;
	private PersonDAO p;

	public Controller() {
		v = new View();
		p = new PersonDAO();
		p.read();	
		}

	public void iniciar() {

		while (true) {
			v.printJump(" ");
			v.printJump("CRUD PASSWORD");
			v.printJump("^^^^Menu^^^^");
			v.printJump("1. Crear persona");
			v.printJump("2. show persona");
			v.printJump("3. delate persona");
			v.printJump("4. update persona");
			v.printJump("5. show persona especifica");
			v.printJump("salir");

			int opcion = v.readInt();

			switch (opcion) {
			case 1:
				v.burnLine();
				v.printJump("Enter name");
				String name = v.readAllLine();
				v.printJump("Enter C.c");
				long cc = v.readLong();
				v.burnLine();
				v.printJump("Enter Date birthday");
				String birthday = v.readAllLine();

				java.sql.Date sqlDate = null;
				try {
				    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
				    java.util.Date utilDate = formato.parse(birthday);
				    sqlDate = new java.sql.Date(utilDate.getTime());
				} catch (ParseException e) {
				    e.printStackTrace();
				}

				v.printJump("Enter cityOfBorn");
				String cityOfBorn = v.readAllLine();
				p.create(new PersonDTO(name, cc, sqlDate, cityOfBorn));
				v.printJump("was added successfully");
				break;
			case 2:

				if (p.getUsers().size() != 0) {
					v.printJump(p.readAll());
				} else {
					v.printJump("No hay usuarios");
				}
				break;
			case 3:
				v.burnLine();
				v.printJump("Enter cc");
				int id4 = v.readInt();
				p.deleteByCc(id4);
				if (p.deleteByCc(id4) == 0) {
					v.printJump("Se elimino con exito");
				} 
				break;
			case 4:
				v.burnLine();
				v.printJump("Enter name");
				String name1 = v.readAllLine();
				v.printJump("Enter C.c");
				long cc1 = v.readLong();
				v.burnLine();
				v.printJump("Enter Date birthday");
				String birthday1 = v.readAllLine();

				java.sql.Date sqlDate1 = null;
				try {
				    SimpleDateFormat formato1 = new SimpleDateFormat("yyyy/MM/dd");
				    java.util.Date utilDate1 = formato1.parse(birthday1);
				    sqlDate1 = new java.sql.Date(utilDate1.getTime());
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				v.printJump("Enter cityOfBorn");
				String cityOfBorn1 = v.readAllLine();
				
				String[] args = new String[]{ name1 ,String.valueOf(sqlDate1), cityOfBorn1};
				p.updateByCc(cc1, args);
			
				break;
			case 5:
				v.burnLine();
				v.printJump("Enter cc");
				int id5 = v.readInt();
				v.printJump(p.readByCc(id5));
				break;
			default:
				v.printJump("Gracias por utilizar este programa");
				System.exit(0);
				break;
			}
		}

	}
}

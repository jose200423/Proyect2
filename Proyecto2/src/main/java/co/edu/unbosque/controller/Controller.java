package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;

import co.edu.unbosque.model.PersonDTO;
import co.edu.unbosque.persistence.PersonDAO;
import co.edu.unbosque.view.View;

public class Controller {

	private View v;
	private PersonDAO p;

	public Controller() {
		v = new View();
		p = new PersonDAO();
		iniciar();

	}

	public void iniciar() {

		while (true) {
			v.printJump(" ");
			v.printJump("CRUD PASSWORD");
			v.printJump("^^^^Menu^^^^");
			v.printJump("1. Crear persona");
			v.printJump("2. show persona");
			v.printJump("3. delate persona");
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

				if (p.getListpersons().size() != 0) {
					v.printJump(p.readAll());
				} else {
					v.printJump("No hay usuarios");
				}
				break;
			case 3:
				v.burnLine();
				v.printJump("Enter cc");
				int id4 = v.readInt();
				p.delateById(id4);
				if (p.delateById(id4) == 0) {
					v.printJump("Se elimino con exito");
				} 
				break;
			default:
				v.printJump("Gracias por utilizar este programa");
				System.exit(0);
				break;
			}
		}

	}
}

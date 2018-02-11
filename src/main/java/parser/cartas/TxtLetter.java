package parser.cartas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import model.User;

public class TxtLetter extends Letter{
	private Writer writer;

	public void createLetter(User user) throws IOException{
		File letter = new File("cartas/txt/" + user.getNombre() + ".txt");
		writer = new FileWriter(letter);
		writer.write("Usuario: " + user.getNombre() + "\n" + "Password: "
				+ user.getPassword());
	}
}

package parser.cartas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import model.Agent;

public class TxtLetter extends Letter{
	private Writer writer;

	public void createLetter(Agent Agent) throws IOException{
		File letter = new File("cartas/txt/" + Agent.getIdentifier() + ".txt");
		writer = new FileWriter(letter);
		writer.write("Usuario: " + Agent.getNombre() + "\n" + "Password: "
				+ Agent.getPassword());
		writer.close();
	}
}

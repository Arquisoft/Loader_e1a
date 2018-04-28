package parsertest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Agent;

import org.junit.Test;

import parser.cartas.Letter;
import parser.cartas.PdfLetter;
import parser.cartas.TxtLetter;
import parser.cartas.WordLetter;

import com.lowagie.text.DocumentException;

public class LetterTest {

	@Test
	public void creadasCorrectamente() throws FileNotFoundException, DocumentException, IOException {
		Letter letter = new PdfLetter();
		Agent Agent1 = new Agent("Paco", "C\\Uría", "francisco@gmail.com", "87654321P", "1, Person");
		letter.createLetter(Agent1);

		File file = new File("cartas/pdf/87654321P.pdf");
		assertTrue(file.exists());
		file.delete();

		letter = new WordLetter();
		letter.createLetter(Agent1);

		file = new File("cartas/word/87654321P.docx");
		assertTrue(file.exists());
		file.delete();

		letter = new TxtLetter();
		letter.createLetter(Agent1);

		file = new File("cartas/txt/87654321P.txt");
		assertTrue(file.exists());
		file.delete();
	}
	
}

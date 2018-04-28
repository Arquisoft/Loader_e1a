package parser.cartas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import model.Agent;

public class PdfLetter extends Letter{
	private Document document;
	public  void createLetter(Agent Agent) throws DocumentException, FileNotFoundException{
		document = null;
		FileOutputStream letter = null;
		letter = new FileOutputStream(
				"cartas/pdf/" + Agent.getIdentifier() + ".pdf");
		document = new Document();
		PdfWriter.getInstance(document, letter);
		document.open();
		document.add(new Paragraph("Usuario: " + Agent.getNombre()
				+ "\n Password: " + Agent.getPassword()));
		document.close();
	}
}

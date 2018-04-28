package dbupdate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.lowagie.text.DocumentException;

import model.Agent;
import parser.cartas.Letter;
import parser.cartas.PdfLetter;
import parser.cartas.TxtLetter;
import parser.cartas.WordLetter;
import persistence.AgentFinder;
import persistence.util.Jpa;
import reportwriter.ReportWriter;

public class InsertP implements Insert {

	@Override
	public Agent save(Agent Agent) throws FileNotFoundException, DocumentException, IOException {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		try {
			if (!AgentFinder.findByDNI(Agent.getNombre()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"El usuario con el nombre " + Agent.getNombre() + " ya existe en la base de datos");
				trx.rollback();
			} else if (!AgentFinder.findByEmail(Agent.getEmail()).isEmpty()) {
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"Ya existe un usuario con el identificador " + Agent.getIdentifier() + " en la base de datos");
				trx.rollback();
			} else {
				Jpa.getManager().persist(Agent);
				//Jpa.getManager().persist(Agent.getKind());
				trx.commit();
				Letter letter = new PdfLetter();
				letter.createLetter(Agent);
				letter = new TxtLetter();
				letter.createLetter(Agent);
				letter = new WordLetter();
				letter.createLetter(Agent);
			}
		} catch (PersistenceException ex) {
			ReportWriter.getInstance().getWriteReport().log(Level.WARNING, "Error de la BBDD");
			if (trx.isActive())
				trx.rollback();
		} finally {
			if (mapper.isOpen())
				mapper.close();
		}
		return Agent;
	}

	@Override
	public List<Agent> findByDNI(String dni) {
		return AgentFinder.findByDNI(dni);
	}

	@Override
	public List<Agent> findByEmail(String email) {
		return AgentFinder.findByEmail(email);
	}
}

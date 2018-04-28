package parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import dbupdate.Insert;
import dbupdate.InsertP;
import model.Agent;
import persistence.AgentFinder;

public class InsertR implements Insert {

	@Override
	public Agent save(Agent Agent) throws FileNotFoundException, DocumentException, IOException {
		return new InsertP().save(Agent);
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

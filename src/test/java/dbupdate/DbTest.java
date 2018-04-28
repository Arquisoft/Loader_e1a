package dbupdate;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Agent;

import org.junit.After;
import org.junit.Test;

import persistence.AgentFinder;
import persistence.util.Jpa;

import com.lowagie.text.DocumentException;

import executer.ActionSingleton;

public class DbTest {

	@Test
	public void usuarioYaExistenteDni() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		Agent Agent1 = new Agent("Paco", "C\\Uría", "francisco@gmail.com", "87654321P", "1, Person");
		Agent Agent2 = new Agent("Paco", "C\\Uría", "franci@gmail.com", "87654321P", "1, Person");

		aS.getAF().saveData(Agent1);
		aS.getAF().saveData(Agent2);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Agent> test = AgentFinder.findByDNI("87654321P");
		assertEquals(test.get(0).getEmail(), "francisco@gmail.com");

		trx.commit();
		mapper.close();
	}

	@Test
	public void usuarioYaExistenteEmail() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		Agent Agent1 = new Agent("Paco", "francisco@gmail.com", "87654321P", "1, Person");
		Agent Agent3 = new Agent("Paco", "francisco@gmail.com", "87654353Y", "1, Person");

		aS.getAF().saveData(Agent1);
		aS.getAF().saveData(Agent3);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Agent> test = AgentFinder.findByEmail("francisco@gmail.com");
		assertEquals(test.get(0).getIdentifier(), "87654321P");

		trx.commit();
		mapper.close();

	}

	@After
	public void deleting() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<Agent> aBorrar = AgentFinder.findByDNI("87654321P");
		Jpa.getManager().remove(aBorrar.get(0));
		trx.commit();
		mapper.close();
	}

}

package executer;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Agent;

import org.junit.Test;

import persistence.AgentFinder;
import persistence.util.Jpa;

import com.lowagie.text.DocumentException;

public class ExecuterTest {

	@Test
	public void testActionSingleton() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		ActionSingleton aS2 = ActionSingleton.getInstance();
		
		assertEquals(aS, aS2);
		
		Agent Agent = new Agent("Paco", "C\\Uría", "francisco@gmail.com", "87654321P", "Person");
		
		aS.getAF().saveData(Agent);
		
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		Agent Agent2 = AgentFinder.findByEmail("francisco@gmail.com").get(0);
		
		assertEquals(Agent, Agent2);
		
		trx.commit();
		
	}

}

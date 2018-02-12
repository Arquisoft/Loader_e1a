package executer;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.User;

import org.junit.Test;

import persistence.UserFinder;
import persistence.util.Jpa;

import com.lowagie.text.DocumentException;

public class ExecuterTest {

	@Test
	public void testActionSingleton() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		ActionSingleton aS2 = ActionSingleton.getInstance();
		
		assertEquals(aS, aS2);
		
		User user = new User("Paco", "C\\Uría", "francisco@gmail.com", "87654321P", "1, Person");
		
		aS.getAF().saveData(user);
		
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		User user2 = UserFinder.findByEmail("francisco@gmail.com").get(0);
		
		assertEquals(user, user2);
		
		trx.commit();
		
	}

}

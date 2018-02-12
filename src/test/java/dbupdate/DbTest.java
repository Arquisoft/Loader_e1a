package dbupdate;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.User;

import org.junit.After;
import org.junit.Test;

import persistence.UserFinder;
import persistence.util.Jpa;

import com.lowagie.text.DocumentException;

import executer.ActionSingleton;

public class DbTest {

	@Test
	public void usuarioYaExistenteDni() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		User user1 = new User("Paco", "C\\Uría", "francisco@gmail.com", "87654321P", "1, Person");
		User user2 = new User("Paco", "C\\Uría", "franci@gmail.com", "87654321P", "1, Person");

		aS.getAF().saveData(user1);
		aS.getAF().saveData(user2);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<User> test = UserFinder.findByDNI("87654321P");
		assertEquals(test.get(0).getEmail(), "francisco@gmail.com");

		trx.commit();
		mapper.close();
	}

	@Test
	public void usuarioYaExistenteEmail() throws FileNotFoundException, DocumentException, IOException {
		ActionSingleton aS = ActionSingleton.getInstance();
		User user1 = new User("Paco", "francisco@gmail.com", "87654321P", "1, Person");
		User user3 = new User("Paco", "francisco@gmail.com", "87654353Y", "1, Person");

		aS.getAF().saveData(user1);
		aS.getAF().saveData(user3);

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<User> test = UserFinder.findByEmail("francisco@gmail.com");
		assertEquals(test.get(0).getIdentificador(), "87654321P");

		trx.commit();
		mapper.close();

	}

	@After
	public void deleting() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<User> aBorrar = UserFinder.findByDNI("87654321P");
		Jpa.getManager().remove(aBorrar.get(0));
		trx.commit();
		mapper.close();
	}

}

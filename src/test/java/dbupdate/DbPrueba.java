package dbupdate;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import model.MockAgent;
import persistence.util.Jpa;

public class DbPrueba {

	@Test
	public void test() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		MockAgent user = new MockAgent("Auxiliar", "auxiliar@uniovi.es", "aux345543", 2);
		
		mapper.persist(user);
		
		trx.commit();
		
		MockAgent mock = mapper.find(user.getClass(), user.getId());
		
		assertEquals(mock, user);
		
		mapper.remove(mock);
		
		MockAgent dos = mapper.find(user.getClass(), user.getId());
		
		assertNull(dos);
		
		mapper.close();	

	}

}

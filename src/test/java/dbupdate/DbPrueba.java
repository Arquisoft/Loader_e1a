package dbupdate;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;

import model.MockAgent;
import model.MockType;
import persistence.util.Jpa;

public class DbPrueba {

	@Test
	public void test() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		MockType tipo = new MockType(1, "aux");
		MockAgent user = new MockAgent("Auxiliar", "auxiliar@uniovi.es", "aux345543", tipo);
		
		
		mapper.persist(tipo);
		mapper.persist(user);
		
		trx.commit();
		
		MockAgent mock = mapper.find(user.getClass(), user.getId());
		
		assertEquals(mock, user);
		
		mapper.remove(mock);
		
		MockAgent dos = mapper.find(user.getClass(), user.getId());
		
		assertNull(dos);
		
		mapper.close();	

	}}


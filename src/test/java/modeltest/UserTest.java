package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import model.Agent;

import org.junit.Test;

public class UserTest {

	@Test
	public void testEquals() {
		Agent Agent1 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "Person");
		Agent Agent2 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "Person");
		Agent Agent3 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", "7787777R", "Person");
		Agent Agent4 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", null, "Person");

		assertEquals(true, Agent1.equals(Agent2));
		assertEquals(true, Agent3.equals(Agent3));
		assertEquals(false, Agent2.equals(Agent3));
		assertNotNull(Agent1);
		assertEquals(false, Agent1.equals(new Integer(8)));
		assertEquals(false, Agent4.equals(Agent3));
	}

	@Test
	public void testHashCode() {
		Agent Agent1 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "Person");
		Agent Agent2 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "Person");
		Agent Agent3 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", "7787777R", "Person");
		Agent Agent4 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", null, "Person");
		Agent Agent5 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", null, "Person");

		assertEquals(Agent1.hashCode(), Agent2.hashCode());
		assertEquals(Agent4.hashCode(), Agent5.hashCode());
		assertNotEquals(Agent2.hashCode(), Agent3.hashCode());

		System.out.println(Agent1.toString());
	}

	@Test
	public void testAll() {
		Agent Agent1 = new Agent("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "Person");

		String password = Agent1.getPassword();
		String AgentName = Agent1.getNombre();
		String toString = "Agent [nombre=Dani, localizacion=C\\Buenavida, email=email@gmail.com, password=" 
		+ Agent1.getPassword()+ ", identificador=7777777R, tipo=Person]";

		assertEquals("Dani", Agent1.getNombre());
		assertEquals("email@gmail.com", Agent1.getEmail());
		assertEquals("C\\Buenavida", Agent1.getLocation());
		assertEquals("7777777R", Agent1.getIdentifier());
		assertEquals(password, Agent1.getPassword());
		assertEquals(AgentName, Agent1.getNombre());
		assertEquals(toString, Agent1.toString());
	}

}

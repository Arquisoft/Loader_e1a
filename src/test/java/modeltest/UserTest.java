package modeltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import model.User;

import org.junit.Test;

public class UserTest {

	@Test
	public void testEquals() {
		User user1 = new User("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "1, Person");
		User user2 = new User("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "1, Person");
		User user3 = new User("Dani", "C\\Buenavida", "email@gmail.com", "7787777R", "1, Person");
		User user4 = new User("Dani", "C\\Buenavida", "email@gmail.com", null, "1, Person");

		assertEquals(true, user1.equals(user2));
		assertEquals(true, user3.equals(user3));
		assertEquals(false, user2.equals(user3));
		assertNotNull(user1);
		assertEquals(false, user1.equals(new Integer(8)));
		assertEquals(false, user4.equals(user3));
	}

	@Test
	public void testHashCode() {
		User user1 = new User("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "1, Person");
		User user2 = new User("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "1, Person");
		User user3 = new User("Dani", "C\\Buenavida", "email@gmail.com", "7787777R", "1, Person");
		User user4 = new User("Dani", "C\\Buenavida", "email@gmail.com", null, "1, Person");
		User user5 = new User("Dani", "C\\Buenavida", "email@gmail.com", null, "1, Person");

		assertEquals(user1.hashCode(), user2.hashCode());
		assertEquals(user4.hashCode(), user5.hashCode());
		assertNotEquals(user2.hashCode(), user3.hashCode());

		System.out.println(user1.toString());
	}

	@Test
	public void testAll() {
		User user1 = new User("Dani", "C\\Buenavida", "email@gmail.com", "7777777R", "1, Person");

		String password = user1.getPassword();
		String userName = user1.getNombre();
		String toString = "User [nombre=Dani, localizacion=C\\Buenavida, email=email@gmail.com, password=" 
		+ user1.getPassword()+ ", identificador=7777777R, tipo=MockType [tipo=1, nombre_tipo= Person]]";

		assertEquals("Dani", user1.getNombre());
		assertEquals("email@gmail.com", user1.getEmail());
		assertEquals("C\\Buenavida", user1.getLocalizacion());
		assertEquals("7777777R", user1.getIdentificador());
		assertEquals(password, user1.getPassword());
		assertEquals(userName, user1.getNombre());
		assertEquals(toString, user1.toString());
	}

}

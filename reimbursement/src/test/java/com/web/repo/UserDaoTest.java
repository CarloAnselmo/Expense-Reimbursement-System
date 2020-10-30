package com.web.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.web.model.UserDTO;

// Not allowed to create, update, or delete from a view, so those Dao methods will need to use the ers_reimb, while the two reader methods are now fine

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

	private UserDao ud;

	@Before
	public void setup() {
		ud = new UserDao();
	}

	@Test
	public void A_findAllUsers() {
		List<UserDTO> users = ud.findAll();
		assertTrue(users.size() == 5);
	}
	
	@Test
	public void B_findUserByNameSingle() {
		UserDTO smallSingle = ud.findByNameSingle("carlo");
		assertEquals(smallSingle.getUsername(), "carlo");
		assertEquals(smallSingle.getLastname(), "Anselmo");
		assertEquals(smallSingle.getRole(), "employee");
	}

	@Test
	public void C_findUsersByNamePass() {
		UserDTO smallSingle = ud.findByNamePass("carlo", "1");
		assertEquals(smallSingle.getUsername(), "carlo");
		assertEquals(smallSingle.getLastname(), "Anselmo");
		assertEquals(smallSingle.getRole(), "employee");
	}

	@Test
	public void D_manipulateUser() {
		UserDTO newUser = new UserDTO("Testman2", "password", "Firstname", "Lastname", "Test2@test.test");
		int create = ud.create(newUser);
		assertEquals(create, 1);
		
		int idToManipulate = ud.findByNameSingle("Testman2").getId();
		UserDTO updateUser = new UserDTO("Testman3", "password", "Firstname", "Lastname", "Test3@test.test");
		updateUser.setId(idToManipulate);
		updateUser.setRole_id(1);
		int update = ud.update(updateUser);
		assertEquals(update, 1);
		
		int delete = ud.delete(idToManipulate);
		assertEquals(delete, 1);
	}

	@Test
	public void findByIdTest() {
		assertEquals(null, ud.findById(1));
	}

	@Test
	public void findByStatusTest() {
		assertEquals(null, ud.findByStatus("status"));
	}

	@Test
	public void findByNameTest() {
		assertEquals(null, ud.findByName("name"));
	}

}
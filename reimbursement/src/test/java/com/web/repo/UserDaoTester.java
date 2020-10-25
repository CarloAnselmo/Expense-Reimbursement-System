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
	// Consider: Add extra fields to the DTO, create a 2nd, or use the old Reimbursement

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTester {
	
	private UserDao ud;

	@Before
	public void setup() {
		ud = new UserDao();
	}
	
	@Test
	public void findAllUsers() {
		List<UserDTO> users = ud.findAll();
		assertTrue(users.size() == 3);
	}
	
	@Test
	public void findUsersByNamePass() {
		UserDTO smallSingle = ud.findByNamePass("employed", "1");
		assertEquals(smallSingle.getUsername(), "employed");
		assertEquals(smallSingle.getLastname(), "Smith");
		assertEquals(smallSingle.getRole(), "employee");
	}
	
	@Test
	public void createUser() {
		UserDTO newUser = new UserDTO("Testman2", "password", "Firstname", "Lastname", "Test2@test.test");
		int create = ud.create(newUser);
		assertEquals(create, 1);
	}
	
	@Test
	public void updateUser() {
		UserDTO newUser = new UserDTO("Testman3", "password", "Firstname", "Lastname", "Test3@test.test");
		newUser.setRole_id(1);
		int update = ud.update(newUser);
		newUser.setId(4);
		assertEquals(update, 1);
	}
		
	@Test
	public void deleteUser() {
		int delete = ud.delete(4);
		assertEquals(delete, 1);
	}

}
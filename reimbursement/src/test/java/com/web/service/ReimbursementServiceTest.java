package com.web.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.web.model.ReimbursementDTO;
import com.web.repo.UserDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReimbursementServiceTest {
	
	private UserDao daoMock;
	private ReimbursementService rs;

	@Before
	public void setup() {
		rs = new ReimbursementService();
		daoMock = mock(UserDao.class);
	}
	
	@Test
	public void ViewAllReimbursementsTest() {
		List<ReimbursementDTO> reimbursements = rs.ViewAllReimbursements();
//		assertTrue(reimbursements.size() == 5);
	}
	
	@Test
	public void viewUserReimbursementsTest() {
		List<ReimbursementDTO> reimbursements = rs.viewUserReimbursements("carlo");
		assertTrue(reimbursements.size() == 4);
	}
	
	@Test
	public void viewPendingReimbursementsTest() {
		List<ReimbursementDTO> reimbursements = rs.viewPendingReimbursements();
		assertTrue(reimbursements.size() == 3);
	}
	
	@Test
	public void viewCompletedReimbursementsTest() {
		List<ReimbursementDTO> reimbursements = rs.viewCompletedReimbursements();
		assertTrue(reimbursements.size() == 3);
	}
	
	@Test
	public void submitReimbursementTest() {
		ReimbursementDTO newReimb = new ReimbursementDTO(4000d, "Testing", null, 2, 7);
		int create = 0;
		try {
			create = rs.submitReimbursement(newReimb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(create, 1);
	}
	
//	@Test
//	public void approveReimbursementTest() {
//		int update = rs.approveReimbursement(1);
//		assertEquals(update, 1);
//	}
//	
//	@Test
//	public void denyReimbursementTest() {
//		int update = rs.denyReimbursement(1);
//		assertEquals(update, 1);
//	}
	
//	@Runwith(Suite.class)
//
//	@SuiteClasses({
//	    findAllTestClass.class,
//	    class1.class,
//	    class2.class,
//	    
//	})
//	public class test {
//
//	}

}

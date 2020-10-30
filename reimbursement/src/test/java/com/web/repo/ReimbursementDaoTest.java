package com.web.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.web.model.ReimbursementDTO;
import com.web.model.UserDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReimbursementDaoTest {
	
	private ReimbursementDao rd;

	@Before
	public void setup() {
		rd = new ReimbursementDao();
	}

	@Test
	public void A_findReimbSum() {
		double result = rd.callSum("doGGo");
		assertEquals(919.83, result, 1);
		rd.callSum("");
	}
	
	@Test
	public void B_findAllReimbursements() {
		List<ReimbursementDTO> reimbursements = rd.findAll();
		assertTrue(reimbursements.size() == 6);
	}
	
	@Test
	public void B_findReimbursementByName() {
		List<ReimbursementDTO> reimbursements = rd.findByName("doGGo");
		assertTrue(reimbursements.size() == 3);
	}
	
	@Test
	public void B_findReimbursementByStatus() {
		List<ReimbursementDTO> reimbursements = rd.findByStatus("Denied");
		assertTrue(reimbursements.size() == 1);
		rd.findByStatus("");
	}
	
	@Test
	public void B_findReimbursementById() {
		ReimbursementDTO smallSingle = rd.findById(2);
		assertEquals(smallSingle.getAmount(), 425.52, 1);
		assertEquals(smallSingle.getDescription(), "Flew to California to consult with warehouse staff");
		assertEquals(smallSingle.getAuthor(), "doGGo");
		rd.findById(-1);
	}
	
	@Test
	public void C_createAndModifyReimbursement() {
		ReimbursementDTO newReimb = new ReimbursementDTO(4000d, "Testing", null, 2, 6);
		int create = rd.create(newReimb);
		assertEquals(create, 1);
		
		int idToManipulate = rd.getIDFromAmount(4000);
		
		assertEquals(0, rd.approveRequest(idToManipulate, 5));
		
		newReimb = rd.findById(5);
		newReimb.setId(idToManipulate);
		newReimb.setStatus_id(1);
		newReimb.setType_id(1);
		newReimb.setAuthor_id(1);
		newReimb.setResolver_id(5);
		assertEquals(1, rd.update(newReimb));
		
		assertEquals(0, rd.denyRequest(idToManipulate, 5));
		
		assertEquals(0, rd.delete(idToManipulate));
	}

	//findbynamepass
	//findbynamesingle

}
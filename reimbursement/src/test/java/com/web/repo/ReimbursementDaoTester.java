package com.web.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.web.model.ReimbursementDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReimbursementDaoTester {
	
	private ReimbursementDao rd;

	@Before
	public void setup() {
		rd = new ReimbursementDao();
	}

	@Test
	public void findAllReimbursements() {
		List<ReimbursementDTO> reimbursements = rd.findAll();
		assertTrue(reimbursements.size() == 5);
	}
	
	@Test
	public void findReimbursementByName() {
		List<ReimbursementDTO> reimbursements = rd.findByName("carlo");
		assertTrue(reimbursements.size() == 4);
	}
	
	@Test
	public void findReimbursementByStatus() {
		List<ReimbursementDTO> reimbursements = rd.findByStatus("denied");
		assertTrue(reimbursements.size() == 1);
	}
	
	@Test
	public void findReimbursementById() {
		ReimbursementDTO smallSingle = rd.findById(2);
		assertEquals(smallSingle.getAmount(), 400, 1);
		assertEquals(smallSingle.getDescription(), "Motel 6, 5 nights");
		assertEquals(smallSingle.getAuthor(), "employed");
	}
	
	@Test
	public void createReimbursement() {
		ReimbursementDTO newReimb = new ReimbursementDTO(4000d, "Testing", null, 2, 7);
		int create = rd.create(newReimb);
		assertEquals(create, 1);
	}
	
	@Test
	public void updateReimbursement() {
		ReimbursementDTO newReimb = rd.findById(2);
		newReimb.setId(5);
		newReimb.setStatus_id(1);
		newReimb.setType_id(1);
		newReimb.setAuthor_id(2);
		int update = rd.update(newReimb);
		assertEquals(update, 1);
	}
	
	@Test
	public void updateReimbursementStatus() {
		int update = rd.updateStatus(3, 1);
		assertEquals(rd.findById(1).getStatus(), "approved");
	}
		
	@Test
	public void deleteReimbursement() {
		int delete = rd.delete(4);
		assertEquals(delete, 1);
	}

}
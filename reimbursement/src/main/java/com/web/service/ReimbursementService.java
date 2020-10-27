package com.web.service;

import java.util.List;

import com.web.model.ReimbursementDTO;
import com.web.repo.ReimbursementDao;

public class ReimbursementService {
	
	private ReimbursementDao rdao;

	public ReimbursementService(ReimbursementDao rdao) {
		super();
		this.rdao = rdao;
	}
	
//	private ReimbursementDao rdao;
//	
//	public ReimbursementService(ReimbursementDao rdao) {
//		super();
//		this.rdao = rdao;
//	}

	public ReimbursementService() {
		this(new ReimbursementDao());
	}
	
	public List<ReimbursementDTO> ViewAllReimbursements() {
		return rdao.findAll();
	}
	
	public List<ReimbursementDTO> viewUserReimbursements(String s) {
		return rdao.findByName(s);
	}
	
	public List<ReimbursementDTO> viewPendingReimbursements() {
		return rdao.findByStatus("pending");
	}
	
	public List<ReimbursementDTO> viewCompletedReimbursements() {
		List<ReimbursementDTO> newList = rdao.findByStatus("denied");
		newList.addAll(rdao.findByStatus("approved"));
		return newList;
	}
	
	// --- Test below services, then move onto user service layer + testing
	
	public int submitReimbursement(ReimbursementDTO t) throws Exception {
		int result = rdao.create(t);
		if(result==0) {
			throw new Exception("The request was not submitted.");
		}
		return result;
	}
	
	public int approveReimbursement(Integer i) {
		return rdao.approveRequest(i);
	}
	
	public int denyReimbursement(Integer i) {
		return rdao.denyRequest(i);
	}
	
	public int deleteReimbursement(Integer i) {
		return rdao.delete(i);
	}
	
}

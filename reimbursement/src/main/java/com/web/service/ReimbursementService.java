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
	
	public double sumUserOffers(String s) {
		return rdao.callSum(s);
	}
	
	public List<ReimbursementDTO> ViewAllReimbursements() {
		return rdao.findAll();
	}
	
	public List<ReimbursementDTO> viewUserReimbursements(String s) {
		return rdao.findByName(s);
	}
	
	public ReimbursementDTO viewByID(Integer i) {
		return rdao.findById(i);
	}
	
	public List<ReimbursementDTO> viewPendingReimbursements() {
		return rdao.findByStatus("Pending");
	}
	
	public List<ReimbursementDTO> viewCompletedReimbursements() {
		List<ReimbursementDTO> newList = rdao.findByStatus("Denied");
		newList.addAll(rdao.findByStatus("Approved"));
		return newList;
	}
	
	public int submitReimbursement(ReimbursementDTO t) throws Exception {
		int result = rdao.create(t);
		if(result==0) {
			throw new Exception("The request was not submitted.");
		}
		return result;
	}
	
	public int approveReimbursement(Integer i, Integer i2) {
		return rdao.approveRequest(i, i2);
	}
	
	public int denyReimbursement(Integer i, Integer i2) {
		return rdao.denyRequest(i, i2);
	}
	
	public int deleteReimbursement(Integer i) {
		return rdao.delete(i);
	}
	
}

package com.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.model.ReimbursementDTO;
import com.web.model.UserDTO;
import com.web.service.ReimbursementService;
import com.web.service.UserService;

public class ReimbursementController {

	final static Logger logger = Logger.getLogger(ReimbursementController.class);

	private ReimbursementService rs;
	private UserService us;

	public ReimbursementController(ReimbursementService rs, UserService us) {
		super();
		this.rs = rs;
		this.us = us;
	}

	public ReimbursementController() {
		this(new ReimbursementService(), new UserService());
	}

	public void sendAllData(HttpServletResponse res) {
		res.setContentType("text/json");
		List<ReimbursementDTO> reimbursements = rs.ViewAllReimbursements();
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursements));
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void sendPending(HttpServletResponse res) {
		res.setContentType("text/json");
		List<ReimbursementDTO> reimbursements = rs.viewPendingReimbursements();
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursements));
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void sendComplete(HttpServletResponse res) {
		res.setContentType("text/json");
		List<ReimbursementDTO> reimbursements = rs.viewCompletedReimbursements();
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursements));
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void sendSpecificData(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/json");
		String name = req.getParameter("name");
		List<ReimbursementDTO> reimbursements = rs.viewUserReimbursements(name);
		try {
			res.getWriter().println(new ObjectMapper().writeValueAsString(reimbursements));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void removeReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String numberString = req.getParameter("id");
//		System.out.println(numberString);
		int number = 0;
		try {
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			res.getWriter().println("You didn't enter a number!");
			e.printStackTrace();
			logger.error(e);
		}
		int updated = rs.deleteReimbursement(number);
		if (updated > 0) {
			res.getWriter().println("Something was deleted.");
			logger.info("User deleted an entry");
		} else {
			res.getWriter().println("Nothing was deleted!");
			logger.info("User failed to delete an entry");
		}
		System.out.println(updated);
	}
	
	public void approveReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			ReimbursementDTO r = new ObjectMapper().readValue(req.getInputStream(), ReimbursementDTO.class);
//			System.out.println(r);
			UserDTO d = us.findUserId(r.getResolver());
//			System.out.println(d);
			r.setResolver_id(d.getId());
//			System.out.println(r);
			try {
				int reimResult = rs.approveReimbursement(r.getId(),r.getResolver_id());
				res.getWriter().println("Reimbursement was approved!");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				res.getWriter().println("Something went wrong.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			res.getWriter().println("Something went wrong.");
		}
	}
	
	public void denyReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			ReimbursementDTO r = new ObjectMapper().readValue(req.getInputStream(), ReimbursementDTO.class);
//			System.out.println(r);
			UserDTO d = us.findUserId(r.getResolver());
//			System.out.println(d);
			r.setResolver_id(d.getId());
//			System.out.println(r);
			try {
				int reimResult = rs.denyReimbursement(r.getId(),r.getResolver_id());
				res.getWriter().println("The reimbursement was denied.");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				res.getWriter().println("Something went wrong.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			res.getWriter().println("Something went wrong.");
		}
	}

	public void save(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			ReimbursementDTO r = new ObjectMapper().readValue(req.getInputStream(), ReimbursementDTO.class);
//			System.out.println(r);
			UserDTO d = us.findUserId(r.getAuthor());
//			System.out.println(d);
			r.setAuthor_id(d.getId());
//			System.out.println(r);
			try {
				int reimResult = rs.submitReimbursement(r);
				res.getWriter().println("The reimbursement was added.");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				res.getWriter().println("Something went wrong.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			res.getWriter().println("Something went wrong.");
		}
	}
}
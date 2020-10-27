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
		System.out.println(numberString);
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
		String numberString = req.getParameter("approve");
		System.out.println(numberString);
		int number = 0;
		try {
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			res.getWriter().println("You didn't enter a number!");
			e.printStackTrace();
			logger.error(e);
		}
		int updated = rs.approveReimbursement(number);
		if (updated > 0) {
			res.getWriter().println("An entry was approved.");
			logger.info("Finance manager approved an entry.");
		} else {
			res.getWriter().println("Entry not approved.");
			logger.info("Finance manager failed to approve a valid entry.");
		}
		System.out.println(updated);
	}
	
	public void denyReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String numberString = req.getParameter("deny");
		System.out.println(numberString);
		int number = 0;
		try {
			number = Integer.parseInt(numberString);
		} catch (Exception e) {
			res.getWriter().println("You didn't enter a number!");
			e.printStackTrace();
			logger.error(e);
		}
		int updated = rs.denyReimbursement(number);
		if (updated > 0) {
			res.getWriter().println("An entry was denied.");
			logger.info("Finance manager denied an entry.");
		} else {
			res.getWriter().println("Entry not denied.");
			logger.info("Finance manager failed to deny a valid entry.");
		}
		System.out.println(updated);
	}

	public void save(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			ReimbursementDTO r = new ObjectMapper().readValue(req.getInputStream(), ReimbursementDTO.class);
			System.out.println(r);
			UserDTO d = us.findUserId(r.getAuthor());
			System.out.println(d);
			r.setAuthor_id(d.getId());
			System.out.println(r);
			try {
				int reimResult = rs.submitReimbursement(r);
				res.getWriter().println("the reimbursement was added");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				res.getWriter().println("something went wrong");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			res.getWriter().println("something went wrong");
		}
	}
}
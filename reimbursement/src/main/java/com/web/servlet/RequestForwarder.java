package com.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.web.controller.ReimbursementController;
import com.web.controller.UserController;

public class RequestForwarder {

	final static Logger logger = Logger.getLogger(RequestForwarder.class);
	
	public String routes(HttpServletRequest req) {
		logger.info(req.getRequestURI());
		switch (req.getRequestURI()){
		case "/reimbursement/employee.page":
			logger.info("Navigating to single employee page");
			return "html/employeeReimbursements.html";
		case "/reimbursement/finance.page":
			logger.info("Navigating to all finance page");
			return "html/financeControl.html";
		default: 
			logger.info("Warning: User fell through in page navigation");
			return "html/main.html";
		}
	}
	
	public void data(HttpServletRequest req, HttpServletResponse res) throws IOException {
		logger.info(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/reimbursement/verify.json":
			logger.info("User logging in");
			new UserController().login(req, res);
			break;
		case "/reimbursement/register.json":
			new UserController().register(req, res);
			break;
		case "/reimbursement/empReim.json":
			new ReimbursementController().save(req, res);
			break;
		case "/reimbursement/allR.json":
			new ReimbursementController().sendAllData(res);
			break;
		case "/reimbursement/specR.json":
			new ReimbursementController().sendSpecificData(req, res);
			break;
		case "/reimbursement/showPending.json":
			new ReimbursementController().sendPending(res);
			break;
		case "/reimbursement/showCompleted.json":
			new ReimbursementController().sendComplete(res);
			break;
		case "/reimbursement/removed.json":
			new ReimbursementController().removeReimbursement(req, res);
			break;
		case "/reimbursement/approved.json":
			new ReimbursementController().approveReimbursement(req, res);
			break;
		case "/reimbursement/denied.json":
			new ReimbursementController().denyReimbursement(req, res);
			break;
		}
	}
}

package com.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.controller.ReimbursementController;
import com.web.controller.UserController;

public class RequestForwarder {

	public String routes(HttpServletRequest req) {
		switch (req.getRequestURI()){
		case "/reimbursement/employee.page":
			return "html/employeeReimbursements.html";
		case "/reimbursement/finance.page":
			return "html/financeControl.html";
		default: 
			return "html/main.html";
		}
	}
	
	public void data(HttpServletRequest req, HttpServletResponse res) throws IOException {
		switch(req.getRequestURI()) {
		case "/reimbursement/verify.json":
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

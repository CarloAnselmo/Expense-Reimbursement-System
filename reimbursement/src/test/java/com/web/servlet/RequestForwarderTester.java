package com.web.servlet;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.web.controller.ReimbursementController;
import com.web.controller.UserController;

public class RequestForwarderTester {
	
	private RequestForwarder rf;
	private ReimbursementController rc;
	private UserController uc;
	HttpServletRequest req;
	HttpServletResponse res;
	static Logger logger;
	
	@Before
	public void setup() {
		logger = Logger.getLogger(RequestForwarderTester.class);
		rf = new RequestForwarder();
		rc = Mockito.mock(ReimbursementController.class);
		uc = Mockito.mock(UserController.class);
		req = Mockito.mock(HttpServletRequest.class);
		res = Mockito.mock(HttpServletResponse.class);
	}

//	@Test
//	public void dataTest() {
//		Mockito.when(req.getRequestURI()).thenReturn("/reimbursement/verify.json");
//		try {
//			rf.data(req, res);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void routeTest() {
//		Mockito.when(req.getRequestURI()).thenReturn("/reimbursement/employee.page");
//		Mockito.when(logger.info("a")).thenReturn("asd");
//		assertNotNull(rf.routes(req));
//		Mockito.when(req.getRequestURI()).thenReturn("/reimbursement/finance.page");
//		assertNotNull(rf.routes(req));
//	}
	
}

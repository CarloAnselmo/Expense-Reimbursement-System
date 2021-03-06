package com.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.model.ReimbursementDTO;
import com.web.model.UserDTO;
import com.web.repo.UserDao;
import com.web.service.UserService;

public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);

	private UserService us;

	public UserController(UserService us) {
		super();
		this.us = us;
	}

	public UserController() {
		this(new UserService());
	}

	public void login(HttpServletRequest req, HttpServletResponse res) {
		try {
			UserDTO u = new ObjectMapper().readValue(req.getInputStream(), UserDTO.class);
			UserDTO u2 = us.verifyLoginCredentials(u.getUsername(), u.getPassword());
			// Success, now output the logged user.
			res.getWriter().println(new ObjectMapper().writeValueAsString(u2));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public void register(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			UserDTO u = new ObjectMapper().readValue(req.getInputStream(), UserDTO.class);
			System.out.println(u);
			try {
				int userResult = us.registerEmployee(u);
				res.getWriter().println("The user was created.");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				res.getWriter().println("The user was not created.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
			res.getWriter().println("Something went wrong.");
		}
	}

}

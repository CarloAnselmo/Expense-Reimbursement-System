package com.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.model.UserDTO;

/**
 * sessions
 * 
 * 		server side user management
 * 			think of cookies, but for the server
 * 
 *
 */
public class SessionController {
	
	public void setSession(HttpServletRequest req, UserDTO m) {
		HttpSession session = req.getSession();
		session.setAttribute("user", m);
	}
	
	public UserDTO getSessionUser(HttpServletRequest req) {
		return (UserDTO)req.getSession().getAttribute("user");
	}
	
	public void invalidate(HttpServletRequest req) {
		req.getSession().invalidate();
	}

}

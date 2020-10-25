package com.web.service;

import com.web.model.UserDTO;
import com.web.repo.DaoContract;
import com.web.repo.UserDao;

public class UserService {
	
	private DaoContract<UserDTO, Integer, String> udao;

	public UserService(DaoContract<UserDTO, Integer, String> udao) {
		super();
		this.udao = udao;
	}
	
//	private UserDao udao;
//	
//	public UserService(UserDao udao) {
//		super();
//		this.udao = udao;
//	}

	public UserService() {
		this(new UserDao());
	}
	
	public int registerEmployee(UserDTO t) throws Exception {
		int result = udao.create(t);
		if(result==0) {
			throw new Exception("The user was not created.");
		}
		return result;
	}
	
	public UserDTO verifyLoginCredentials(String name, String pass) {
		return udao.findByNamePass(name, pass);
	}

}

package com.web.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.model.Reimbursement;
import com.web.model.ReimbursementStatus;
import com.web.model.ReimbursementType;
import com.web.model.User;
import com.web.model.UserRole;
import com.web.util.ConnectionUtil;

public class VerboseReimbursementDao implements DaoContract<Reimbursement, Integer, String> {
	
	final static Logger logger = Logger.getLogger(ReimbursementDao.class);

	public static void main(String[] args) {
		VerboseReimbursementDao dao = new VerboseReimbursementDao();
		List<Reimbursement> tooMuchData = dao.findAll();
		for(Reimbursement r : tooMuchData) {
			System.out.println(r);
		}
		System.out.println();
		
		Reimbursement single = dao.findById(1);
		System.out.println(single);
	}
	
	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> reimbursements = new LinkedList<>();
		String sql = "select * from reimbursement_all";
		try(Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class), rs.getString("reimb_description"), rs.getBinaryStream("reimb_receipt"),
						new User(rs.getInt("auth_id"), rs.getString("auth_username"), rs.getString("auth_password"), rs.getString("auth_first_name"), rs.getString("auth_last_name"), rs.getString("auth_email"),
								new UserRole(rs.getInt("auth_role_id"), rs.getString("auth_role"))),
						new User(rs.getInt("resolver_id"), rs.getString("resolver_username"), rs.getString("resolver_password"), rs.getString("resolver_first_name"), rs.getString("resolver_last_name"), rs.getString("resolver_email"),
								new UserRole(rs.getInt("resolver_role_id"), rs.getString("resolver_role"))),
						new ReimbursementStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")),
						new ReimbursementType(rs.getInt("reimb_type_id"), rs.getString("reimb_type"))));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public Reimbursement findById(Integer i) {
		Reimbursement r = null;
		String sql = "select * from reimbursement_all where reimb_id=?";
		try(Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class), rs.getString("reimb_description"), rs.getBinaryStream("reimb_receipt"),
						new User(rs.getInt("auth_id"), rs.getString("auth_username"), rs.getString("auth_password"), rs.getString("auth_first_name"), rs.getString("auth_last_name"), rs.getString("auth_email"),
								new UserRole(rs.getInt("auth_role_id"), rs.getString("auth_role"))),
						new User(rs.getInt("resolver_id"), rs.getString("resolver_username"), rs.getString("resolver_password"), rs.getString("resolver_first_name"), rs.getString("resolver_last_name"), rs.getString("resolver_email"),
								new UserRole(rs.getInt("resolver_role_id"), rs.getString("resolver_role"))),
						new ReimbursementStatus(rs.getInt("reimb_status_id"), rs.getString("reimb_status")),
						new ReimbursementType(rs.getInt("reimb_type_id"), rs.getString("reimb_type")));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public int update(Reimbursement t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int create(Reimbursement t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reimbursement> findByName(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement findByNamePass(String s, String s2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findByStatus(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateStatus(Integer i, Integer i2) {
		// TODO Auto-generated method stub
		return 0;
	}

}

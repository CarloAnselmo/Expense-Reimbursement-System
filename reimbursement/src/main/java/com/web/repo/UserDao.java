package com.web.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.SupportedSourceVersion;

import org.apache.log4j.Logger;

import com.web.model.UserDTO;
import com.web.util.ConnectionUtil;

public class UserDao implements DaoContract<UserDTO, Integer, String> {

	final static Logger logger = Logger.getLogger(UserDao.class);

//	public static void main(String[] args) {
//		UserDao dao = new UserDao();
//		UserDTO test = dao.findByNamePass("carlo", "1");
//		System.out.println(test);
//	}

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> users = new LinkedList<>();
		String sql = "select * from complete_users where ers_users_id>0";
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users.add(new UserDTO(rs.getInt("ers_users_id"), rs.getString("ers_username"),
						rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"),
						rs.getString("user_email"), rs.getString("user_role")));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public UserDTO findByNamePass(String s, String s2) {
		UserDTO u = null;
		String sql = "select * from complete_users where ers_username = ? and ers_password = md5(?||?||'cool');";
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s);
			ps.setString(3, s2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new UserDTO(rs.getInt("ers_users_id"), rs.getString("ers_username"), rs.getString("ers_password"),
						rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"),
						rs.getString("user_role"));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public int create(UserDTO t) {
		int created = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "insert into ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email) values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstname());
			ps.setString(4, t.getLastname());
			ps.setString(5, t.getEmail());
			created = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return created;
	}

	@Override
	public int update(UserDTO t) {
		int updated = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "update ers_users set ers_username=?, ers_password=?, user_first_name=?, user_last_name=?, user_email=?, user_role_id=? where ers_users_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstname());
			ps.setString(4, t.getLastname());
			ps.setString(5, t.getEmail());
			ps.setInt(6, t.getRole_id());
			ps.setInt(7, t.getId());
			updated = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public int delete(Integer i) {
		int updated = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "delete from ers_users where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			updated = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return updated;
	}

	@Override
	public UserDTO findById(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findByName(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findByStatus(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateStatus(Integer i, Integer i2) {
		// TODO Auto-generated method stub
		return 0;
	}

}

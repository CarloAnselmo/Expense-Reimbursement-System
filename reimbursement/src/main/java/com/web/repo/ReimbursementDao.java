package com.web.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.web.model.ReimbursementDTO;
import com.web.util.ConnectionUtil;

public class ReimbursementDao implements DaoContract<ReimbursementDTO, Integer, String> {
	
	final static Logger logger = Logger.getLogger(ReimbursementDao.class);
	
//	public static void main(String[] args) {
//		ReimbursementDao dao = new ReimbursementDao();
//		int updated = dao.approveRequest(4);
//		System.out.println(updated);
//	}
	
	@Override
	public List<ReimbursementDTO> findAll() {
		List<ReimbursementDTO> reimbursements = new LinkedList<>();
		String sql = "select * from complete_reimbursement";
		try(Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(new ReimbursementDTO(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class), rs.getString("reimb_description"), rs.getBinaryStream("reimb_receipt"),
						rs.getString("reimb_author"), rs.getString("reimb_resolver"), rs.getString("reimb_status"), rs.getString("reimb_type")));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	@Override
	public List<ReimbursementDTO> findByName(String s) {
		List<ReimbursementDTO> reimbursements = new LinkedList<>();
		String sql = "select * from complete_reimbursement where reimb_author=?";
		try(Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(new ReimbursementDTO(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class), rs.getString("reimb_description"), rs.getBinaryStream("reimb_receipt"),
						rs.getString("reimb_author"), rs.getString("reimb_resolver"), rs.getString("reimb_status"), rs.getString("reimb_type")));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	@Override
	public List<ReimbursementDTO> findByStatus(String s) {
		List<ReimbursementDTO> reimbursements = new LinkedList<>();
		String sql = "select * from complete_reimbursement where reimb_status=?";
		try(Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				reimbursements.add(new ReimbursementDTO(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class), rs.getString("reimb_description"), rs.getBinaryStream("reimb_receipt"),
						rs.getString("reimb_author"), rs.getString("reimb_resolver"), rs.getString("reimb_status"), rs.getString("reimb_type")));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	@Override
	public ReimbursementDTO findById(Integer i) {
		ReimbursementDTO r = null;
		String sql = "select * from complete_reimbursement where reimb_id=?";
		try(Connection conn = ConnectionUtil.getInstance().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				r = new ReimbursementDTO(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class), rs.getString("reimb_description"), rs.getBinaryStream("reimb_receipt"),
						rs.getString("reimb_author"), rs.getString("reimb_resolver"), rs.getString("reimb_status"), rs.getString("reimb_type"));
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return r;
	}	
	
	@Override
	public int update(ReimbursementDTO t) {
		int updated = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "update ers_reimbursement set reimb_amount=?, reimb_submitted=?, reimb_resolved=?, reimb_description=?, reimb_receipt=?,"
					+ " reimb_author=?, reimb_resolver=?, reimb_status_id=?, reimb_type_id=? where reimb_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, t.getAmount());
			ps.setTimestamp(2, Timestamp.valueOf(t.getSubmitted()));
			if(t.getResolved()==null) {
				ps.setTimestamp(3,null);
			} else {
				ps.setTimestamp(3, Timestamp.valueOf(t.getResolved()));
			}
			ps.setString(4, t.getDescription());
			if(t.getReceipt()==null) {
				ps.setTimestamp(5,null);
			} else {
				ps.setBinaryStream(5, t.getReceipt());
			}
			ps.setInt(6, t.getAuthor_id());
			ps.setInt(7, t.getResolver_id());
			ps.setInt(8, t.getStatus_id());
			ps.setInt(9, t.getType_id());
			ps.setInt(10, t.getId());
			updated = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return updated;
	}
	
	public int approveRequest(Integer i, Integer i2) {
		int updated = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "call approve(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ps.setInt(2, i2);
			updated = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return updated;
	}
	
	public int denyRequest(Integer i, Integer i2) {
		int updated = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "call deny(?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i);
			ps.setInt(2, i2);
			updated = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return updated;
	}
	
	@Override
	public int create(ReimbursementDTO t) {
		int created = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "insert into ers_reimbursement (reimb_amount, reimb_description, reimb_receipt, reimb_author, reimb_type_id) values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, t.getAmount());
			ps.setString(2, t.getDescription());
			if(t.getReceipt()==null) {
				ps.setTimestamp(3,null);
			} else {
				ps.setBinaryStream(3, t.getReceipt());
			}
			ps.setInt(4, t.getAuthor_id());
			ps.setInt(5, t.getType_id());
			created = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return created;
	}

	@Override
	public int delete(Integer i) {
		int updated = 0;
		try (Connection conn = ConnectionUtil.getInstance().getConnection()) {
			String sql = "delete from ers_reimbursement where reimb_id = ? and reimb_status_id = 1";
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
	public ReimbursementDTO findByNamePass(String s, String s2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementDTO findByNameSingle(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}

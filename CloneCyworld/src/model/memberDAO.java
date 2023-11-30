package model;

import java.sql.SQLException;

import common.DBConnector;

public class memberDAO extends DBConnector {

	public memberDAO() {
		super();
	}

	public int CreateMember(member cyMember) throws ClassNotFoundException {
		String INSERT_MEMBER_SQL = "insert into member (id, password, email, phone, isAdmin, imgFile) values "
				+ "(?, ?, ?, ?, ?, ?)";

		int result = 0;

		try {
			psmt = con.prepareStatement(INSERT_MEMBER_SQL);
			psmt.setString(1, cyMember.getId());
			psmt.setString(2, cyMember.getPassword());
			psmt.setString(3, cyMember.getEmail());
			psmt.setString(4, cyMember.getPhone());
			psmt.setString(5, cyMember.getIsAdmin());
			psmt.setString(6, cyMember.getImgFile());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int CheckDuplicateId(String id) {
		String GET_MEMBER_SQL = "select id from member where id=?";

		int result = 0;

		try {
			psmt = con.prepareStatement(GET_MEMBER_SQL);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int LoginCheck(String id, String pw) {
		String Check_MEMBER_SQL = "select * from member where id=? and password=?";
		
		int result = 0;
		
		try {
			psmt = con.prepareStatement(Check_MEMBER_SQL);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public member selectMember(String id) {
		member dto = new member();
		String CYWORLD_MEMBER_SQL = "select * from member where id = ?";
		try {
			psmt = con.prepareStatement(CYWORLD_MEMBER_SQL);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setPhone(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setIsAdmin(rs.getString(5));
				dto.setImgFile(rs.getString(6));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}

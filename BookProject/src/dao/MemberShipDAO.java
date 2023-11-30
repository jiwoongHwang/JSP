package dao;

import common.JDBConnect;
import dto.MemberShipDTO;

public class MemberShipDAO extends JDBConnect {
	
	public MemberShipDAO() {
		super();
	}
	
	public MemberShipDTO selectMember(String id, String pw) {
		MemberShipDTO dto = new MemberShipDTO();
		String sql = "select * from membership where memberid=? and memberpw=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			System.out.println("로그인 성공");
			if(rs.next()) {
				dto.setId(rs.getString(1));
				dto.setPw(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setTel(rs.getString(5));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("로그인 실패");
		}
		return dto;
	}
	
	public void insertMember(String id, String pw, String name, String address, String tel) {
		String sql = "insert into membership values(?, ?, ?, ?, ?)";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			psmt.setString(4, address);
			psmt.setString(5, tel);
			psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 실패");
		}
	}
	
	public String deleteMember(String id, String pw) {
		String sql = "select * from membership where memberid=?";
		String result = "";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String pw2 = rs.getString(2);
				
				if(pw.equals(pw2)) {
					result = "회원탈퇴 성공";
					String sql2 = "delete from membership where memberid=?";
					psmt = con.prepareStatement(sql2);
					psmt.setString(1, id);
					psmt.executeUpdate();
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원탈퇴실패");
		}
		
		return result;
	}
}

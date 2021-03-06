package com.tb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tb.beans.User;
import com.tb.utils.DBConnector;

public class UserDAO {
	private String usersTable = "users";
	private Statement stm;
	public UserDAO() {
		try {
			Connection con = DBConnector.getInstance().getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public User createAccount(String name,String email,String pw) throws SQLException{
		User u = new User();
		String sql = "insert into " + usersTable +"(name,email,encrypted_password) values" +
				"('"+ name +"','"+ email +"','"+ pw +"') ";
		
		stm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet  rs = stm.getGeneratedKeys();
		int id =0;
		while(rs.next()){
			id = rs.getInt(1);
		}
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setEncryptedPassword(pw);
		return u;
	}
	public User loginCheck(String email, String password) throws SQLException{
		ResultSet rs = stm.executeQuery("select id,name, email, encrypted_password from " + usersTable + " WHERE email = '"
	                                  + email + "' and encrypted_password = '" + password + "'");
		User u = null;
	    
	    while (rs.next()) {
	    	u = new User();
	    	u.setId(rs.getInt(1));
	    	u.setName(rs.getString(2));
	    	u.setEmail(rs.getString(3));
	    	u.setEncryptedPassword(rs.getString(4));
	    }
	    return u;
	}

	public List<User> fetchAll(int pageNo) throws SQLException {
	    ResultSet rs = stm.executeQuery("select * from " + usersTable + " limit " + (pageNo - 1) * 4 + ", 4" );
	    List<User> users = new ArrayList<User>();
	   
	    User u = new User();
	    while (rs.next()) {
	    	u.setId(rs.getInt(1));
	    	u.setAdmin(rs.getString(16));
	    	u.setCurrentSignInAt(rs.getString(9));
	    	u.setCurrentSignInIp(rs.getString(11));
	    	u.setEmail(rs.getString(2));
	    	u.setEncryptedPassword(rs.getString(3));
	    	u.setLastSignInAt(rs.getString(10));
	    	u.setLastSignInIp(rs.getString(12));
	    	u.setName(rs.getString(15));
	    	u.setPasswordSalt(rs.getString(4));
	    	u.setRememberCreatedAt(rs.getString(7));
	    	u.setRememberToken(rs.getString(6));
	    	u.setResetPasswordToken(rs.getString(5));
	    	u.setSignInCount(rs.getInt(8));
	    	users.add(u);
	    }
	    return users;
	}
	
	public User findById(int userId) throws SQLException {
		ResultSet rs = stm.executeQuery("select * from " + usersTable + " WHERE id = " + userId);
		
		User u = new User();
	    while (rs.next()) {
	    	u.setId(rs.getInt(1));
	    	u.setAdmin(rs.getString(16));
	    	u.setCurrentSignInAt(rs.getString(9));
	    	u.setCurrentSignInIp(rs.getString(11));
	    	u.setEmail(rs.getString(2));
	    	u.setEncryptedPassword(rs.getString(3));
	    	u.setLastSignInAt(rs.getString(10));
	    	u.setLastSignInIp(rs.getString(12));
	    	u.setName(rs.getString(15));
	    	u.setPasswordSalt(rs.getString(4));
	    	u.setRememberCreatedAt(rs.getString(7));
	    	u.setRememberToken(rs.getString(6));
	    	u.setResetPasswordToken(rs.getString(5));
	    	u.setSignInCount(rs.getInt(8));
	    	
	    }
	    return u;
	}
	public int countUpdates( int userId) throws SQLException{
		ResultSet rs   = stm.executeQuery("select sign_in_count from " + usersTable + " where id = "+ userId );
		int count = 0;
		
		while(rs.next()){
			
			count = rs.getInt(1);
		}
		
		 stm.executeUpdate("update "+ usersTable  + " set  sign_in_count = " + (count+1) + " where id = " + userId );
		
		return count+1;
	}
}

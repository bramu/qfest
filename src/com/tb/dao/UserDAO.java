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
	public User createAccount(String name,String email,String pw,String c_pw) throws SQLException{
		User u = new User();
		int id = stm.executeUpdate("insert into " + usersTable +"(name,email,password,confirm_pw) values" +
				"('"+ name +"','"+ email +"','"+ pw +"','"+ c_pw +"') ");		
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setPassword(pw);
		return u;
	}
	public User loginCheck(String email, String password) throws SQLException{
		ResultSet rs = stm.executeQuery("select name, email, password from " + usersTable + " WHERE email = '"
	                                  + email + "' and password = '" + password + "'");
		User u = null;
	    
	    while (rs.next()) {
	    	u = new User();
	    	u.setName(rs.getString(1));
	    	u.setEmail(rs.getString(2));
	    	u.setPassword(rs.getString(3));
	    }
	    return u;
	}
	public int countUpdate( String countColumn,int userId) throws SQLException{
		ResultSet rs   = stm.executeQuery("select "+ countColumn +" from " + usersTable + " where id = "+ userId );
		int count = 0;
		
		while(rs.next()){
			
			count = rs.getInt(1);
		}
		
		int finalcount = stm.executeUpdate("update "+ usersTable  + " set " + countColumn + " = " + (count+1) + " where id = " + userId );
		
		return finalcount;
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
		
		int finalcount = stm.executeUpdate("update "+ usersTable  + " set  sign_in_count = " + (count+1) + " where id = " + userId );
		
		return finalcount;
	}
}

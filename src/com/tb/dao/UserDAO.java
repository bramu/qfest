package com.tb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tb.beans.User;
import com.tb.utils.DBConnector;

public class UserDAO {
	private String usertable = "userdata";
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
		int id = stm.executeUpdate("insert into " + usertable +"(name,email,password,confirm_pw) values" +
				"('"+ name +"','"+ email +"','"+ pw +"','"+ c_pw +"') ");		
		u.setId(id);
		u.setName(name);
		u.setEmail(email);
		u.setPassword(pw);
		u.setConfirm_pw(c_pw);
		return u;
	}
	public User loginCheck(String email, String password) throws SQLException{
		ResultSet rs = stm.executeQuery("select name, email, password from " + usertable + " WHERE email = '"
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
}

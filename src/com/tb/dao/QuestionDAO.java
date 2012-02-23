package com.tb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tb.beans.*;
import com.tb.utils.*;

public class QuestionDAO {
	private String tableName = "questions";
	private String usertable = "userdata";
	private Statement stm;
	public QuestionDAO() {
		try {
			
			Connection con = DBConnector.getInstance().getConnection();
					
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTotalCount() throws SQLException {
	    ResultSet rs = stm.executeQuery("select count(*) as total_count from " + tableName);
	    
	    while (rs.next()) {
	    	return rs.getInt(1);
	    }
	    return 0;
	}
	
	public List<Question> fetchAll(int pageNo) throws SQLException {
	    ResultSet rs = stm.executeQuery("select id, title from " + tableName + " limit " + (pageNo - 1) * 3 + ", 3" );
	    List<Question> questions = new ArrayList<Question>();
	    
	    while (rs.next()) {
	    	Question q = new Question();
	    	q.setId(rs.getInt(1));
	    	q.setTitle(rs.getString(2));
	    	questions.add(q);
	    }
	    return questions;
	}
	
	public Question findById(int questionId) throws SQLException {
		ResultSet rs = stm.executeQuery("select id, title from " + tableName + " WHERE id = " + questionId);
		Question q = new Question();
	    while (rs.next()) {
	    	q.setId(rs.getInt(1));
	    	q.setTitle(rs.getString(2));
	    }
	    return q;
	}
	
	
	public Question create(String title) throws SQLException {
		Question q = new Question();
		int id = stm.executeUpdate("insert into "+ tableName +"(title,company_id) values('"+ title +"','1')");
		q.setId(id);
		q.setTitle(title);
		return q;
	}
	
	public Question update(int id,String title) throws SQLException {
		Question q = new Question();
		int question_id = stm.executeUpdate("update "+ tableName +" set title = '"+ title + "' where id=" + id);
		q.setId(id);
		q.setTitle(title);
		return q;
	}
	
	public void delete( int id) throws SQLException {
		int id1 = stm.executeUpdate("delete from "+ tableName +" where id=" + id);	
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

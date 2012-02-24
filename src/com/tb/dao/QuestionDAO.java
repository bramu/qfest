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
	private String answerstable = "answers";
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
	public List<Question> unanswered(int pageNo) throws SQLException{
		String sql = "select id,title from "+ tableName +" where title not in" +
				"(select title from "+ tableName +" q, "+ answerstable +" a where a.question_id = q.id ) " +
						" limit " + (pageNo - 1) * 3 + ",3";
		List<Question> questions = new ArrayList<Question>();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()){
			Question q = new Question();
			q.setId(rs.getInt(1));
			q.setTitle(rs.getString(2));
			questions.add(q);
		}
		return questions;
	}
	public int getTotalUnansweredCount() throws SQLException {
		String sql = "select count(*) as totalUnansweredCount from "+ tableName +" where title not in" +
				"(select title from "+ tableName +" q, "+ answerstable +" a where a.question_id = q.id )";
	    ResultSet rs = stm.executeQuery(sql);
	    
	    while (rs.next()) {
	    	return rs.getInt(1);
	    }
	    return 0;
	}
	
}

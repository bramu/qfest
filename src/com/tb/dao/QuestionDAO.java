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
	private String questionsTable = "questions";
	private Statement stm;
	UserDAO udao = new UserDAO();
	public QuestionDAO() {
		try {
			
			Connection con = DBConnector.getInstance().getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTotalCount() throws SQLException {
	    ResultSet rs = stm.executeQuery("select count(*) as total_count from " + questionsTable);
	    
	    while (rs.next()) {
	    	return rs.getInt(1);
	    }
	    return 0;
	}
	
	public List<Question> fetchAll(int pageNo) throws SQLException {
	    ResultSet rs = stm.executeQuery("select * from " + questionsTable + " limit " + (pageNo - 1) * 4 + ", 4" );
	    List<Question> questions = new ArrayList<Question>();
	    
	    while (rs.next()) {
	    	Question q = new Question();
	    	q.setUser(udao.findById(rs.getInt(1)));
	    	q.setId(rs.getInt(1));
	    	q.setAnswersCount(rs.getInt(12));
	    	q.setBookmarksCount(rs.getInt(16));
	    	q.setCommentsCount(rs.getInt(13));
	    	q.setFlag(rs.getInt(8));
	    	q.setInappCount(rs.getInt(15));
	        q.setInterviewId(rs.getInt(4));
	        q.setNoCount(rs.getInt(11));
	        q.setQuestionText(rs.getString(2));
	        q.setSource(rs.getString(3));
	        q.setTitle(rs.getString(9));
	        q.setUserId(rs.getInt(5));
	        q.setViewsCount(rs.getInt(14));
	        q.setYesCount(rs.getInt(10));
	    	questions.add(q);
	    }
	    return questions;
	}
	
	public Question findById(int questionId) throws SQLException {
		ResultSet rs = stm.executeQuery("select * from  " + questionsTable + " WHERE id = " + questionId);
		Question q = new Question();
	    while (rs.next()) {
	    	q.setUser(udao.findById(rs.getInt(1)));
	    	q.setId(rs.getInt(1));
	    	q.setAnswersCount(rs.getInt(12));
	    	q.setBookmarksCount(rs.getInt(16));
	    	q.setCommentsCount(rs.getInt(13));
	    	q.setFlag(rs.getInt(8));
	    	q.setInappCount(rs.getInt(15));
	        q.setInterviewId(rs.getInt(4));
	        q.setNoCount(rs.getInt(11));
	        q.setQuestionText(rs.getString(2));
	        q.setSource(rs.getString(3));
	        q.setTitle(rs.getString(9));
	        q.setUserId(rs.getInt(5));
	        q.setViewsCount(rs.getInt(14));
	        q.setYesCount(rs.getInt(10));
	    }
	    return q;
	}
	
	
	public Question create(String title) throws SQLException {
		Question q = new Question();
		int id = stm.executeUpdate("insert into "+ questionsTable +"(title,company_id, created_at, updated_at) values('"+ title +"','1', NOW(), NOW())");
		q.setId(id);
		q.setTitle(title);
		return q;
	}
	public int countUpdates( String count_column,int question_id) throws SQLException{
		ResultSet rs   = stm.executeQuery("select "+ count_column +" from " + questionsTable + " where id = "+ question_id );
		int count = 0;
		
		while(rs.next()){
			
			count = rs.getInt(1);
		}
		
		 stm.executeUpdate("update "+ questionsTable  + " set " + count_column + " = " +( count+1) + " where id = " + question_id );
		
		return count+1 ;
	}
	
	public List<Question> unanswered(int pageNo) throws SQLException{
		String sql = "select id,title from " + questionsTable + " where answers_count = 0  order by id desc " +
						" limit " + (pageNo - 1) * 4 + ",4";
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
		String sql = "select count(*) from "+ questionsTable +" where answers_count = 0";
	    ResultSet rs = stm.executeQuery(sql);
	    
	    while (rs.next()) {
	    	return rs.getInt(1);
	    }
	    return 0;
	}
	
	public List<Question> recent(int pageNo) throws SQLException{
		String sql="SELECT id, title FROM "+ questionsTable +" ORDER BY id DESC limit " + (pageNo - 1) * 4  + ", 4";
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
	
	public List<Question> mostViewed(int pageNo) throws SQLException{
		String sql="SELECT id, title FROM "+ questionsTable +" ORDER BY views_count DESC limit " + (pageNo - 1) * 4  + ", 4";
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
	
	public List<Question> mostRated(int pageNo) throws SQLException{
		String sql="SELECT id, title FROM "+ questionsTable +" ORDER BY (yes_count + no_count) DESC limit " + (pageNo - 1) * 4  + ", 4";
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
	
	public List<Question> tags(int tag_id,int pageNo) throws SQLException{
		List<Question> questions = new ArrayList<Question>();
		String sql = "select question_text from questions q, qtags qt where q.id = qt.question_id and qt.tag_id = " + tag_id  
				+ " limit " + (pageNo-1)*4 + ",4" ;
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()){
			Question q = new Question();
			q.setId(rs.getInt(1));
			q.setTitle(rs.getString(2));
			questions.add(q);
		}
		return questions;		  
	}
	
	public int countUpdate( String countColumn,int questionId) throws SQLException{
		ResultSet rs   = stm.executeQuery("select "+ countColumn +" from " + questionsTable + " where id = "+ questionId );
		int count = 0;
		
		while(rs.next()){
			
			count = rs.getInt(1);
		}
		
		int finalcount = stm.executeUpdate("update "+ questionsTable  + " set " + countColumn + " = " + (count+1) + " where id = " + questionId );
		
		return finalcount;
	}
	 
}

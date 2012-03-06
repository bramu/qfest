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
	/* constructor for the connection with the database*/
	public QuestionDAO() {
		try {
			
			Connection con = DBConnector.getInstance().getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* count the number of questions in the questions table*/
	public int getTotalCount() throws SQLException {
	    ResultSet rs = stm.executeQuery("select count(*) as total_count from " + questionsTable);
	    
	    while (rs.next()) {
	    	return rs.getInt(1);
	    }
	    return 0;
	}
	/* get the list of questions from questions table*/
	public List<Question> fetchAll(int pageNo) throws SQLException {
	    ResultSet rs = stm.executeQuery("select * from " + questionsTable + " limit " + (pageNo - 1) * 20 + ", 20" );
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
	/* get one row from the questions table for the given questionId*/
	public Question findById(int questionId) throws SQLException {
		ResultSet rs = stm.executeQuery("select id,question_text,title from  " + questionsTable + " WHERE id = " + questionId);
		Question q = new Question();
	    while (rs.next()) {
	    	q.setUser(udao.findById(rs.getInt(1)));
	    	q.setId(rs.getInt(1));
	    	 q.setQuestionText(rs.getString(2));
	    	 q.setTitle(rs.getString(3));
	    	/*q.setAnswersCount(rs.getInt(12));
	    	q.setBookmarksCount(rs.getInt(16));
	    	q.setCommentsCount(rs.getInt(13));
	    	q.setFlag(rs.getInt(8));
	    	q.setInappCount(rs.getInt(15));
	        q.setInterviewId(rs.getInt(4));
	        q.setNoCount(rs.getInt(11));
	       ;
	        q.setSource(rs.getString(3));
	       
	        q.setUserId(rs.getInt(5));
	        q.setViewsCount(rs.getInt(14));
	        q.setYesCount(rs.getInt(10));*/
	    }
	    return q;
	}
	
	/* adding question and answer by the registered user*/
	public int create(int userId,String title,String questionText,String answerText) throws SQLException {
		Question q = new Question();
		int qId = 0;
		String sql = "insert into "+ questionsTable +"(user_id,title,question_text, created_at, updated_at) " +
				"values('"+ userId+"','"+ title +"','"+ questionText +"', NOW(), NOW())";
		stm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stm.getGeneratedKeys();
		if(rs.next()){
			qId = rs.getInt(1);
		}
		q.setId(qId);
		q.setTitle(title);
		q.setQuestionText(questionText);
		q.setUserId(userId);
		stm.executeUpdate("insert into answers(question_id,answer_text,user_id,created_at, updated_at) " +
				"values('"+ qId +"','"+ answerText +"','"+ userId +"',NOW(),NOW())");
		return qId;
	}
	/* updating the count for the given count type in the questions table*/
	public int countUpdates( String count_column,int question_id) throws SQLException{
		ResultSet rs   = stm.executeQuery("select "+ count_column +" from " + questionsTable + " where id = "+ question_id );
		int count = 0;
		
		while(rs.next()){
			
			count = rs.getInt(1);
		}
		
		 stm.executeUpdate("update "+ questionsTable  + " set " + count_column + " = " +( count+1) + " where id = " + question_id );
		
		return count+1 ;
	}
	/*get list of all unanswered questions*/
	public List<Question> unanswered(int pageNo) throws SQLException{
		String sql = "select id,title from " + questionsTable + " where answers_count = 0  order by id desc " +
						" limit " + (pageNo - 1) * 20 + ",20";
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
	/*count the all unanswered questions from the database*/
	public int getTotalUnansweredCount() throws SQLException {
		String sql = "select count(*) from "+ questionsTable +" where answers_count = 0";
	    ResultSet rs = stm.executeQuery(sql);
	    
	    while (rs.next()) {
	    	return rs.getInt(1);
	    }
	    return 0;
	}
	/* get the all recently added questions*/
	public List<Question> recent(int pageNo) throws SQLException{
		String sql="SELECT id, title FROM "+ questionsTable +" ORDER BY id DESC limit " + (pageNo - 1) * 20  + ", 20";
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
	/*get all the most viewed questions*/
	public List<Question> mostViewed(int pageNo) throws SQLException{
		String sql="SELECT id, title FROM "+ questionsTable +" ORDER BY views_count DESC limit " + (pageNo - 1) * 20 + ", 20";
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
	/*get the list of most liked questions*/
	public List<Question> mostRated(int pageNo) throws SQLException{
		String sql="SELECT id, title FROM "+ questionsTable +" ORDER BY (yes_count + no_count) DESC limit " + (pageNo - 1) * 20  + ", 20";
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
	/*get the list of the particular tag related questions*/
	public List<Question> tags(int tag_id,int pageNo) throws SQLException{
		List<Question> questions = new ArrayList<Question>();
		String sql = "select question_text from questions q, qtags qt where q.id = qt.question_id and qt.tag_id = " + tag_id  
				+ " limit " + (pageNo-1)*20 + ",20" ;
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()){
			Question q = new Question();
			q.setId(rs.getInt(1));
			q.setTitle(rs.getString(2));
			questions.add(q);
		}
		return questions;		  
	}
	/*to insert the comments for the question into the database*/
	public int submitComment(int questionId,String comment, int userId) throws SQLException{
		Comment c = new Comment();
		String sql =  "insert into comments(commentable_id,content,commentable_type,user_id,created_at,updated_at) " +
				"values('" + questionId+ "','"+ comment +"','question','"+ userId+"',NOW(),NOW())";
		stm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stm.getGeneratedKeys();
		int commentId = 0;
		
		while(rs.next()) {
			commentId = rs.getInt(1);
		}
		c.setCommentableId(questionId);
		c.setCommentableType("question");
		c.setCommentableId(commentId);
		c.setUserId(userId);
		return commentId;
	}
	/* to show the list of comments for the selected question from the database*/
	public List<Comment> listOfComments(int questionId) throws SQLException{
		ResultSet rs = stm.executeQuery("select content from comments where  commentable_type = 'question' and commentable_id = "+ questionId );
		List<Comment> comments = new ArrayList<Comment>();
		Comment c = new Comment();
		while(rs.next()){
			c.setContent(rs.getString(1));
			comments.add(c);
		}
		return comments;
	}
	 
}

package com.tb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tb.beans.Answer;
import com.tb.utils.DBConnector;

public class AnswerDAO {
	private String answersTable = "answers";
	private Statement stm;
	
	private QuestionDAO qdao = new QuestionDAO();
	private UserDAO udao = new UserDAO();
	/*constructor for connecting with the database*/
	public AnswerDAO() {
		try {

			Connection con = DBConnector.getInstance().getConnection();

			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*get the list of answers for the selected question */
	public List<Answer> listOfAnswers(int questionId) throws SQLException {
		ResultSet rs = stm.executeQuery("select answer_text from " + answersTable
				+ " where question_id=" + questionId);
		List<Answer> answers = new ArrayList<Answer>();
		while (rs.next()) {
			Answer a = new Answer();
			a.setAnswerText(rs.getString(1));
			answers.add(a);
		}
		return answers;
	}
	/*get all the list of answers from answers table*/
	public List<Answer> fetchAll(int pageNo) throws SQLException {
		ResultSet rs = stm.executeQuery("select * from " + answersTable
				+ " limit " + (pageNo - 1) * 4 + ", 4");
		List<Answer> answers = new ArrayList<Answer>();
		while (rs.next()) {
			Answer a = new Answer();
			a.setId(rs.getInt(1));
			a.setAnswerText(rs.getString(3));
			a.setQuestion(qdao.findById(rs.getInt(2)));
			a.setUser(udao.findById(rs.getInt(4)));
			a.setNoCount(rs.getInt(6));
			a.setQuestionId(rs.getInt(2));
			a.setYesCount(rs.getInt(5));
			answers.add(a);
		}
		return answers;
	}
	/*get one answer from answers table for the given answer id*/
	public Answer findById(int answerId) throws SQLException {
		ResultSet rs = stm.executeQuery("select * from " + answersTable
				+ " WHERE id = " + answerId);
		Answer a = new Answer();
		while (rs.next()) {
			a.setId(rs.getInt(1));
			a.setAnswerText(rs.getString(3));
			a.setQuestion(qdao.findById(rs.getInt(2)));
			a.setUser(udao.findById(rs.getInt(4)));
			a.setNoCount(rs.getInt(6));
			a.setQuestionId(rs.getInt(2));
			a.setYesCount(rs.getInt(5));
		}
		return a;
	}
	
	public int updateCounts(String countcolumn, int answerId)
			throws SQLException {
		ResultSet rs = stm.executeQuery("select " + countcolumn + " from "
				+ answersTable + " where id = " + answerId);
		int count = 0;

		while (rs.next()) {
			count = rs.getInt(1);
		}

		 stm.executeUpdate("update " + answersTable + " set "
				+ countcolumn + " = " + (count + 1) + " where id = "
				+ answerId);

		return count+1;
	}
	/* insert the answer for the selected question into the database*/
	public int submitAnswer(int questionId,String answer, int userId) throws SQLException{
		Answer a = new Answer();
		String sql =  "insert into "+ answersTable +"(question_id,answer_text,user_id,created_at,updated_at) " +
				"values('" + questionId+ "','"+ answer +"','"+ userId+"',NOW(),NOW())";
		stm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stm.getGeneratedKeys();
		int answerId = 0;
		while(rs.next()){
			answerId = rs.getInt(1);
		}
		a.setAnswerText(answer);
		a.setQuestionId(questionId);
		a.setUserId(userId);
		return answerId;
	}

}

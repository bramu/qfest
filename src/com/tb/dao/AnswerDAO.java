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
	private String answerstable = "answers";
	private Statement stm;
	public AnswerDAO() {
		try {
			
			Connection con = DBConnector.getInstance().getConnection();
					
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Answer> listOfAnswers(int id) throws SQLException{
		ResultSet rs = stm.executeQuery("select answer from "+ answerstable +" where question_id="+id);
		List<Answer> answers = new ArrayList<Answer>();
		while(rs.next()){
			Answer a = new Answer();
			a.setAnswer(rs.getString(1));
			answers.add(a);
		}
		return answers;
	}

}

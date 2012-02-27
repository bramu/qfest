package com.tb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tb.utils.DBConnector;
import com.tb.beans.*;
public class RatingDAO {
	private String ratingsTable = "ratings";
	private Statement stm;
	UserDAO udao = new UserDAO();
	public RatingDAO() {
		try {
			
			Connection con = DBConnector.getInstance().getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void Up(int questionId) throws SQLException{
		Rating r = new Rating();
		String sql = "INSERT INTO "+ ratingsTable  +" ( rtype,type_id,rating) VALUES ('question'," + questionId + ",1 )";
		int id = stm.executeUpdate(sql);
		r.setId(id);
		r.setRtype("question");
		r.setRating(1);
		Question q = new Question();
		ResultSet rs = stm.executeQuery("select yes_count from questions where id = " + questionId);
		int count = 0;
		while(rs.next()){
			 count = rs.getInt(1);
		}
		 stm.executeUpdate("UPDATE questions SET yes_count = '" + (count+1) + "'  WHERE id = " + questionId );
		q.setYesCount(count+1);
	}
	public void Down(int questionId) throws SQLException{
		Rating r = new Rating();
		String sql = "INSERT INTO "+ ratingsTable  +" ( rtype,type_id,rating) VALUES ('question'," + questionId + ",0 )";
		int id = stm.executeUpdate(sql);
		r.setId(id);
		r.setRtype("question");
		r.setRating(0);
		Question q = new Question();
		ResultSet rs = stm.executeQuery("select no_count from questions where id = " + questionId);
		int count = 0;
		while(rs.next()){
			 count = rs.getInt(1);
		}
		 stm.executeUpdate("UPDATE questions SET no_count = " + (count+1) + " WHERE id = " + questionId );
		q.setNoCount(count+1);
	}
	public void Inappropriate(int questionId) throws SQLException{
		Rating r = new Rating();
		String sql = "INSERT INTO "+ ratingsTable  +" ( rtype,type_id,rating) VALUES ('question'," + questionId + ",-1 )";
		int id = stm.executeUpdate(sql);
		r.setId(id);
		r.setRtype("question");
		r.setRating(-1);
		Question q = new Question();
		ResultSet rs = stm.executeQuery("select flag from questions where id = " + questionId);
		int count = 0;
		while(rs.next()){
			 count = rs.getInt(1);
		}
		 stm.executeUpdate("UPDATE questions SET flag = " + (count+1) + " WHERE id = " + questionId );
		q.setFlag(count+1);
	}
}

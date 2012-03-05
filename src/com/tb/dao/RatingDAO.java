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
	/* constructor for connecting the database*/
	public RatingDAO() {
		try {

			Connection con = DBConnector.getInstance().getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*inserting the rating type,type id and updating the like  count*/
	public void like(String type, int id) throws SQLException {
		Rating r = new Rating();
		String sql = "INSERT INTO " + ratingsTable
				+ " ( rtype,type_id,rating,created_at,updated_at) VALUES "
				+ "('" + type + "'," + id + ",1 ,NOW(),Now())";
		stm.executeUpdate(sql);
		r.setRtype(type);
		r.setRating(1);
		if (type.equals("question")) {
			Question q = new Question();
			ResultSet rs = stm
					.executeQuery("select yes_count from questions where id = "
							+ id);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			stm.executeUpdate("UPDATE questions SET yes_count = '"
					+ (count + 1) + "'  WHERE id = " + id);
			q.setYesCount(count + 1);
		}
		else if (type.equals("answer")) {
			Answer a = new Answer();
			ResultSet rs = stm
					.executeQuery("select yes_count from answers where id = "
							+ id);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			stm.executeUpdate("UPDATE answers SET yes_count = '"
					+ (count + 1) + "'  WHERE id = " + id);
			a.setYesCount(count + 1);
		}
	}
	/* inserting the rating type,type id and updating the unlike  count*/
	public void unlike(String type, int id) throws SQLException {
		Rating r = new Rating();
		String sql = "INSERT INTO " + ratingsTable
				+ " ( rtype,type_id,rating,created_at,updated_at) VALUES "
				+ "('" + type + "'," + id + ",0 ,NOW(),NOW() )";
		stm.executeUpdate(sql);
		r.setRtype(type);
		r.setRating(0);
		if (type.equals("question")) {
			Question q = new Question();
			ResultSet rs = stm
					.executeQuery("select no_count from questions where id = "
							+ id);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			stm.executeUpdate("UPDATE questions SET no_count = '"
					+ (count + 1) + "'  WHERE id = " + id);
			q.setYesCount(count + 1);
		}
		else if (type.equals("answer")) {
			Answer a = new Answer();
			ResultSet rs = stm
					.executeQuery("select no_count from answers where id = "
							+ id);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			stm.executeUpdate("UPDATE answers SET no_count = '"
					+ (count + 1) + "'  WHERE id = " + id);
			a.setYesCount(count + 1);
		}
	}
	/* inserting the rating type for inappropriate case for the question and updating the count*/
	public void Inappropriate(int questionId) throws SQLException {
		Rating r = new Rating();
		String sql = "INSERT INTO " + ratingsTable
				+ " ( rtype,type_id,rating,created_at,updated_at) VALUES "
				+ "('question'," + questionId + ",-1 ,NOW(),NOW())";
		stm.executeUpdate(sql);
		r.setRtype("question");
		r.setRating(-1);
		Question q = new Question();
		ResultSet rs = stm
				.executeQuery("select flag from questions where id = "
						+ questionId);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		stm.executeUpdate("UPDATE questions SET flag = " + (count + 1)
				+ " WHERE id = " + questionId);
		q.setFlag(count + 1);
	}

}

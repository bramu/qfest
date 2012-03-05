package com.tb.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tb.utils.DBConnector;

public class ViewDAO {
	private Statement stm;
	private String watchesTable="watches";
	/*constructor for connecting with the database*/
	public ViewDAO() {
		try {
			Connection con = DBConnector.getInstance().getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int viewedCount() throws SQLException {
		String sql = "SELECT COUNT(*) as viewedCount FROM "+ watchesTable +" WHERE watchable_type = 'questions'";
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
}

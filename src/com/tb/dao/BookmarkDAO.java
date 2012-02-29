package com.tb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tb.beans.Bookmark;
import com.tb.beans.Question;
import com.tb.utils.DBConnector;

public class BookmarkDAO {
	private String bookmarksTable="bookmarks";
	private Statement stm;
	public BookmarkDAO() {
		try {
			
			Connection con = DBConnector.getInstance().getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void bookmarkable(int questionId,int userId) throws SQLException{
		Bookmark b = new Bookmark();
		String sql = "INSERT INTO "+ bookmarksTable  +" (bookmarkable_type,bookmarkable_id,user_id) VALUES" +
				" ('question','" + questionId + "','"+userId +"' )";
		stm.executeUpdate(sql);
		b.setBookmarkableType("question");
		b.setBookmarkableId(questionId);
		b.setUserId(userId);
		Question q = new Question();
		ResultSet rs = stm.executeQuery("select bookmarks_count from questions where id = " + questionId);
		int count = 0;
		while(rs.next()){
			 count = rs.getInt(1);
		}
		 stm.executeUpdate("UPDATE questions SET bookmarks_count = '" + (count+1) + "'  WHERE id = " + questionId );
		 q.setBookmarksCount(count+1);
	}
	public List<Question> bookmarked(int userId , int pageNo) throws SQLException{
		List<Question> questions = new ArrayList<Question>();
		ResultSet rs = stm.executeQuery("SELECT id,title FROM questions WHERE id IN " +
				"( SELECT bookmarkable_id FROM bookmarks WHERE user_id = "+ userId +")" +
						" limit "+ (pageNo-1)*4 +",4");
		Question q= new Question();
		while(rs.next()){
			q.setId(rs.getInt(1));
			q.setTitle(rs.getString(2));
			questions.add(q);
		}
		return questions;
	}
}

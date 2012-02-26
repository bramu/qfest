package com.tb.beans;

public class Bookmark extends Base {
	
	private int score;
	private int userId;
	private int bookmarkableId;
	private String bookmarkableType;
	
	private User user;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookmarkableId() {
		return bookmarkableId;
	}

	public void setBookmarkableId(int bookmarkableId) {
		this.bookmarkableId = bookmarkableId;
	}

	public String getBookmarkableType() {
		return bookmarkableType;
	}

	public void setBookmarkableType(String bookmarkableType) {
		this.bookmarkableType = bookmarkableType;
	}
	
	

}

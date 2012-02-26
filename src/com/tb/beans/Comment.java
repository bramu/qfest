package com.tb.beans;

public class Comment extends Base {
	
	private String content;
	private int commentableId;
	private String commentableType;
	private int userId;
	private int yesCount;
	private int noCount;
	private int inappCount;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCommentableId() {
		return commentableId;
	}

	public void setCommentableId(int commentableId) {
		this.commentableId = commentableId;
	}

	public String getCommentableType() {
		return commentableType;
	}

	public void setCommentableType(String commentableType) {
		this.commentableType = commentableType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getYesCount() {
		return yesCount;
	}

	public void setYesCount(int yesCount) {
		this.yesCount = yesCount;
	}

	public int getNoCount() {
		return noCount;
	}

	public void setNoCount(int noCount) {
		this.noCount = noCount;
	}

	public int getInappCount() {
		return inappCount;
	}

	public void setInappCount(int inappCount) {
		this.inappCount = inappCount;
	}

	

	
}

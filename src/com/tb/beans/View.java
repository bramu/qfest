package com.tb.beans;

public class View extends Base {
	private String viewableType;
	private int viewableId;
	private int userId;
	
	private User user;

	public String getViewableType() {
		return viewableType;
	}

	public void setViewableType(String viewableType) {
		this.viewableType = viewableType;
	}

	public int getViewableId() {
		return viewableId;
	}

	public void setViewableId(int viewableId) {
		this.viewableId = viewableId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

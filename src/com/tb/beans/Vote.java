package com.tb.beans;

public class Vote {
	
	private int userId;
	private int vote;
	private String votableType;
	private int votableId;

	private User user;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getVotableType() {
		return votableType;
	}

	public void setVotableType(String votableType) {
		this.votableType = votableType;
	}

	public int getVotableId() {
		return votableId;
	}

	public void setVotableId(int votableId) {
		this.votableId = votableId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

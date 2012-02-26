package com.tb.beans;

public class Question extends Base {

	private String title;
	private String questionText;
	private String source;
	private int interviewId;
	private int userId;
	private int flag;

	private int yesCount;
	private int noCount;
	private int answersCount;
	private int commentsCount;
	private int viewsCount;
	private int inappCount;
	private int bookmarksCount;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
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

	public int getAnswersCount() {
		return answersCount;
	}

	public void setAnswersCount(int answersCount) {
		this.answersCount = answersCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public int getViewsCount() {
		return viewsCount;
	}

	public void setViewsCount(int viewsCount) {
		this.viewsCount = viewsCount;
	}

	public int getInappCount() {
		return inappCount;
	}

	public void setInappCount(int inappCount) {
		this.inappCount = inappCount;
	}

	public int getBookmarksCount() {
		return bookmarksCount;
	}

	public void setBookmarksCount(int bookmarksCount) {
		this.bookmarksCount = bookmarksCount;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFlag() {
		return flag;
	}

}
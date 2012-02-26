package com.tb.beans;

public class Company extends Base {
	
	private String name;
	private String description;
	private int interviewsCount;
	private int questionsCount;
	private String logo;
	private int industryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getInterviewsCount() {
		return interviewsCount;
	}

	public void setInterviewsCount(int interviewsCount) {
		this.interviewsCount = interviewsCount;
	}

	public int getQuestionsCount() {
		return questionsCount;
	}

	public void setQuestionsCount(int questionsCount) {
		this.questionsCount = questionsCount;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

}

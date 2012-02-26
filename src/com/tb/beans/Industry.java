package com.tb.beans;

public class Industry extends Base {
	private String description;
	private String name;
	private int companiesCount;

	public int getCompaniesCount() {
		return companiesCount;
	}

	public void setCompaniesCount(int companiesCount) {
		this.companiesCount = companiesCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

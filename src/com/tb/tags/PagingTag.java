package com.tb.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PagingTag extends SimpleTagSupport{
	private String type;
	
	private int pageNo;
	
	private int totalCount;
	
	public void setType(String type) {
		this.type = type;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void doTag() throws JspException, IOException {
		
		if(type != null){
			getJspContext().getOut().print("<div class='pagination' align='right'>");
			getJspContext().getOut().print("<ul>");
			if(pageNo >1){
				getJspContext().getOut().print
				("<li><a href='/qfest/questions?action=index&type='type'&page='pageNo-1''>previous</a></li>");
			}
			else {
				getJspContext().getOut().print("<li class='active'><a href='#'>previous</a></li>");
			}
			int totalPages = Math.abs(((Integer)totalCount / 20)) + 1;
			if (totalPages > pageNo) {
				getJspContext().getOut().print
				("<li><a href='/qfest/questions?action=index&type='type'&page='pageNo+1''>next</a></li>");
			}
			else{
				getJspContext().getOut().print("<li class='active'><a href='#'>next</a></li>");
			}
			getJspContext().getOut().print("</ul>");
			getJspContext().getOut().print("</div>");
		}
		else{
			getJspContext().getOut().print("<div class='pagination' align='right'>");
			getJspContext().getOut().print("<ul>");
			if(pageNo >1){
				getJspContext().getOut().print
				("<li><a href='/qfest/questions?action=index&page='pageNo-1''>previous</a></li>");
			}
			else {
				getJspContext().getOut().print("<li class='active'><a href='#'>previous</a></li>");
			}
			int totalPages = Math.abs(((Integer)totalCount / 20)) + 1;
			if (totalPages > pageNo) {
				getJspContext().getOut().print
				("<li><a href='/qfest/questions?action=index&page='pageNo+1''>next</a></li>");
			}
			else{
				getJspContext().getOut().print("<li class='active'><a href='#'>next</a></li>");
			}
			getJspContext().getOut().print("</ul>");
			getJspContext().getOut().print("</div>");
		}
		
	}
	
}



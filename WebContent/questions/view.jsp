<%@page import="com.tb.beans.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.tb.beans.Question"%>
<%@page import="com.tb.beans.Answer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="html/css/bootstrap.css" rel="stylesheet">
<link href="html/css/docs.css" rel="stylesheet">
<link href="html/css/bootstrap-responsive.css" rel="stylesheet">
<script type="text/javascript"
	src="http://platform.twitter.com/widgets.js"></script>
<script src="html/js/jquery.js"></script>
<script src="html/js/google-code-prettify/prettify.js"></script>
<script src="html/js/bootstrap-transition.js"></script>
<script src="html/js/bootstrap-alert.js"></script>

<script src="html/js/bootstrap-modal.js"></script>
<script src="html/js/bootstrap-dropdown.js"></script>
<script src="html/js/bootstrap-scrollspy.js"></script>
<script src="html/js/bootstrap-tab.js"></script>
<script src="html/js/bootstrap-tooltip.js"></script>
<script src="html/js/bootstrap-popover.js"></script>

<script src="html/js/bootstrap-button.js"></script>
<script src="html/js/bootstrap-collapse.js"></script>
<script src="html/js/bootstrap-carousel.js"></script>
<script src="html/js/bootstrap-typeahead.js"></script>
<script src="html/js/application.js"></script>
<title>Qfest view page</title>
</head>
<body>
	<div class="container">
		<div class="navbar">
			<div class="navbar-inner">
				<div style="width: auto;" class="container">
					<a data-target=".nav-collapse" data-toggle="collapse"
						class="btn btn-navbar"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <a href="#" class="brand">Quest fest</a>
					<div class="nav-collapse">
						<div class="nav-menu span-26 corners black-bg4 menu-shadow">
							<ul class="nav">
								<li><a href="#"></a></li>
								<li><a href="/qfest/questions">Questions</a></li>
								<li><a href="#">Interviews</a></li>
								<li><a href="#">Companies</a></li>
								<li><a href="#">Skills</a></li>
								<li><a href="#">Tags</a></li>
								<li><a href="#">Users</a></li>

							</ul>
						</div>
						<form action="" class="navbar-search pull-right">
							<input type="text" class="span3">
							<button type="submit" class="searchbutton" style="margin: 0;">Search</button>

						</form>

					</div>
					<!-- /.nav-collapse -->
				</div>
			</div>
			<!-- /navbar-inner -->
		</div>
		<div class="row">
			<div class="span9">
				<div>
					<h3>
						<%
							out.print(((Question) request.getAttribute("question")).getTitle());
						%>
					</h3>
				</div>
				<div class="page-header"></div>
				<div class="row">
					<div class="span6">
						<div>
							<p>
								<%
									out.print(((Question) request.getAttribute("question"))
											.getQuestionText());
								%>
							</p>
							
						</div>
						<div>
							<a class="label label-info" href="#">java</a> <a
								class="label label-info" href="#">arrays</a> <a
								class="label label-info" href="#">servlets</a>
						</div>

					</div>
					
				</div>
				<br />
				<div style="margin-bottom: 9px;" class="tabbable">
					<ul class="nav nav-tabs">
						<li class=""><a data-toggle="tab" href="#answers_tab">answers</a></li>
						<li class=""><a data-toggle="tab" href="#comments_tab">comments</a></li>

					</ul>
					<div class="tab-content">
						<div id="answers_tab" class="tab-pane">
							<p>
								<%
								
								List<Answer> answers = (List<Answer>) request
										.getAttribute("answers");
							if(answers.isEmpty()){
								out.println("No answers to display");
							}else {
								for (int i = 0; i < answers.size(); i++) {
									out.println(answers.get(i).getAnswerText());%>
									<div>
									<a id='up-<%=answers.get(i).getId()%>'
										href="/qfest/ratings?action=like&type=answer&answerId=<%=answers.get(i).getId()%>"><img
										src="html/images/link_like2.gif"> </a> <a
										id='down-<%=answers.get(i).getId()%>'
										href="/qfest/ratings?action=unlike&type=answer&answerId=<%=answers.get(i).getId()%>"><img
										src="html/images/link_dislike2.gif"> </a>
							
								</div>  
							<%    
								}
							}%>
							
							</p>
						</div>
						<div id="comments_tab" class="tab-pane">
							<p>
							   <%
								List<Comment> comments = (List<Comment>) request
										.getAttribute("comments");
							   if(comments.size()== 0){
								   out.println("No comments to display for this question");
							   }else{
								for (int i = 0; i < comments.size(); i++) {
									out.println(comments.get(i).getContent());
								}
							   }
							%>
							</p>
						</div>

					</div>
				</div>

			</div>
			<div class="span3">
				<div class="form-actions">
					<h3>Share or ask the questions you already faced in interviews
						and get answers and comments from thousands of people around
						you...</h3>

					<div align="middle">
						<a href="#" class="label label-info">Add Question</a>
					</div>
				</div>
				<div class="page-header"></div>
				<div align="middle">
					<h1><%=(Integer) request.getAttribute("totalCount")%></h1>
				</div>
				<div align="middle">
					<h1>Questions</h1>
				</div>
				<div class="page-header"></div>
				<div>
					<h3>popular tags</h3>
				</div>

				<div>
					<a class="btn js-btn" href="#">arrays</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">c</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">c++</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">java</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">linked list</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">pointers</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">servlets</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">jdbc</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">jsp</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">generics</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">collections</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">inner classes</a>
				</div>
				<div>
					<a class="btn js-btn" href="#">hashmap</a>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container" style="width: 100%;">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <a class="brand" href="#"> w5db.com.All rights reserved </a>

				</div>
			</div>
			<!-- /navbar-inner -->
		</div>
	</div>
</body>
</html>
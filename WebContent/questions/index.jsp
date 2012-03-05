<%@page import="java.util.List"%>
<%@page import="com.tb.beans.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="html/css/bootstrap.css" rel="stylesheet">
<link href="html/css/docs.css" rel="stylesheet">
<link href="html/css/bootstrap-responsive.css" rel="stylesheet">
<link href="html/css/ownstyle.css" rel="stylesheet">

<script src="html/js/jquery.js"></script>

<script type="text/javascript"
	src="html/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript"
	src="html/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css"
	href="html/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

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

<script type="text/javascript">
	$(document).ready(function() {
		$("a[id^=write-answer-]").fancybox();
		$("a[id^=write-comment-]").fancybox();
		$("#login").fancybox();
		$("#register").fancybox();
		$("#dologin").fancybox();
		$("#alogin").fancybox();
		$("#clogin").fancybox();
		$("#blogin").fancybox();

	});
</script>
<title>Qfest index page</title>
</head>
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
			<div class="row">
				<div class="span5">

					<%
						if ((String) request.getAttribute("type") != null) {
					%>
					<h2><%=(String) request.getAttribute("type")%></h2>
					<%
						} else {
					%>
					<h2>Recent Questions</h2>
					<%
						}
					%>
				</div>
				<div class="span4">
					<ul class="nav nav-pills" align="right">
						<li><a href="/qfest/questions?action=index&type=recent">recent</a></li>
						<li><a href="/qfest/questions?action=index&type=rated">rated</a></li>
						<li><a href="/qfest/questions?action=index&type=viewed">viewed</a></li>
						<li><a
							href="/qfest/questions?action=index&type=unanswered
								">unanswered</a></li>
						<%
							if (session.getAttribute("userId") == null) {
						%>
						<li><a href="#">bookmark</a></li>
						<%
							} else {
						%>
						<li><a href="/qfest/questions?action=index&type=bookmarked">bookmark</a></li>
						<%
							}
						%>


					</ul>
				</div>

			</div>
			<div class="page-header"></div>

			<%
				List<Question> questions = (List<Question>) (request
						.getAttribute("questions"));

				for (int i = 0; i < questions.size(); i++) {
			%>

			<div class="row">
				<div class="span6">
					<div>
						<h3>
							<%
								out.println(questions.get(i).getTitle());
							%>
						</h3>

					</div>

					<div>

						<a class="label label-info" href="#">java</a> <a
							class="label label-info" href="#">arrays</a> <a
							class="label label-info" href="#">servlets</a>
					</div>
				</div>
				<div class="span3">
					<a
						href="/qfest/questions?action=view&questionId=<%=questions.get(i).getId()%>">ShowAnswer</a>

					<div>
						<a id='up-<%=questions.get(i).getId()%>'
							href="/qfest/ratings?action=like&type=question&questionId=<%=questions.get(i).getId()%>"><img
							src="html/images/link_like2.gif"> </a> <a
							id='down-<%=questions.get(i).getId()%>'
							href="/qfest/ratings?action=unlike&type=question&questionId=<%=questions.get(i).getId()%>"><img
							src="html/images/link_dislike2.gif"> </a>
					</div>

					<div>
						<a
							href="/qfest/ratings?action=inappropriate&id=<%=questions.get(i).getId()%>">flag
							as inappropriate</a>
					</div>
				</div>
			</div>

			<div>
				<%
					if (session.getAttribute("userId") == null) {
				%>
				<ul class="nav nav-pills" align="right">
					<li><a href="/qfest/users?action=login" id="alogin">WriteAnswer</a></li>
					<li><a href="/qfest/users?action=login" id="blogin">WriteComment</a></li>
					<li><a href="/qfest/users?action=login" id="clogin">Bookmark</a></li>
				</ul>



				<%
					} else {
				%>
				<ul class="nav nav-pills" align="right">
					<li><a
						href="/qfest/answers?action=writeAnswer&questionId=<%=questions.get(i).getId()%>"
						id='write-answer-<%=questions.get(i).getId()%>'>WriteAnswer</a></li>
					<li><a
						href="/qfest/questions?action=writeComment&questionId=<%=questions.get(i).getId()%>"
						id='write-comment-<%=questions.get(i).getId()%>'>WriteComment</a></li>
					<li><a
						href="/qfest/questions?action=index&type=bookmarkable&id=<%=questions.get(i).getId()%>">Bookmark</a></li>
				</ul>
				<%
					}
				%>

			</div>


			<div class="page-header"></div>

			<%
				}
			%>


			<%
				if ((String) request.getAttribute("type") != null) {
			%>
			<div class="pagination">

				<ul>
					<%
						if ((Integer) request.getAttribute("pageNo") > 1) {
					%>
					<li><a
						href="/qfest/questions?action=index&type=<%=(String) request.getAttribute("type")%>&page=1">first</a></li>
					<%
						} else {
					%>
					<li class="active"><a href="#">first</a></li>
					<%
						}

							for (int i = 1; i <= Math.abs(((Integer) request
									.getAttribute("totalCount") / 4)) + 1; i++) {
								if ((Integer) request.getAttribute("pageNo") == i) {
					%>
					<li class="active"><a href="#"><%=i%></a></li>
					<%
						} else {
					%>
					<li><a
						href="/qfest/questions?action=index&type=<%=(String) request.getAttribute("type")%>&page=<%=i%>"><%=i%></a></li>
					<%
						}
							}
					%>
					<%
						int totalPages = Math.abs(((Integer) request
									.getAttribute("totalCount") / 4)) + 1;
							if (totalPages > (Integer) request.getAttribute("pageNo")) {
					%>
					<li><a
						href="/qfest/questions?action=index&type=<%=(String) request.getAttribute("type")%>&page=<%=totalPages%>">last</a></li>
					<%
						} else {
					%>
					<li class="active"><a href="#">last</a></li>
					<%
						}
					%>

				</ul>
			</div>

			<%
				} else {
			%>
			<div class="pagination">

				<ul>
					<%
						if ((Integer) request.getAttribute("pageNo") > 1) {
					%>
					<li><a href="/qfest/questions?action=index&page=1">first</a></li>
					<%
						} else {
					%>
					<li class="active"><a href="#">first</a></li>
					<%
						}
					%>

					<%
						for (int i = 1; i <= Math.abs(((Integer) request
									.getAttribute("totalCount") / 4)) + 1; i++) {
								if ((Integer) request.getAttribute("pageNo") == i) {
					%>
					<li class="active"><a href="#"><%=i%></a></li>
					<%
						} else {
					%>
					<li><a href="/qfest/questions?action=index&page=<%=i%>"><%=i%></a></li>
					<%
						}
							}
					%>
					<%
						int totalPages = Math.abs(((Integer) request
									.getAttribute("totalCount") / 4)) + 1;
							if (totalPages > (Integer) request.getAttribute("pageNo")) {
					%>
					<li><a
						href="/qfest/questions?action=index&page=<%=totalPages%>">last</a></li>
					<%
						} else {
					%>
					<li class="active"><a href="#">last</a></li>
					<%
						}
					%>

				</ul>
			</div>
			<%
				}
			%>
		</div>

		<div class="span3">
			<ul class="nav nav-pills">
				<%
					if (session.getAttribute("name") != null) {
				%>
				<li><a href="#">Hi,<%=session.getAttribute("name")%></a></li>
				<li><a href="/qfest/users?action=logout">logout</a></li>
				<%
					} else {
				%>
				<li><a href="#">Hi,guest</a></li>
				<li><a id='register' href="/qfest/users?action=register">Register
				</a></li>
				<li><a id='dologin' href="/qfest/users?action=login">Login
				</a></li>
				<%
					}
				%>
			</ul>
			<div class="welcome">
				<h2>Share or ask the questions you already faced in interviews
					and get answers and comments from thousands of people around you..</h2>
				<div class="addnew">
					<%
						if (session.getAttribute("userId") == null) {
					%>
					<a href="/qfest/users?action=login" class="label label-info">Add
						Question</a>

					<%
						} else {
					%>
					<a
						href="/qfest/questions?action=add&userId=<%=session.getAttribute("userId")%>"
						class="label label-info">Add Question</a>

					<%
						}
					%>
				</div>
			</div>

			<div class="page-header"></div>
			<div align="middle">
				<h1>
					<%
						out.print(request.getAttribute("totalCount"));
					%>
				</h1>
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
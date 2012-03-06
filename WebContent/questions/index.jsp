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
				</a> <a href="#" class="brand">Qfest</a>
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
			<div class="pagination" align="right">

				<ul>
					<%
						if ((Integer) request.getAttribute("pageNo") > 1) {
					%>
					<li><a
						href="/qfest/questions?action=index&type=<%=(String) request.getAttribute("type")%>
						&page=<%=(Integer) request.getAttribute("pageNo")-1%>">previous</a></li>
					<%
						} else {
					%>
					<li class="active"><a href="#">previous</a></li>
					<%
						}
						int totalPages = Math.abs(((Integer) request
									.getAttribute("totalCount") / 20)) + 1;
							if (totalPages > (Integer) request.getAttribute("pageNo")) {
					%>
					<li><a
						href="/qfest/questions?action=index&type=<%=(String) request.getAttribute("type")%>
						&page=<%=(Integer) request.getAttribute("pageNo")+1%>">next</a></li>
					<%
						} else {
					%>
					<li class="active"><a href="#">next</a></li>
					<%
						}
					%>

				</ul>
			</div>

			<%
				} else {
			%>
			<div class="pagination" align="right">

				<ul>
					<%
						if ((Integer) request.getAttribute("pageNo") > 1) {
					%>
					<li><a href="/qfest/questions?action=index&page=<%=(Integer) request.getAttribute("pageNo")-1 %>">previous</a></li>
					<%
						} else {
					%>
					<li class="active"><a href="#">previous</a></li>
					<%
						}
					%>
					<%
						int totalPages = Math.abs(((Integer) request
									.getAttribute("totalCount") / 20 )) + 1;
							if (totalPages > (Integer) request.getAttribute("pageNo")) {
					%>
					<li><a
						href="/qfest/questions?action=index&page=<%=(Integer) request.getAttribute("pageNo")+1%>">next</a></li>
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
			<div class="form-actions">
				<h3>Share or ask the questions you already faced in interviews
					and get answers and comments from thousands of people around you...</h3>

				<div align="middle">
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

			<div style="padding-top: 20px;">
				<code>
					<a href="#">Arrays</a>
				</code>
				x67

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">oops</a>
				</code>
				x54

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">Servlets</a>
				</code>
				x40

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">linkedlist</a>
				</code>
				x30

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">pointers</a>
				</code>
				x20

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">Structs</a>
				</code>
				x10

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">coolections</a>
				</code>
				x21

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">generics</a>
				</code>
				x32

			</div>
			<div style="padding-top: 20px;">
				<code>
					<a href="#">java</a>
				</code>
				x63

			</div>


		</div>

	</div>

	<footer class="footer">
	<p class="pull-right">
		<a href="#">Back to top</a>
	</p>
	<p>
		Designed and built with all the love in the world <a target="_blank"
			href="http://twitter.com/twitter">@twitter</a> by <a target="_blank"
			href="http://twitter.com/mdo">@mdo</a> and <a target="_blank"
			href="http://twitter.com/fat">@fat</a>.
	</p>
	<p>
		Code licensed under the <a target="_blank"
			href="http://www.apache.org/licenses/LICENSE-2.0">Apache License
			v2.0</a>. Documentation licensed under <a
			href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.
	</p>
	<p>
		Icons from <a href="http://glyphicons.com">Glyphicons Free</a>,
		licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC
			BY 3.0</a>.
	</p>
	</footer>

</div>

</body>

</html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<jsp:include page="/layout/header.jsp"></jsp:include>
		<div class="row">
			<div class="span9">
				<div>
					<h3>${question.title}</h3>
				</div>
				<div class="page-header"></div>
				<div class="row">
					<div class="span6">
						<div>
							<p>${question.questionText}</p>
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
									<c:choose>
										<c:when test="${answers == null }">
											<p>No answers to display</p>
										</c:when>
										<c:otherwise>
										<c:forEach var="answer" items="${answers}">
											${answer.answerText}
											<div>
												<a id='up-${answer.id}'
													href="/qfest/ratings?action=like&type=answer&answerId=${answer.id}"><img
													src="html/images/link_like2.gif"> </a> <a
													id='down-${answer.id}'
													href="/qfest/ratings?action=unlike&type=answer&answerId=${answer.id}"><img
													src="html/images/link_dislike2.gif"> </a>
											</div>
										</c:forEach>
										</c:otherwise>
									</c:choose>
							</p>
						</div>
						<div id="comments_tab" class="tab-pane">
							<p>
									<c:choose>
										<c:when test="${comments == null }">
							   				<p>No comments to display for this question</p>
							   			</c:when>
										<c:otherwise>
										 <c:forEach var="comment" items="${comments}">
							   				${comment.content}
							   				<br />
							   				</c:forEach>
										</c:otherwise>
									</c:choose>
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
						<c:choose>
							<c:when test="${userId == null }">
								<a href="/qfest/users?action=login" class="label label-info">Add
									Question</a>

							</c:when>
							<c:otherwise>
								<a href="/qfest/questions?action=add&userId= ${userId}"
									class="label label-info">Add Question</a>
							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
				<div class="page-header"></div>
				<div align="middle">
					<h1>${totalCount}</h1>
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
						<a href="#">collections</a>
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

		<jsp:include page="/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>
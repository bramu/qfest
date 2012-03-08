<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<script type="text/javascript"
	src="html/fancybox/jquery.easing-1.3.pack.js"></script>

<script type="text/javascript"
	src="html/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript"
	src="html/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript"
	src="html/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css"
	href="html/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<script src="html/js/jquery.js"></script>
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
	<jsp:include page="/layout/header.jsp"></jsp:include>

	<div class="row">
		<div class="span9">
			<div class="row">
				<div class="span5">
					<c:choose>
						<c:when test="${type != null}">
							<h2>${type }</h2>
						</c:when>
						<c:otherwise>
							<h2>Recent Questions</h2>
						</c:otherwise>
					</c:choose>



				</div>
				<div class="span4">
					<ul class="nav nav-pills" align="right">
						<li><a href="/qfest/questions?action=index&type=recent">recent</a></li>
						<li><a href="/qfest/questions?action=index&type=rated">rated</a></li>
						<li><a href="/qfest/questions?action=index&type=viewed">viewed</a></li>
						<li><a
							href="/qfest/questions?action=index&type=unanswered
								">unanswered</a></li>
						<c:choose>
							<c:when test="${userId == null }">
								<li><a href="#">bookmark</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/qfest/questions?action=index&type=bookmarked">bookmark</a></li>
							</c:otherwise>
						</c:choose>


					</ul>
				</div>

			</div>
			<div class="page-header"></div>

			<c:forEach var="question" items="${questions}">
				<div class="row">
					<div class="span6">
						<div>
							<h3>${question.title}</h3>

						</div>

						<div>

							<a class="label label-info" href="#">java</a> <a
								class="label label-info" href="#">arrays</a> <a
								class="label label-info" href="#">servlets</a>
						</div>
					</div>
					<div class="span3">

						<a href="/qfest/questions?action=view&questionId=${question.id }">ShowAnswer</a>

						<div>
							<a id='up-${question.id }'
								href="/qfest/ratings?action=like&type=question&questionId=${question.id }"><img
								src="html/images/link_like2.gif"> </a> <a
								id='down-${question.id }'
								href="/qfest/ratings?action=unlike&type=question&questionId=${question.id }"><img
								src="html/images/link_dislike2.gif"> </a>
						</div>

						<div>
							<a href="/qfest/ratings?action=inappropriate&id=${question.id }">flag
								as inappropriate</a>
						</div>
					</div>
				</div>

				<div>
					<c:choose>
						<c:when test="${userId == null }">
							<ul class="nav nav-pills" align="right">
								<li><a href="/qfest/users?action=login" id="alogin">WriteAnswer</a></li>
								<li><a href="/qfest/users?action=login" id="blogin">WriteComment</a></li>
								<li><a href="/qfest/users?action=login" id="clogin">Bookmark</a></li>
							</ul>

						</c:when>
						<c:otherwise>
							<ul class="nav nav-pills" align="right">
								<li><a
									href="/qfest/answers?action=writeAnswer&questionId=${question.id }"
									id='write-answer-${question.id }'>WriteAnswer</a></li>
								<li><a
									href="/qfest/questions?action=writeComment&questionId=${question.id }"
									id='write-comment-${question.id }'>WriteComment</a></li>
								<li><a
									href="/qfest/questions?action=index&type=bookmarkable&id=${question.id }">Bookmark</a></li>
							</ul>
						</c:otherwise>

					</c:choose>


				</div>


				<div class="page-header"></div>

			</c:forEach>
			<c:choose>
				<c:when test="${type != null }">
					<div class="pagination" align="right">

						<ul>
							<c:choose>
								<c:when test="${pageNo > 1 }">
									<li><a
										href="/qfest/questions?action=index&type= ${type }&page= ${pageNo-1}">previous</a></li>
								</c:when>
								<c:otherwise>
									<li class="active"><a href="#">previous</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test=" ${totalPages > pageNo }">
									<li><a
										href="/qfest/questions?action=index&type= ${type }&page=${pageNo+1}">next</a></li>
								</c:when>
								<c:otherwise>
									<li class="active"><a href="#">next</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<div class="pagination" align="right">

						<ul>
							<c:choose>
								<c:when test="${pageNo > 1 }">
									<li><a
										href="/qfest/questions?action=index&page= ${pageNo-1}">previous</a></li>
								</c:when>
								<c:otherwise>
									<li class="active"><a href="#">previous</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test=" ${pageNo < totalPages} ">
									<li><a
										href="/qfest/questions?action=index&page=${pageNo+1}">next</a></li>
								</c:when>
								<c:otherwise>
									<li class="active"><a href="#">next</a></li>
								</c:otherwise>
							</c:choose>

						</ul>
					</div>
				</c:otherwise>
			</c:choose>


		</div>

		<div class="span3">
			<ul class="nav nav-pills">
				<c:choose>
					<c:when test="${name != null }">
						<li><a href="#">Hi,${name}</a></li>
						<li><a href="/qfest/users?action=logout">logout</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="#">Hi,guest</a></li>
						<li><a id='register' href="/qfest/users?action=register">Register
						</a></li>
						<li><a id='dologin' href="/qfest/users?action=login">Login
						</a></li>
					</c:otherwise>
				</c:choose>
				
			</ul>
			<div class="form-actions">
				<h3>Share or ask the questions you already faced in interviews
					and get answers and comments from thousands of people around you...</h3>

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

	<jsp:include page="/layout/footer.jsp"></jsp:include>

</div>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="html/css/bootstrap.css" rel="stylesheet">
<link href="html/css/docs.css" rel="stylesheet">
<link href="html/css/bootstrap-responsive.css" rel="stylesheet">
<script type="text/javascript"src="http://platform.twitter.com/widgets.js"></script>
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
<title>user login</title>
</head>
<body>
	<div class="container">
		<jsp:include page="/layout/header.jsp"></jsp:include>
	
		<div class="row">
			<div class="span6">

				<form class="form-horizontal" method="post" action="/qfest/questions">
					<input type="hidden" name="action" value="create">
					<fieldset>
						<legend>Register</legend>
						<div class="control-group">
							<%
								if (request.getParameter("msg") != null) {
							%>
							<h3 align="middle"> ${msg} </h3>
							<%
								}
							%>
							<label for="name" class="control-label">Name</label>
							<div class="controls">
								<input type="text" name="name" class="input-xlarge">

							</div>
						</div>
						<div class="control-group">
							<label for="email" class="control-label">Email</label>
							<div class="controls">
								<input type="text" name="email" class="input-xlarge">

							</div>
						</div>
						<div class="control-group">
							<label for="pw" class="control-label">Password</label>
							<div class="controls">
								<input type="password" name="pw" class="input-xlarge">

							</div>
						</div>
						<div class="control-group">
							<label for="c_pw" class="control-label">Confirm Password</label>
							<div class="controls">
								<input type="password" name="c_pw" class="input-xlarge">

							</div>
						</div>
						<div align="middle">
							<button class="btn btn-primary" type="submit">Register</button>
							<form action="/qfest/users?action=reset">
								<button class="btn" type="submit">Reset</button>
							</form>
						</div>

					</fieldset>
				</form>

			</div>
			<div class="span6">
				<form class="form-horizontal" method="post" action="/qfest/users">
					<input type="hidden" name="action" value="logincheck">
					<fieldset>
						<legend>Sign In</legend>

						<div class="control-group">
							<%
								if (request.getParameter("msg1") != null) {
							%>
							<h3 align="middle"> ${msg1}</h3>
							<%
								}
							%>
							<label for="emailc" class="control-label">Email</label>
							<div class="controls">
								<input type="text" name="emailc" class="input-xlarge">

							</div>
						</div>
						<div class="control-group">
							<label for="pwc" class="control-label">Password</label>
							<div class="controls">
								<input type="password" name="pwc" class="input-xlarge">

							</div>
						</div>

						<div align="middle">
							<button class="btn btn-primary" type="submit">Login</button>
							<button class="btn">Forget Password</button>
						</div>
							
					</fieldset>
				</form>
			</div>
		</div>
	

	<div class="navbar">
		<div class="navbar-inner">
			<div class="container" style="width: 100%;">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#"> Qfest.com.All rights reserved </a>

			</div>
		</div>
		<!-- /navbar-inner -->
	</div>
</div>
</body>
</html>
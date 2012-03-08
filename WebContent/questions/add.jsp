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
<script src="js/jquery.js"></script>
<script src="js/google-code-prettify/prettify.js"></script>
<script src="js/bootstrap-transition.js"></script>
<script src="js/bootstrap-alert.js"></script>

<script src="js/bootstrap-modal.js"></script>
<script src="js/bootstrap-dropdown.js"></script>
<script src="js/bootstrap-scrollspy.js"></script>
<script src="js/bootstrap-tab.js"></script>
<script src="js/bootstrap-tooltip.js"></script>
<script src="js/bootstrap-popover.js"></script>

<script src="js/bootstrap-button.js"></script>
<script src="js/bootstrap-collapse.js"></script>
<script src="js/bootstrap-carousel.js"></script>
<script src="js/bootstrap-typeahead.js"></script>
<script src="js/application.js"></script>
<title>New Question Html</title>
</head>
<body>
	<div class="container">
		<jsp:include page="/layout/header.jsp"></jsp:include>
		<div class="row">
			<div class="span8">
				<form  method = "post" class="form-horizontal" 
				action="/qfest/questions ">
			<input type="hidden" name="action" value="create">
				<input type ="hidden" name="userId" value="${userId}"> 
					<fieldset>
						<legend>Add Question</legend>
						<div class="control-group">
							<label for="title" class="control-label">Title</label>
							<div class="controls">
								<input type="text" id="input01" name="title" class="input-xlarge">
								
							</div>
						</div>
						<div class="control-group">
							<label for="questionText" class="control-label">Question Text</label>
							<div class="controls">
								<textarea rows="3" id="textarea" name="questionText" class="input-xlarge"></textarea>
							</div>
						</div>
						
				<!--  	<div class="control-group" style=" width: 500px ">
							<label for="select01" class="control-label">Tags</label>
							<div class="controls">
								<select id="select01">
									<option>select Tags</option>
									<option>c</option>
									<option>Java</option>
									<option>servlets</option>
									<option>linkedlist</option>
								</select>
							</div>
						</div>       -->	
						
						
						<div class="control-group">
							<label for="answerText" class="control-label">If u have answer write here</label>
							<div class="controls">
								<textarea rows="3" id="textarea" name="answerText" class="input-xlarge"></textarea>
							</div>
						</div>
						<div align="middle">
							<button class="btn btn-primary" type="submit">AddQuestion</button>
							
						</div>
					</fieldset>
				</form>
				
			</div>
		</div>
	</div>
	<jsp:include page="/layout/footer.jsp"></jsp:include>
 </body>
</html>
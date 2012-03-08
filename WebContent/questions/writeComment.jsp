<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/docs.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
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

<title>Insert title here</title>
</head>
<body>
	<div id="fancybox-content"
		style="border-width: 10px; width: 480px; height: auto;">

		<form method="post" class="form-horizontal" action="/qfest/questions">
		<input type="hidden" name="action" value="submitComment">
		<input type="hidden" name="questionId" value="${questionId}"> 
			<legend>Write Comment</legend>
			<div class="control-group">
				<label for="comment" class="control-label">Textarea</label>
				<div class="controls">
					<textarea rows="10" id="textarea" name="comment" class="input-xlarge"></textarea>
				</div>
			</div>
			<div align="middle">
				<button class="label label-info" type="submit">Submit</button>
			</div>
		</form>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/docs.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">


<title>Insert title here</title>
</head>
<body>
	<div id="fancybox-content"
		style="border-width: 10px; width: 480px; height: auto;">

		<form method="post" class="form-horizontal"
			action="/qfest/answers">
			<input type="hidden" name="action" value="submitAnswer"> <input
				type="hidden" name="questionId"
				value="${questionId}"> 
			<legend>Write Answer</legend>
			<div class="control-group">
				<label for="answer" class="control-label">Textarea</label>
				<div class="controls">
					<textarea rows="10" id="textarea" name="answer"
						class="input-xlarge"></textarea>
				</div>
			</div>
			<div align="middle">
				<button class="label label-info" type="submit">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>
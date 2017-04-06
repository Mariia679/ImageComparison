<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Image Comparison</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<style>
.marg {
	margin: 30px 0px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row ">
			<h1 class="text-center marg">Image Comparison</h1>
			<h3 class="text-center marg">Please download the image to find
				difference between two pictures</h3>
			<div class="col-sm-5 col-xs-12 marg">
				<form:form class="form-horizontal" action="/img" method="POST"
					modelAttribute="image" enctype="multipart/form-data">
					<div class="form-group">
						<label for="file" class="col-sm-4 control-label">Image
							First</label>
						<div class="col-sm-8">
							<input name="fileFirst" type="file" id="file">
						</div>
					</div>
					<div class="form-group">
						<label for="file" class="col-sm-4 control-label">Image
							Second</label>
						<div class="col-sm-8">
							<input name="fileSecond" type="file" id="file">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Find
								differences</button>
							<a class="btn btn-primary" href="/cancel">Cancel</a>
						</div>
					</div>
				</form:form>
			</div>
			<div class="col-sm-7 col-xs-12">

				<img src="/images/new_image/1.jpg" width="750"
					alt="Here will be a photo">

			</div>
		</div>
	</div>
</body>
</html>
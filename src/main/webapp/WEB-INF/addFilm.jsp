<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" href="main.css">

<title>Film Query</title>
</head>

<body>

	<div class="container-fluid">

		<h1>Film Query</h1>



		<div class="outline">
			<div class="row">
				<div class="col-sm-8">
					<form method=GET action="searchByIdInput.do">
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-text">Search by Film ID #</div>
							<input type="text" class="form-control" id="filmId" name="filmId"
								required>
							<button type="submit" class="btn btn-primary">Search</button>
						</div>
					</form>
				</div>
				<div class="col-sm-4">
					<form action="add.do">
						<button type="submit" class="btn btn-primary addNewButton">Add
							New Film</button>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-8">
					<form method=GET action="searchByKeywordInput.do">
						<div class="input-group input-group-sm mb-3">
							<div class="input-group-text">Search by Keyword</div>
							<input type="text" class="form-control" id="keyword"
								name="keyword" value="">
							<button type="submit" class="btn btn-primary">Search</button>
						</div>
					</form>
				</div>
			</div>
		</div>



<p>Put form here</p>






	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
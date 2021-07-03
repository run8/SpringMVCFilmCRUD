<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="col-sm-4">
			<div class="form">
				<form method=POST action="id.do">
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-text" id="film_id_search">Search by
							Film ID #</div>
						<input type="text" class="form-control" id="film_id" value="">
						<button type="submit" class="btn btn-primary">Search</button>
					</div>
				</form>

				<form method=POST action="keyword.do">
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-text" id="keyword_search">Search by
							Keyword</div>
						<input type="text" class="form-control">
						<button type="submit" class="btn btn-primary">Search</button>
					</div>
				</form>
			</div>
		</div>


	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
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
							<input type="number" class="form-control" id="filmId" name="filmId"
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
		</div><br>



		<h1>Add Film</h1>

		<div class="row">
			<div class="col-sm-8">
				<form method=POST action="addFilm.do">
				
						<label for="title">Film Title:</label><br> 
						<input type="text" id="title" name="title" value=""><br>

						<label for="description">Film Description:</label><br> 
						<textarea id="description" name="description" maxlength="200" name="filmDescription" rows="4" cols="50"></textarea><br>

						<label for="rentalRate">Rental Rate ($):</label><br> 
						<input type="text" id="rentalRate" name="rentalRate" pattern="[0-9.]*" required><br>

						<label for="filmLength">Film Length (minutes):</label><br> 
						<input type="text" id="filmLength" name="filmLength" pattern="[0-9]*" required><br>

						<label for="replacementCost">Replacement Cost ($):</label><br> 
						<input type="text" id="replacementCost" name="replacementCost" pattern="[0-9.]*" required><br>

						Rental Duration (days): <select name="rentalDuration"  >
						<option value="3" selected>3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
					</select><br> 

					Rating: <select name="rating"  >
						<option value="G" selected>G</option>
						<option value="PG">PG</option>
						<option value="PG13">PG13</option>
						<option value="R">R</option>
						<option value="NC17">NC17</option>
					</select><br> 
					
					Release Year: <select name="releaseYear">
					<c:forEach begin="0" end="121" varStatus="loop">
						<option value="${2021-loop.current}">${2021-loop.current}</option>
						</c:forEach></select><br>
<!-- 						<option value="2021" selected>2021</option> -->
<!-- 						<option value="2020">2020</option> -->
<!-- 						<option value="2019">2019</option> -->
<!-- 						<option value="2018">2018</option> -->
<!-- 						<option value="2017">2017</option> -->
<!-- 						<option value="2016">2016</option> -->
<!-- 						<option value="2015">2015</option> -->
<!-- 						<option value="2014">2014</option> -->
<!-- 						<option value="2013">2013</option> -->
<!-- 						<option value="2012">2012</option> -->
<!-- 						<option value="2011">2011</option> -->
<!-- 						<option value="2010">2010</option> -->
<!-- 						<option value="2009">2009</option> -->
<!-- 						<option value="2008">2008</option> -->
<!-- 						<option value="2007">2007</option> -->
<!-- 						<option value="2006">2006</option> -->
<!-- 						<option value="2005">2005</option> -->
<!-- 						<option value="2004">2004</option> -->
<!-- 						<option value="2003">2003</option> -->
<!-- 						<option value="2002">2002</option> -->
<!-- 						<option value="2001">2001</option> -->
<!-- 						<option value="2000">2000</option> -->
<!-- 						<option value="1999">1999</option> -->
<!-- 						<option value="1998">1998</option> -->
<!-- 						<option value="1997">1997</option> -->
<!-- 						<option value="1996">1996</option> -->
<!-- 						<option value="1995">1995</option> -->
<!-- 						<option value="1994">1994</option> -->
<!-- 						<option value="1993">1993</option> -->
<!-- 						<option value="1992">1992</option> -->
<!-- 						<option value="1991">1991</option> -->
<!-- 						<option value="1990">1990</option> -->
<!-- 						<option value="1989">1989</option> -->
<!-- 						<option value="1987">1987</option> -->
<!-- 						<option value="1996">1996</option> -->
<!-- 						<option value="1995">1995</option> -->
<!-- 						<option value="1994">1994</option> -->
<!-- 						<option value="1993">1993</option> -->
<!-- 						<option value="1992">1992</option> -->
<!-- 						<option value="1991">1991</option> -->
<!-- 						<option value="1990">1990</option> -->
<!-- 						<option value="1989">1989</option> -->
<!-- 						<option value="1988">1988</option> -->
<!-- 						<option value="1987">1987</option> -->
<!-- 						<option value="1986">1986</option> -->
<!-- 						<option value="1985">1985</option> -->
<!-- 						<option value="1984">1984</option> -->
<!-- 						<option value="1983">1983</option> -->
<!-- 						<option value="1982">1982</option> -->
<!-- 						<option value="1981">1981</option> -->
<!-- 						<option value="1980">1980</option> -->
<!-- 						<option value="1979">1979</option> -->
<!-- 						<option value="1978">1978</option> -->
<!-- 						<option value="1977">1977</option> -->
<!-- 						<option value="1976">1976</option> -->
<!-- 						<option value="1975">1975</option> -->
<!-- 						<option value="1974">1974</option> -->
<!-- 						<option value="1973">1973</option> -->
<!-- 						<option value="1972">1972</option> -->
<!-- 						<option value="1971">1971</option> -->
<!-- 						<option value="1970">1970</option> -->
<!-- 					</select><br>  -->
					
					Special Feature: <select name="specialFeature">
						<option value="" selected>None</option>
						<option value="Trailers">Trailers</option>
						<option value="Commentaries">Commentaries</option>
						<option value="DeletedScenes">DeletedScenes</option>
						<option value="BehindTheScenes">BehindTheScenes</option>
					</select><br> 
					
					Language: <select name="languageID">
						<option value="1" selected>English</option>
						<option value="2">Italian</option>
						<option value="3">Japanese</option>
						<option value="4">Mandarin</option>
						<option value="5">French</option>
						<option value="6">German</option>
					</select><br>
					
					Category: <select name="categoryId">
						<option value="1" selected>Action</option>
						<option value="2">Animation</option>
						<option value="3">Children</option>
						<option value="4">Classics</option>
						<option value="5">Comedy</option>
						<option value="6">Documentary</option>
						<option value="7">Drama</option>
						<option value="8">Family</option>
						<option value="9">Foreign</option>
						<option value="10">Games</option>
						<option value="11">Horror</option>
						<option value="12">Music</option>
						<option value="13">New</option>
						<option value="14">Sci-Fi</option>
						<option value="15">Sports</option>
						<option value="16">Travel</option>		
					</select><br><br>
					
					<input type="submit" value="Submit">
					
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
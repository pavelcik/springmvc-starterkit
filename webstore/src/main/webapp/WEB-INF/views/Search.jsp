<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Search Books</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Search</h1>
				<p>Please enter your search criteria</p>
			</div>
		</div>
	</section>
<div class="container-fluid">
		<div class="row">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Search for book by title</h3>
					</div>
					<div class="panel-body">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="title"
										name="title" type="text">
								</div>
								
							<div class="panel panel-default">
								<div class="panel-heading">
						<h3 class="panel-title">Search for book by its author</h3>
					</div>
					</div>
					
								<div class="form-group">
									<input class="form-control" placeholder="author"
										name="book.{author}" type="text">
								</div>
								<input class="btn btn-lg btn-success btn-block" type="submit"
									value="Submit">
							</fieldset>
						</form>
					</div>
				</div>
			
		</div>
	</div>
	<div>
	${searchResult}
	</div>
</body>
</html>
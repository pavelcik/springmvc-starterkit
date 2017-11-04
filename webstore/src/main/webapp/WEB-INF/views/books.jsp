<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://getbootstrap.com/dist/css/bootstrap.min.css">
<title>Books</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Books</h1>
				
			</div>
		</div>
	</section>
	
	 <div class="album text-muted">
        <div class="container">
        	<div align=right>
					
					<p>
					<a href="<spring:url value="/" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Main Page
					</a>

				</p>
				

			</div>
		<div class="row">
			<c:forEach items="${bookList}" var="book">
			 <div class="card" style="
    width: 202px;
    height: 302px;

">
              <center><img src="https://openclipart.org/download/275692/1489798288.svg" style="width: 160px;height: 160px;"  alt="" class="img-responsive center-block"></center>
            <center> <p class="card-text"> ${book.title}</p> 
            <p> ${book.authors} </p> </center>
            
              <p>
          <center>
							<a
									href=" <spring:url value="/books/book?id=${book.id}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Details
								</a>
							
							
								<a
									href=" <spring:url value="/books/delete?id=${book.id}" /> "
									class="btn btn-danger"> <span
									class="glyphicon-info-sign glyphicon" /></span> Delete
								</a>
							</p>
						</center>	
						</div>
					
			</c:forEach>
		</div>
		</div>
	</div> 
</body>
</html>

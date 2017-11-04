<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="http://getbootstrap.com/dist/css/bootstrap.min.css">
<title>Search For Book</title>
<style>
body {
    background-image: url("http://stmedia.startribune.com/images/card1.jpg");
    background-repeat: no-repeat;
    background-position: right top;
    margin-right: 200px;
}
</style>
</head>
<body>
		<div class="search-background">
            
            <div class="well-searchbox">
                <form class="form-horizontal" action="/webstore/books/result" method="GET" role="form">
                    <div class="form-group">
                  <p>
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4">
                            <input type="text" name="title" class="form-control" placeholder="Search by title">
                           
                        </div>
                        
                    
                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-4">
                            <input type="text" name="author" class="form-control" placeholder="Search by author">
                        </div>
                        
                    </div>
                    <div class="col-sm-offset-4 col-sm-5">
                        <button type="submit" value="submit" class="btn btn-primary">Search</button>
                    </div>
                   
                </form>
            </div>
            </body>
	
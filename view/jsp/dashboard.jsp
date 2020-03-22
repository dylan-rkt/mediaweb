<!DOCTYPE HTML>
<html>

    <head>
	    <title>MediaWeb</title>
	
        <%@ page contentType="text/html; charset=UTF-8" %>
		<link rel="stylesheet" href="view/css/style.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
    </head>
	
    <body>
	
        <jsp:include page="header.jsp"/>
        <% if((boolean) request.getSession().getAttribute("userBiblio")) { %>
            <%@include file="librarian.jsp" %>
        <% } else { %>
            <%@include file="user.jsp" %>
        <% } %>
		
    </body>
	
</html>

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
	
    <header class="en-tête text-center" style="background: linear-gradient(to bottom right, #ded5cc 15%, #e6c28c 75%) !important; box-shadow: 0 0 7px 0px;">

            <div class="container navbar-top">
                
                <img class="taille" src="view/css/img/mediaweb-logo.png" alt="logo"/>
            
                <p><h3>MediaWeb, la médiathèque à votre service</h3><p>
                
            </div>
            
        </header>
        
        <% if((boolean) request.getSession().getAttribute("userBiblio")) { %>
            <%@include file="librarian.jsp" %>
        <% } else { %>
            <%@include file="user.jsp" %>
        <% } %>
		
    </body>
	
</html>

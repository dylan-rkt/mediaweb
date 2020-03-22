<!DOCTYPE HTML>
<html>

	<head>
		
    	<%@ page contentType="text/html; charset=UTF-8" %>
		
    </head>

	<div id="livreContainer">
	
		<div class="container text-center mt-5">
		
			<p class="font-weight-bold h1 txt">Emprunter Réserver Retourner</p>

			<div class="row d-flex justify-content-between mt-5">
			
				<ul class="card list-group col-5 mt-4 ml-3 pr-0 row">
				
					<p class="h5 card-header">Emprunter / Réserver</p>

					<%@page import="mediatek2020.items.Document"%>
					<%@page import="mediatek2020.Mediatheque"%>
					
					<form method="get" action="empresdoc" class="pl-5 pt-4 pb-5 pr-5">
					<% for (Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
						<% if (infos[3] == null) { %>
							<div class="row">
								<input type="radio" id="<%=infos[4]%>" name="id" value="<%=infos[4]%>">
								<label for="<%=infos[4]%>" class="pl-4"><%=infos[1]%></label>
							</div>
						<% } %>
					<% } %>	
						
						<hr>
						
						<div class="mt-3">
							<input name="traitement" type="submit" value="emprunt" class="btnSubmit"/> 
							<input name="traitement" type="submit" value="réserver" class="btnSubmit"/>
						</div>
						
					</form>
					
				</ul>

				<ul class="card list-group col-5 mt-4 ml-3 pr-0 row" id="listRendre">
				
					<p class="h5 card-header">Retourner</p>
					
					<form method="get" action="retourdoc" class="pl-5 pt-4 pb-5 pr-5">
					<% for (Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
						<% if (!(infos[3] == null) && infos[3].equals((String) request.getSession().getAttribute("login"))) { %>
							<div class="row">
								<input type="radio" id="<%=infos[4]%>" name="id" value="<%=infos[4]%>">
								<label for="<%=infos[4]%>" class="pl-4"><%=infos[1]%></label>
							</div>
						<% } %>
					<% } %>
						
						<hr>
						
						<br/>
						
						<input type="submit" value="retourner" class="btnSubmit" />
						
					</form>
					
				</ul>
				
			</div>

			<div class="col-2 offset-5 pl-5 mt-5 deco">
			
				<form action=<%= request.getContextPath()%>>
							
					<input class="btnSubmit disconnect" type="submit" value="Se déconnecter">
					
				</form>
				
			</div>
			
		</div>
		
	</div>

</html>

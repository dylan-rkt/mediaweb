<!DOCTYPE HTML>
<html>

	<head>
	
    	<%@ page contentType="text/html; charset=UTF-8" %>
		
    </head>


	<script type='text/javascript'>
		function indisponible() {
			alert("Action encore indisponible !");
		}
	</script>

	<div id="livreContainer">
	
		<div class="container text-center border p-5 mt-5 mb-5">
		
			<p class="font-weight-bold h4 mouseSelected">Emprunter ou rendre un document</p>

			<div class="row d-flex justify-content-between">
			
				<ul class="list-group col-3 mt-3 row" id="listEmprunt">
				
					<p class="mouseSelected">Emprunter</p>

					<%@page import="mediatek2020.items.Document"%>
					<%@page import="mediatek2020.Mediatheque"%>
					


					<form method="get" action="empresdoc">
					<% for(Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
						<% if (infos[3] == null) { %>
							<div class="row">
								<input type="radio" id="<%=infos[4]%>" name="id" value="<%=infos[4]%>">
								<label for="<%=infos[4]%>"><%=infos[1]%></label>
							</div>
						<% } %>
					<% } %>	
						<input name="traitement" type="submit" value="emprunt"/> 
						<input name="traitement" type="button" value="réserver" onclick="indisponible()"/>
					</form>
				</ul>

				<i class="fas fa-exchange-alt col-2 d-flex align-items-center justify-content-center"></i>

				<ul class="list-group col-3 mt-3 row" id="listRendre">
				
					<p class="mouseSelected">Rendre</p>
					
					<form method="get" action="retourdoc">
					<% for(Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
						<% if(!(infos[3] == null) && infos[3].equals((String) request.getSession().getAttribute("login"))) { %>
							<div class="row">
								<input type="radio" id="<%=infos[4]%>" name="id" value="<%=infos[4]%>">
								<label for="<%=infos[4]%>"><%=infos[1]%></label>
							</div>
						<% } %>
					<% } %>
						<input type="submit" value="rendre"/> 
					</form>
				</ul>
				
			</div>
			
		</div>
		
	</div>

</html>

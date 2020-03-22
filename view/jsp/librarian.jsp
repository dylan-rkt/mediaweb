<!DOCTYPE HTML>
<html>

	<head>
	
    	<%@ page contentType="text/html; charset=UTF-8" %>
		
    </head>

	<div id="livreContainer">
	
		<div class="container text-center mt-5">
		
			<p class="font-weight-bold h1 txt">Ajouter Rechercher</p>

			<div class="row d-flex justify-content-between mt-5">
			
				<ul class="card list-group col-5 mt-4 ml-3 pr-0 row" id="formAjout">

					<p class="h5 card-header">Retourner</p>

					<form method="get" action="ajoutdoc" class="pl-5 pt-4 pb-5 pr-5">

						<div class="form-group">
						
							<label for="inputTitre">Titre</label>
							
							<input name="inputTitre" type="text" class="form-control" aria-describedby="emailHelp" placeholder="Titre du document" required>
							
						</div>

						<div class="form-group">
						
							<label for="inputAuteur">Auteur</label>
							
							<input name="inputAuteur" type="text" class="form-control" placeholder="Auteur du document" required>
							
						</div>

						<label class="mr-sm-2 mouseSelected" for="inlineFormCustomSelect">Type</label>

						<select name="type" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
						
							<option value="1" selected>Livre</option>
							<option value="2">DVD</option>
							<option value="3">CD</option>
							
						</select>
								
						<hr>

						<input type="submit" value="Ajouter" class="btnSubmit mt-4"/>

					</form>

				</ul>

				<ul class="card list-group col-5 mt-4 ml-3 pr-0 row">
				
					<p class="h5 card-header mb-2">Liste des documents</p>
					
					<% for (Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
		                		
								<li class="list-unstyled mt-1"><span class="font-italic">"<%=infos[1]%>"</span> (<%=infos[2]%>)</li>
					
					<% } %>
					
				</ul>

				
			</div>
			
		</div>

		<div class="col-2 offset-5 pl-5 mt-5">
		
			<form action=<%= request.getContextPath()%>>
						
				<input class="btnSubmit disconnect" type="submit" value="Se déconnecter">
				
			</form>
			
		</div>

	</div>

</html>

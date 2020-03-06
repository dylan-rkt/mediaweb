<!DOCTYPE HTML>
<html>

	<head>
	
    	<%@ page contentType="text/html; charset=UTF-8" %>
		
    </head>

	<div id="livreContainer">
	
		<div class="container text-center border p-5 mt-5 mb-5">
		
			<p class="font-weight-bold h4 mouseSelected">Ajouter des documents</p>

			<div class="row d-flex justify-content-between">
			
				<ul class="list-group col-3 mt-3 row" id="listEmprunt">
				
					<p class="mouseSelected">Documents présents</p>
					
					<% for(Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
						
							<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#toggleDescDocID<%=infos[4]%>"
		                	aria-controls="toggleDescDocID<%=infos[4]%>" aria-expanded="false" aria-label="Toggle navigation">
		                		
								<li class="list-group-item"><a class="mouseSelected"><%=infos[1]%></a></li>
								
					              <div class="collapse" id="toggleDescDocID<%=infos[4]%>">
								  
					                <div class="bg-dark p-4">
									
										<h4 class="text-white mouseSelected">Auteur : <%=infos[2]%></h4>
										
										<span class="text-muted mouseSelected"><%=infos[0]%></span>
										
					                </div>
									
					            </div>
								
		            		</button>
					<% } %>
					
				</ul>
				
				<i class="fas fa-plus col-2 d-flex align-items-center justify-content-center"></i>

				<ul class="list-group col-3 mt-3 row" id="formAjout">

					<form method="get" action="ajoutdoc">

						<div class="form-group">
						
							<label for="inputTitre" class="mouseSelected">Titre</label>
							
							<input name="inputTitre" type="text" class="form-control mouseSelected" aria-describedby="emailHelp" placeholder="Titre du document">
							
						</div>

						<div class="form-group">
						
							<label for="inputAuteur" class="mouseSelected">Auteur</label>
							
							<input name="inputAuteur" type="text" class="form-control mouseSelected" placeholder="Auteur du document">
							
						</div>

						<label class="mr-sm-2 mouseSelected" for="inlineFormCustomSelect">Type</label>

						<select name="type" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
						
							<option value="1" selected>Livre</option>
							<option value="2">DVD</option>
							<option value="3">CD</option>
							
						</select>

						<input type="submit" class="btn btn-primary mt-3"/>

					</form>

				</ul>
				
			</div>
			
		</div>

	</div>

</html>

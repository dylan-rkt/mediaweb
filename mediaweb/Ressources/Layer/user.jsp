<!DOCTYPE HTML>
<html>

	<head>
    	<%@ page contentType="text/html; charset=UTF-8" %>
    </head>

	<div id="livreContainer">
		<div class="container text-center border p-5 mt-5 mb-5">
			<p class="font-weight-bold h4 styleSelection">Emprunter ou rendre un document</p>

			<div class="row d-flex justify-content-between">
				<ul class="list-group col-3 mt-3 row" id="listEmprunt">
					<p class="styleSelection">Emprunter</p>

					<%@page import="mediatek2020.items.Document"%>
					<%@page import="mediatek2020.Mediatheque"%>
					<% for(Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
						<% if(infos[3] == null) { %>
							<form method="get" action="empruntdoc">
								<input type="hidden" name="id" value="<%=infos[4]%>"/>
								<input class="navbar-toggler" type="submit" value="<%=infos[1]%>"/>
							</form>
				                <!--<li class="list-group-item"><a class="styleSelection"><%=infos[1]%></a></li>
 								<div class="collapse" id="toggleDescDocID1">
					                <div class=" bg-dark p-4">
					                  <h4 class="text-white styleSelection">Auteur : <%=infos[2]%></h4>
					                  <span class="text-muted styleSelection"><%=infos[0]%></span>
					                </div>
				              	</div>
				            </button>-->
						<% } %>
					<% } %>
				</ul>

				<i class="fas fa-exchange-alt col-2 d-flex align-items-center justify-content-center"></i>

				<ul class="list-group col-3 mt-3 row" id="listRendre">
					<p class="styleSelection">Rendre</p>
					<% for(Document d : Mediatheque.getInstance().tousLesDocuments()) { %>
						<% String[] infos = (String[]) d.data(); %>
						<% if(!(infos[3] == null) && infos[3].equals((String) request.getSession().getAttribute("login"))) { %>
							<form method="get" action="retourdoc">
								<input type="hidden" name="id" value="<%=infos[4]%>"/>
								<input class="navbar-toggler" type="submit" value="<%=infos[1]%>"/>
							</form>
				                <!--<li class="list-group-item "><a class="styleSelection"><%=infos[1]%></a></li>
				              	<div class="collapse" id="toggleDescDocID7">
					                <div class=" bg-dark p-4">
					                  <h4 class="text-white styleSelection">Auteur : <%=infos[2]%></h4>
					                  <span class="text-muted styleSelection"><%=infos[0]%></span>
					                </div>
				              	</div>
				            </button>-->
						<% } %>
					<% } %>
				</ul>
			</div>
		</div>
	</div>

</html>

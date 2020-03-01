<!DOCTYPE HTML>
<html lang="fr">

    <head>
        <%@ page contentType="text/html; charset=UTF-8" %>
		<link rel="stylesheet" href="Ressources/CSS/connexion.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		
        <title>Connexion</title>
		
    </head>

    <body>

		<div class="background">
		
			<div class="container login-container">
			
				<div class="row justify-content-center">

					<div class="col-md-6 login-form login-form-user">
					
						<h3 class="text-center">Espace Client</h3>
						
						<form method="post" action="./connexion">
						
							<div class="form-group">
							
								<input type="text" class="form-control" placeholder="Identifiant" value="" name="login" required/>
							
							</div>
							
							<div class="form-group">
							
								<input type="password" class="form-control" placeholder="Mot de passe" value="" name="password" required/>
							
							</div>
							
							<div class="form-group">
							
								<input type="submit" class="btnSubmit bg-primary" value="Login" />
								
							</div>

						</form>
						
					</div>
					
				</div>
				
			</div>

			<ul class="bg-bubbles">
			
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				
			</ul>

		</div>
		
    </body>
	
</html>

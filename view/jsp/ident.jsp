<!DOCTYPE HTML>
<html lang="fr">

    <head>
	
		<title>Connexion</title>
	
        <%@ page contentType="text/html; charset=UTF-8" %>
		
		<link rel="stylesheet" href="view/css/ident.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		
    </head>

    <body>
		<div class="background">

			<div class="container login-container">
			
				<div class="row justify-content-center">

					<div class="col-md-7 login-form login-form-user">
					
						<p><h2>Bienvenue dans la médiathèque MediaWeb !</h2></p>

						<br/>

						<h4>Veuillez vous identifier</h4>
						
						<form method="post" action="./ident" class="offset-1 col-10">
						
							<div class="form-group">
							
								<input type="text" class="form-control" placeholder="Identifiant" value="" name="login" required/>
							
							</div>
							
							<div class="form-group">
							
								<input type="password" class="form-control" placeholder="Mot de passe" value="" name="password" required/>
							
							</div>
							
							<div class="form-group">
							
								<input type="submit" class="btnSubmit" value="Login" />
								
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

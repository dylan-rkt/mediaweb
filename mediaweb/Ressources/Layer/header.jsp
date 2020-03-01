<%@ page pageEncoding="UTF-8" %>
<nav class="d-flex justify-content-between navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand styleSelection" href="#">MediaWeb</a>

  <div id="navbarSupportedContent">
      <form action=<%= request.getContextPath() %>>
          <input class="btn btn-outline-success my-sm-0" type="submit" value="Deconnexion">
      </form>
  </div>
</nav>

<header class="bg-primary text-white text-center">
    <div class="container">
      <h1 class="text-uppercase mb-0 styleSelection">MediaWeb</h1>
      <h2 class="font-weight-light mb-0 styleSelection">Gérez votre bibliothèque ou vos emprunts</h2>
    </div>
</header>

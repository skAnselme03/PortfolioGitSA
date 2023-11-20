<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main-header">
    <div class="logo">
        <a href="${pageContext.request.contextPath}/acceuil"><img src="${pageContext.request.contextPath}/Assets/Img/logo.png" alt="Logo de notre site sans nom"></a>
    </div>
    <nav class="nav-links">
        <ul>
            <li><a href="${pageContext.request.contextPath}/acceuil">Accueil</a></li>
            <li><a href="${pageContext.request.contextPath}/contact">Contactez-nous</a></li>
            <li><a href="${pageContext.request.contextPath}/catalogue">Produits</a></li>
            <c:if test="${not clientConnecte && not isAdmin}">
                <li><a href="login">Se connecter</a></li>
            </c:if>
             
            <c:if test="${sessionScope.clientConnecte && not isAdmin}">                    
               <li> <a href="${pageContext.request.contextPath}/profilClient" class="btn btn-danger">${clientDonnees.nom}, ${clientDonnees.prenom} (Profil)</a></li>
            </c:if>
            <%-- Afficher le bouton sigUp si l'utilisateur n'existe pas et n'est pas un admin --%>
            <c:if test="${(not empty(errorMessageLogin) or not clientConnecte) && not isAdmin}">
  				<li><a href="signUp">Cr�er un compte</a></li>
			</c:if>

             <!-- Afficher le bouton de page admin si l'internaute est un admin -->
		     <c:if test="${sessionScope.isAdmin or isAdmin}">
		          <li><a href="${pageContext.request.contextPath}/pageAdmin">Administration</a></li>
		     </c:if>
             <%-- Afficher le bouton de d�connection si le client ou l'admin est connect� --%>
             <c:if test="${clientConnecte or isAdmin}">
                 <li><a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">D�connexion</a></li>
             </c:if>
             <li><a href="${pageContext.request.contextPath}/panier">Panier</a></li>
        </ul>
    </nav>
    <form class="nav-recherche" action="${pageContext.request.contextPath}/CatalogueServlet" method="GET">
        <input type="text" name="search" placeholder="Chercher un produit sp�cifique">
        <button type="submit">&#128269;</button>
    </form>
</header> 
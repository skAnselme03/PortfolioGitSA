// Quand l'utilisateur scroll la page, exécuter la fonction menuSticky
window.onscroll = function() {
   var navbar = document.getElementById("navbar");
    if (window.pageYOffset > 0) {
        navbar.classList.add("sticky");
    } else {
        navbar.classList.remove("sticky");
    }
};
/*
// Récupérer la navbar
let navbar = document.querySelector(".navbar");

// Trouver la position offset de la navbar
let sticky = navbar.offsetTop;

// Ajouter la classe "sticky" à la navbar quand on atteint sa position de scroll. Retirer la classe "sticky" quand on quitte la position de scroll.
function menuSticky() {
  if (window.scrollY >= sticky) {
    navbar.classList.add("sticky");
  } else {
    navbar.classList.remove("sticky");
  }
}*/
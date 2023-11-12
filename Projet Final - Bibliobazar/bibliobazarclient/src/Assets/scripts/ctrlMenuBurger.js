
/* Masquer la bannière Hero lorsque le menu hamburger est activé et que le dom est déjà chargé */
document.addEventListener("DOMContentLoaded", function() {
    const menuBurger = document.querySelector('.menu-burger');
    const heroBanner = document.querySelector('.header__hero');

    menuBurger.addEventListener('change', function () {
        if (this.checked) {
            heroBanner.style.display = 'none';
        } else {
            heroBanner.style.display = 'flex';
        }
    });
});


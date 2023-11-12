import React from "react";

const PiedDePage = () =>{
    return(
        <footer>
            <p className="footer-text-center">Les livres, leur titre, image et code ISBN proviennent des sites 
                {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
                <a href="https://www.abebooks.com/Canada/"> Abebooks</a> et <a href="https://www.amazon.com/b?node=283155">Amazon</a></p>

            <p>Camille Andriamamonjy &  Stéphanie Anselme &copy; 2023, Tous droits réservés</p>
        </footer>
    );
}
export default PiedDePage;
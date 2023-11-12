import React, { useState } from "react";
import { NavLink, useLocation } from 'react-router-dom';
import logo from "../../Assets/img/Logo.png";
import Hero from "./Hero";
import { useAuth } from '../../AuthContext';

const Entete = ({ handleLogout }) => {
    const location = useLocation();
    const { estConnecter, isAdmin } = useAuth();
    const [menuBurgerChecked, setMenuBurgerChecked] = useState(false);

    const handleMenuBurgerChange = (event) => {
        setMenuBurgerChecked(event.target.checked);
    };

    const closeMenuBurger = () => {
        setMenuBurgerChecked(false);
    };

    console.log('estConnecter in Entete:', estConnecter);

    return (
        <header>
            <section className="menu_entete wrapper">
                <picture>
                    <img src={logo} alt="Logo provisoire" />
                </picture>
                <nav>
                    <input type="checkbox" className="menu-burger" checked={menuBurgerChecked} onChange={handleMenuBurgerChange} />

                    <ul>
                        <li><NavLink to='/' onClick={closeMenuBurger}>Accueil</NavLink></li>
                        <li><NavLink to='/Catalogue' onClick={closeMenuBurger}>Catalogue</NavLink></li>
                        {/* Affiche les liens selon si l'utilisateur est connecté ou non */}
                        {(!estConnecter && !isAdmin) && (
                            <>
                                <li><NavLink to='/SeConnecter' onClick={closeMenuBurger}>Se connecter</NavLink></li>
                                <li><NavLink to='/CreerUnCompte' onClick={closeMenuBurger}>Créer un compte</NavLink></li>
                            </>
                        )}
                        {estConnecter && !isAdmin && (
                            <>
                                <li><NavLink to='/CreerAnnonce' onClick={closeMenuBurger}>Créer une annonce</NavLink></li>
                                <li><NavLink to='/Profil' onClick={closeMenuBurger}>Profil</NavLink></li>
                                <li><NavLink to='/SeDeconnecter' onClick={() => { handleLogout(); console.log("Lien déconnexion cliqué"); }}>Déconnexion</NavLink></li>
                            </>
                        )}
                        {isAdmin && (
                            <>
                                <li><NavLink to='/PanelAdmin' onClick={closeMenuBurger}>Panneau administratif</NavLink></li>
                                <li><NavLink to='/SeDeconnecter' onClick={() => { handleLogout(); console.log("Lien déconnexion cliqué"); }}>Déconnexion</NavLink></li>
                            </>
                        )}
                    </ul>
                </nav>
            </section>
            {(location.pathname === "/" || location.pathname === "/Catalogue") && !menuBurgerChecked && <Hero />}
        </header>
    );
}

export default Entete;

import React, { useState } from "react";
import axios from "axios";
import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from '../../AuthContext';

// Sous-composant pour le formulaire de connexion
function FormulaireDeConnection({ onLogin }) {
    const [login, setLogin] = useState("");
    const [motDePasse, setMotDePasse] = useState("");
    const { logAdmin } = useAuth();

    const navigate = useNavigate();
    const [infoMessage, setInfoMessage] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();

        if (motDePasse === "admin" && (login === "12345" || login === "54321")) {
            // Connecte l'admin
            console.log('Logging in as admin');
            logAdmin();
            navigate('/PanelAdmin');
        } else {
            console.log('Login as admin failed. Login:', login, 'Password:', motDePasse);
            try {
                const response = await axios
                    .post("http://localhost:5227/api/Membre/RecupererMembreParUsernamePassword", {
                        Login: login,
                        MotDePasse: motDePasse,
                    });
                console.log("Login: ", login);
                console.log("MotDePasse: ", motDePasse);

                // Utiliser les données du Membre connecté
                const membre = response.data;
                const estConnecter = true;
                console.log("Membre connecté:", membre);
                console.log("estConnecter:", estConnecter);

                // Vérifie si la réponse contient le token JWT et les informations du membre
                if (response.data && response.data.token) {
                    const memberInfo = response.data.memberInfo;

                    // Vérifie si le membre n'a pas été archivé
                    if (memberInfo.statut === "Actif") {
                        // Récupère le token
                        const token = response.data.token;
                        console.log("Token :", token);

                        // Store le token dans les cookies (localStorage) pour maintenir une session
                        localStorage.setItem('token', token);
                        console.log("cookies:", localStorage);

                        // Récupère les informations du membre
                        const memberInfo = response.data.memberInfo;
                        console.log("membre:", memberInfo);
                        setInfoMessage("");

                        // Redirige immédiatement vers la page Accueil
                        navigate('/');

                        // Appelle la fonction onLogin pour indiquer qu'un utilisateur est connecté
                        onLogin(memberInfo, true);
                    } else {
                        setInfoMessage("Cet utilisateur a été supprimé.");
                    }
                } else {
                    // La connexion a échouée
                    console.error("Pas de token dans la réponse");
                    setInfoMessage("Nom d'utilisateur ou mot de passe incorrect.");
                }
            } catch (error) {
                console.error("Échec de la connexion:", error);
                setInfoMessage("Nom d'utilisateur ou mot de passe incorrect.");
                //TODO: À rediriger vers la page 404!
            }
        }
    };

    return (
        <div>
            <form onSubmit={handleLogin}>
                <fieldset>
                    <label htmlFor="login">Nom d'utilisateur ou Adresse e-mail:</label>
                    <input
                        type="text"
                        name="login"
                        id="login"
                        placeholder="Entrer votre username ou votre email"
                        aria-label="un email ou un userna quelconque"
                        required
                        value={login}
                        onChange={(e) => setLogin(e.target.value)}
                    />
                    <label htmlFor="password">Mot de passe</label>
                    <input
                        type="password"
                        name="password"
                        id="password"
                        aria-label="un password quelconque"
                        placeholder="Entrer votre password"
                        required
                        value={motDePasse}
                        onChange={(e) => setMotDePasse(e.target.value)}
                    />
                </fieldset>
                <div className='mdpOublie-container'>
                    <em><NavLink to='/MotDePasseOublie'>Mot de passe oublié?</NavLink></em>
                </div>
                <button type="submit" className="">
                    Connexion
                </button>
            </form>
            <div>
                {infoMessage && <p>{infoMessage}</p>}
            </div>
        </div>
    );
}
export default FormulaireDeConnection;

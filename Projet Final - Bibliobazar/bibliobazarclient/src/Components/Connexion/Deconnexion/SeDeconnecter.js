import React, { useState } from "react";
const SeDeconnecter = ({ onLogout }) => {
    const [deconnexionEffectuee, setDeconnexionEffectuee] = useState(false);

    const handleDeconnexion = () => {
        // Vous pouvez effectuer les actions de déconnexion ici, par exemple :
        // - Réinitialiser les informations de l'utilisateur connecté
        // Nettoie le localStorage
        console.log('Before removal - localStorage:', localStorage);
        localStorage.removeItem('userToken');
        localStorage.removeItem('userData');
        console.log('After removal - localStorage:', localStorage);
        // - Mettre à jour l'état de connexion
        console.log('onLogout function:', onLogout);
        onLogout(); // Appeler la fonction de déconnexion passée en prop
        console.log('Bouton déconnexion cliqué (SeDeconnecter.js)');

        // Marquer la déconnexion comme effectuée
        window.location.reload();   // Rafraîchir la page permet de se déconnecter...
    };

    // Si la page est rechargée, setDeconnexionEffectuee == true
    window.onload = () => {
        if (!deconnexionEffectuee) {
            setDeconnexionEffectuee(true); // Et le message s'affiche comme si on n'avait pas rafraichi...
        }
    };


    return (
        <section>
            {deconnexionEffectuee ? (
                <p className={"succes"}>Vous avez été déconnecté avec succès.</p>
            ) : (
                <div>
                    <p>Êtes-vous sûr de vouloir vous déconnecter ?</p>
                    <button onClick={handleDeconnexion}>Déconnexion</button>
                </div>
            )}
        </section>
    );
};
export default SeDeconnecter;
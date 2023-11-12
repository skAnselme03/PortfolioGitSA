import React, { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import { useAuth } from '../../../AuthContext';
import FormulaireDeConnection from '../../../Assets/scripts/FormulaireDeConnexion';
import userIcon from '../../../Assets/img/user-icon.png'

const SeConnecter = () => {
    const location = useLocation();
    const { membreConnecte, login, estConnecter } = useAuth();

    const [compteEstCreer, setCompteEstCreer] = useState(false);

    const handleLogin = (user) => {
        login(user);
    };

    useEffect(() => {
        const parametreRecherche = new URLSearchParams(location.search);
        setCompteEstCreer(parametreRecherche.get("compteEstCreer") === "true");
    }, [location.search]);

    console.log("estConnecter = " + estConnecter);

    return (
        <section>
            <div className="seconnecter wrapper">
                <div>
                    <h2>Connexion<span><img src={userIcon} alt="Icon user"/></span></h2>
                    <p>Veuillez entrer vos identifiants</p>
                </div>
                {membreConnecte ? (
                    <div>
                        <p>Vous êtes connecté en tant que {membreConnecte.username}</p>
                    </div>
                ) : (
                    <>
                        {compteEstCreer && (
                            <p>Le compte a été créé avec succès. Vous pouvez maintenant vous connecter.</p>
                        )}
                        <FormulaireDeConnection onLogin={handleLogin} />
                    </>
                )}
            </div>
        </section>
    );
}

export default SeConnecter;
import React, { useState, useEffect } from "react";
import { useAuth } from '../../AuthContext';
import { Link } from "react-router-dom";

const CreerAuteur = () => {
    const { estConnecter, membreConnecte } = useAuth();
    const [nom, setNom] = useState("");
    const [prenom, setPrenom] = useState("");
    const [dateNaissance, setDateNaissance] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        // Pour vider le formulaire
        const resetForm = () => {
            setNom("");
            setPrenom("");
            setDateNaissance("");
        };

        // Pour créer un nouvel auteur
        const newAuteur = {
            Nom: nom,
            Prenom: prenom,
            DateNaissance: dateNaissance,
            Statut: "Visible"
        };

        try {
            const response = await fetch("http://localhost:5227/api/Auteur/CreerAuteur", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newAuteur)
            });

            if (response.status == 201 || response.status == 200) {
                // Auteur created successfully, you can handle success here
                console.log("Auteur·e créé·e avec succès!");
                resetForm(); // Vide le formulaire
                setErrorMessage(""); // Nettoie le précédent message d'erreur, s'il y a lieu
                setSuccessMessage("Auteur·e créé·e avec succès.");
            } else {
                console.error("Erreur lors de la création de l'auteur·e.");
                setSuccessMessage(""); // Nettoie le précédent message de succès, s'il y a lieu
                setErrorMessage("Erreur lors de la création de l'auteur·e.");
            }
        } catch (error) {
            console.error("Erreur:", error);
            setSuccessMessage(""); // Nettoie le précédent message de succès, s'il y a lieu
            setErrorMessage("Erreur lors de la création de l'auteur·e.");
        }
    };

    return (
        <section>
            {estConnecter && membreConnecte ? (
                <div className="creer-une-annonce wrapper">
                    <div>
                        <h2>Créer un nouvel auteur</h2>
                        <p><em>Vous êtes connecté·e en tant que <strong>{membreConnecte.username}</strong></em></p>
                    </div>
                    <form onSubmit={handleFormSubmit}>
                        <input
                            type="text"
                            value={nom}
                            placeholder="Nom de l'auteur·e"
                            onChange={(e) => setNom(e.target.value)}
                            required
                        />

                        <input
                            type="text"
                            value={prenom}
                            placeholder="Prénom de l'auteur·e"
                            onChange={(e) => setPrenom(e.target.value)}
                            required
                        />

                        <input
                            type="date"
                            value={dateNaissance}
                            placeholder="Date de naissance"
                            onChange={(e) => setDateNaissance(e.target.value)}
                            required
                        />

                        <button type="submit">Créer Auteur</button>
                    </form>
                    <br />
                    {successMessage && <p>{successMessage}</p>}
                    {errorMessage && <p>{errorMessage}</p>}
                    <br />
                    <Link to={`/CreerAnnonce`}>Retour à la page précédente</Link>
                </div>
            ) : (
                <p>Veuillez vous connecter pour accéder à cette page</p>
            )}
        </section>
    );
};

export default CreerAuteur;
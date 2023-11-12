import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { useAuth } from '../../AuthContext';

// ... other imports

const Noter = () => {
    const { numeroReference } = useParams();
    const { estConnecter, membreConnecte } = useAuth();
    const [annonce, setAnnonce] = useState(null);
    const [infoMessage, setInfoMessage] = useState("");
    const [starRating, setStarRating] = useState(0); // Initialize with 0 stars
    const [description, setDescription] = useState("");

    useEffect(() => {
        if (estConnecter && membreConnecte) {
            const fetchAnnonce = async () => {
                try {
                    const response = await fetch(
                        `http://localhost:5227/api/Annonce/RecupererAnnonceParNumeroReference?numeroReference=${numeroReference}`
                    );

                    if (!response.ok) {
                        throw new Error("La réponse du serveur n'est pas bonne");
                    }

                    const data = await response.json();

                    if (data && data.length > 0) {
                        setAnnonce(data[0]);
                    }
                } catch (error) {
                    console.error("Erreur lors de la récupération de l'annonce:", error);
                }
            };

            fetchAnnonce();
        }
    }, [estConnecter, membreConnecte, numeroReference]);

    const formatDate = (dateString) => {
        if (!dateString) return "Date non disponible";

        const options = {
            day: "numeric",
            month: "long",
            year: "numeric",
        };

        return new Date(dateString).toLocaleDateString("fr-FR", options);
    };

    const handleStarClick = (rating) => {
        setStarRating(rating);
    };

    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };

    const handleSubmitNote = async () => {
        try {
            const response = await fetch(
                `http://localhost:5227/api/Note/CreerNote?CodeMembre=${membreConnecte.codeUtilisateur}&CodeAnnonce=${annonce.numeroReference}&Evaluation=${starRating}&Description=${encodeURIComponent(description)}`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                });

            if (response.ok) {
                const data = await response.text();
                console.log("Server response:", data);

                if (data.trim() === "Note créée") {
                    setInfoMessage("Note créée avec succès");
                } else {
                    console.log("Server response:", data);
                    setInfoMessage("Erreur lors de la création de la note");
                }
            } else if (response.status === 409) {
                // Conflict: Note déjà existante par ce membre pour cette annonce
                setInfoMessage("Vous avez déjà noté cette annonce.");
            } else {
                throw new Error("La réponse du serveur n'est pas bonne");
            }
        } catch (error) {
            console.error("Erreur lors de la création de la note:", error);
            setInfoMessage("Erreur lors de la création de la note");
        }
    };

    return (
        <section>
            {estConnecter && membreConnecte ? (
                <div className="creer-une-annonce wrapper" style={{ textAlign: 'center' }}>
                    <div>
                        <h2>Noter l'annonce de {annonce ? annonce.membreUsername : "l'utilisateur"}</h2>
                        {annonce ? (
                            <>
                                <p>Annonce - {annonce.titreLivre}</p>
                                <p>Publiée le {formatDate(annonce.datePublicationAnnonce)}</p>
                            </>
                        ) : (
                            <p>Chargement de l'annonce...</p>
                        )}
                    </div>
                    <form>
                        <fieldset>
                            <div>
                                {[1, 2, 3, 4, 5].map((rating) => (
                                    <span
                                        key={rating}
                                        onClick={() => handleStarClick(rating)}
                                        style={{
                                            cursor: "pointer",
                                            color: rating <= starRating ? "gold" : "gray",
                                            fontSize: "5em", // Adjust the font size here
                                        }}
                                    >
                                        ★
                                    </span>
                                ))}
                            </div>
                            <div>
                                <label htmlFor="description">Description:</label>
                                <textarea
                                    id="description"
                                    name="description"
                                    value={description}
                                    placeholder="Commentaire"
                                    onChange={handleDescriptionChange}
                                />
                            </div>
                        </fieldset>
                        <button type="button" onClick={handleSubmitNote}>
                            Enregistrer la note
                        </button>
                        {annonce && numeroReference && (
                            <Link to={`/Annonce/${annonce.numeroReference}`}>
                                <button>Revenir à l'annonce</button>
                            </Link>
                        )}
                    </form>
                    {infoMessage && <p>{infoMessage}</p>}
                </div>
            ) : (
                <p>Veuillez vous connecter pour accéder à cette page</p>
            )}
        </section>
    );
};

export default Noter;
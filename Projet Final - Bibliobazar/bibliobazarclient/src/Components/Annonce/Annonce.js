import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { useAuth } from '../../AuthContext';

const Annonce = () => {
    const { numeroReference } = useParams();
    const { estConnecter, membreConnecte } = useAuth();
    const [annonce, setAnnonce] = useState(null);
    const [icon, setIcon] = useState("🤍");
    const userId = membreConnecte ? membreConnecte.codeUtilisateur : null;

    console.log('userId:', userId);
    if (membreConnecte) {
        console.log('membreConnecte.codeUtilisateur:', membreConnecte.codeUtilisateur);
    } else {
        console.log('membreConnecte is null');
    }
    console.log('numeroReference:', numeroReference);

    const toggleIcon = async () => {
        try {
            // Cherche les favoris du membre
            const response = await fetch(
                `http://localhost:5227/api/Favoris/RecupererFavorisParMembre?idMembre=${membreConnecte.codeUtilisateur}`
            );

            if (response.ok) {
                const favorites = await response.json();

                // Vérifie si l'annonce est déjà dans les favoris
                const isFavorite = favorites.some(favorites => favorites.codeAnnonceNumRef == numeroReference);

                if (isFavorite) {
                    // Si oui, cliquer sur le bouton la supprime
                    await fetch(
                        `http://localhost:5227/api/Favoris/SupprimerFavoris?CodeMembre=${membreConnecte.codeUtilisateur}&CodeAnnonce=${numeroReference}`,
                        {
                            method: 'DELETE',
                        }
                    );
                    setIcon("🤍");
                } else {
                    // Si non, on l'ajoute
                    await fetch(
                        `http://localhost:5227/api/Favoris/CreerFavoris?CodeMembre=${membreConnecte.codeUtilisateur}&CodeAnnonce=${numeroReference}`,
                        {
                            method: 'POST',
                        }
                    );
                    setIcon("🤎");
                }
            } else if (response.status === 404) {
                // Si l'usager n'a pas de favoris, on en crée
                await fetch(
                    `http://localhost:5227/api/Favoris/CreerFavoris?CodeMembre=${membreConnecte.codeUtilisateur}&CodeAnnonce=${numeroReference}`,
                    {
                        method: 'POST',
                    }
                );
                setIcon("🤎");
            } else {
                console.error("Erreur lors de la récupération des favoris");
            }
        } catch (error) {
            console.error("Erreur dans le toggle du favori:", error);
        }
    };

    const formatDate = (dateString) => {
        if (!dateString) return "Date non disponible";

        const options = {
            year: "numeric",
            month: "long",
            day: "numeric",
        };

        return new Date(dateString).toLocaleDateString("fr-FR", options);
    };

    const formatYear = (dateString) => {
        if (!dateString) return "Date non disponible";

        const options = {
            year: "numeric",
        };

        return new Date(dateString).toLocaleDateString("fr-FR", options);
    };

    useEffect(() => {
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

        // vérifie si l'annonce est déjà dans les favoris et change l'icone en fonction
        const checkFavorite = async () => {
            try {
                const response = await fetch(
                    `http://localhost:5227/api/Favoris/RecupererFavorisParMembre?idMembre=${membreConnecte.codeUtilisateur}`
                );

                if (response.ok) {
                    const favorites = await response.json();
                    const isFavorite = favorites.some(favorites => favorites.codeAnnonceNumRef == numeroReference);
                    if (isFavorite) {
                        setIcon("🤎");
                    }
                }
            } catch (error) {
                console.error("Erreur dans la vérification du favori:", error);
            }
        };

        checkFavorite();
    }, [numeroReference]);

    return (
        <section className="annonce wrapper">
            <div className="annonce-wrapper">
                {annonce ? (
                    <div className="annonce-details-wrapper">
                        <article className="entete-annonce">
                            <div className="entete-titre-annonce">
                                <h2>{annonce.titreLivre}</h2>
                                <p>{annonce.membreUsername}</p>
                                <p>
                                    {annonce.prix !== undefined ? (
                                        <span> {annonce.prix.toFixed(2)}$</span>
                                    ) : (
                                        <span> Prix non disponible</span>
                                    )}
                                </p>
                            </div>
                            <div className="entete-icon-annonce">
                                {membreConnecte ? (
                                    <button className="bouton-favori" onClick={toggleIcon} disabled={!membreConnecte}>
                                        {icon}
                                    </button>
                                ) : null}
                            </div>
                        </article>
                        <article className="details-annonce">
                            <picture className="wrapper-img-annonce"><img src={annonce.image_Url} alt={annonce.titreLivre} height="400" /></picture>
                            <div className="wrapper-details-annonce">
                                <p><span>ISBN -</span> {annonce.isbnlivre}</p>
                                <p><span>TITRE -</span> {annonce.titreLivre}</p>
                                <p><span>AUTEUR -</span> {annonce.auteur ? `${annonce.auteur.prenom} 
                                                            ${annonce.auteur.nom}` : "Auteur non disponible"}
                                </p>
                                <p><span>ANNÉE -</span> {formatYear(annonce.dateParutionLivre)}</p>
                                <p><span>GENRE -</span> {annonce.genres ? annonce.genres.map(genre => genre.description).join(", ") : "Genre non disponible"}</p>
                                <p><span>ÉTAT -</span> {annonce.etat ? annonce.etat.description : "État non disponible"}</p>
                                <p><em>Annonce publiée le {formatDate(annonce.datePublicationAnnonce)}</em></p>
                            </div>
                        </article>
                        {/* TODO: Boutons - À FAIRE */}
                        <article className="action-annonce">
                            <button>Contacter le vendeur</button>
                            <Link to={`/Noter/${annonce.numeroReference}`}>
                                <button>Noter l'annonce</button>
                            </Link>
                            <button>Signaler l'annonce</button>
                        </article>
                    </div>
                ) : (
                    <p>Un instant s'il vous plaît...</p>
                )}
            </div>
        </section>
    );
};

export default Annonce;
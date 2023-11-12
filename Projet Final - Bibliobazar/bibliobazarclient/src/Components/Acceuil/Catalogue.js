import React, { useState, useEffect } from "react";
import {Link, useLocation} from "react-router-dom"; // voir useNavigate dans Hero.js

const Catalogue = () => {
    const location = useLocation();
    const searchQuery = new URLSearchParams(location.search).get("searchQuery");
    const selectedEtat = new URLSearchParams(location.search).get("selectedEtat");
    const selectedGenre = new URLSearchParams(location.search).get("selectedGenre");
    const [annonces, setAnnonces] = useState([]);
    const [loading, setLoading] = useState(true);

    console.log("selectedGenre : ", selectedGenre);
    console.log("selectedEtat : ", selectedEtat);

    // Fonction pour récupérer les annonces filtrées
    // eslint-disable-next-line react-hooks/exhaustive-deps
    const fetchFilteredAnnonces = async () => {
        try {
            let url = `http://localhost:5227/api/Annonce/RechercherAnnonces?`;
    
            if (searchQuery) {
                url += `titre=${searchQuery}&`;
            }
    
            if (selectedGenre) {
                url += `genreId=${selectedGenre}&`;
            }
    
            if (selectedEtat) {
                url += `etatId=${selectedEtat}`;
            }
    
            const response = await fetch(url);
    
            if (!response.ok) {
                throw new Error("La réponse du serveur n'est pas bonne");
            }
    
            const data = await response.json();
            setAnnonces(data);
        } catch (error) {
            console.error("Erreur lors de la récupération des annonces:", error);
        }
    };    

    /*useEffect(() => {
        fetchFilteredAnnonces();
    }, [fetchFilteredAnnonces, searchQuery, selectedEtat, selectedGenre]);*/
    useEffect(() => {
        fetchFilteredAnnonces();
    }, [searchQuery, selectedEtat, selectedGenre]);

    return (
        <section className="wrapper">
            {annonces.length === 0 ? (
                <p>Aucune offre ne correspond à vos critères</p>
            ) : (
                <article className="catalogue-grid__container">
                    {annonces.map((annonce, index) => (
                        <div key={index} className="catalogue-grid__item">
                            <Link to={`/Annonce/${annonce.numeroReference}`} className="item-image__wrapper">
                                {annonce && (
                                    <img src={annonce.image_Url} alt={annonce.titreLivre} height="400" />
                                )}
                            </Link>
                            <article className="item-contenu__wrapper">
                                {annonce ? (
                                    <h3>{annonce.titreLivre}</h3>
                                ) : (
                                    <h3>Un instant s'il vous plait...</h3>
                                )}
                                <p>
                                    {annonce && annonce.prix !== undefined ? (
                                        <span>{annonce.prix.toFixed(2)}$</span>
                                    ) : (
                                        <span>Prix non disponible</span>
                                    )}
                                </p>
                                {annonce ? (
                                    <p>{annonce.etat.description}</p>
                                ) : (
                                    <p>Un instant s'il vous plait...</p>
                                )}
                            </article>
                        </div>
                    ))}
                </article>
            )}
        </section>
    );
};

export default Catalogue;
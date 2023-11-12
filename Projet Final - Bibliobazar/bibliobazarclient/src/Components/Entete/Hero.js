import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import imgHero from "../../Assets/img/img-hero.png";

const Hero = () => {
    const [searchQuery, setSearchQuery] = useState("");
    const [etats, setEtats] = useState([]);
    const [selectedEtat, setSelectedEtat] = useState("");
    const [genres, setGenres] = useState([]);
    const [selectedGenre, setSelectedGenre] = useState("");

    /*MÉTHODE DE LA BARRE DE RECHERCHE - À FAIRE*/
    const navigate = useNavigate();
    const handleSearch = (e) => {
        e.preventDefault();
        // Redirige vers le Catalogue avec le searchQuery comme paramètre de recherche
        navigate(`/Catalogue?searchQuery=${searchQuery}&selectedEtat=${selectedEtat}&selectedGenre=${selectedGenre}`);
      };

    /*MÉTHODE POUR LE MENU DES ÉTATS - À FAIRE*/
    // Récupère tous les états de la BDD
    useEffect(() => {
        fetch("http://localhost:5227/api/Etat/RecupererTousEtatsVisibles")
            .then((response) => response.json())
            .then((data) => {
                setEtats(data);
            })
            .catch((error) => {
                console.error("Error fetching visible Etats:", error);
                setEtats([]);
            });
    }, []);

    /*MÉTHODE POUR LE MENU DES GENRES - À FAIRE*/
    // Récupère tous les genres de la BDD
    useEffect(() => {
        fetch("http://localhost:5227/api/Genre/RecupererTousGenresVisibles")
            .then((response) => response.json())
            .then((data) => {
                setGenres(data);
            })
            .catch((error) => {
                console.error("Error fetching visible Genres:", error);
                setGenres([]);
            });
    }, []);

    return (
        <section className="header__hero">{/* eslint-disable-next-line jsx-a11y/img-redundant-alt */}
            <img src={imgHero} alt="Image hero montrant des livres" className="hero__image" />

            <article className="hero__contenu">
                <div>
                    <div className="barre-recherche">
                        {/* Barre de recherche */}
                        <form onSubmit={handleSearch} className="form-recherche">
                            <input
                                type="text"
                                placeholder="Rechercher des livres..."
                                value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                            />
                            <button type="submit" className="bouton-recherche">&#x1F50D;</button>
                        </form>

                        {/* Liste d'options de filtrage */}
                        <section className="options-filtre">
                            <p>Filtrer par : </p>
                            <div>
                                <select
                                    value={selectedEtat}
                                    onChange={(e) => setSelectedEtat(e.target.value)}
                                >
                                    <option value="">État du livre</option>
                                    {etats.map((etat) => (
                                        <option key={etat.idEtat} value={etat.idEtat}>
                                            {etat.description}
                                        </option>
                                    ))}
                                </select>

                                <select
                                    value={selectedGenre}
                                    onChange={(e) => setSelectedGenre(e.target.value)}
                                >
                                    <option value="">Genre du livre</option>
                                    {genres.map((genre) => (
                                        <option key={genre.idGenre} value={genre.idGenre}>
                                            {genre.description}
                                        </option>
                                    ))}
                                </select>
                            </div>
                        </section>
                    </div>
                </div>
            </article>
        </section>
    );
}
export default Hero;
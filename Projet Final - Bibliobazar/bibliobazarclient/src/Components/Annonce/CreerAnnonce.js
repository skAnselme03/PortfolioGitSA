import React, { useState, useEffect } from "react";
import { useAuth } from '../../AuthContext';
import {Link, NavLink} from "react-router-dom";

const CreerAnnonce = () => {
    const { estConnecter, membreConnecte } = useAuth();

    const [isbnLivre, setIsbnLivre] = useState("");
    const [titreLivre, setTitreLivre] = useState("");
    const [prix, setPrix] = useState(0);
    const [image_url, setImageUrl] = useState("");

    // Gestion de l'année... En vrai je me demande si ça vaut la peine
    const [dateParutionLivre, setDateParutionLivre] = useState("");
    const [year, setYear] = useState("");
    const generateYearOptions = () => {
        const currentYear = new Date().getFullYear();
        const startYear = -500;
        const years = [];

        for (let i = currentYear; i >= startYear; i--) {
            years.push(i.toString());
        }

        return years;
    };
    const yearOptions = generateYearOptions();
    const handleYearChange = (selectedYear) => {
        // Combine l'année sélectionnée avec un mois et jour par défaut
        const formattedDate = `${selectedYear}-01-01T00:00:00`;
        setDateParutionLivre(formattedDate);
        setYear(selectedYear);
        console.log("formattedDate: ", formattedDate);
        console.log("selectedYear: ", selectedYear);
    };

    const [codeEtatLivreId, setCodeEtatLivreId] = useState(0);
    const [codeGenreId, setCodeGenreId] = useState(0);
    const [codeAuteurId, setCodeAuteurId] = useState(0);

    const [etats, setEtats] = useState([]);
    const [genres, setGenres] = useState([]);
    const [auteurs, setAuteurs] = useState([]);

    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    // Pour vider le formulaire
    const resetForm = () => {
        setIsbnLivre("");
        setTitreLivre("");
        setPrix(0);
        setImageUrl("");
        setDateParutionLivre("");
        setYear("");
        setCodeEtatLivreId(0);
        setCodeGenreId(0);
        setCodeAuteurId(0);
        setSuccessMessage("");
        setErrorMessage("");
    };

    // Pour soumettre le formulaire
    const handleFormSubmit = async (e) => {
        e.preventDefault();

        if (!titreLivre) {
            setErrorMessage("Le titre du livre est requis.");
            return;
        }
        /* Faire pareil pour les autres sections */

        const formData = {
            isbnLivre,
            titreLivre,
            prix,
            image_url: image_url || "https://i.ibb.co/bQzbqJ2/no-image-icon-23483.png", // Valeur par défaut
            dateParutionLivre,
            statut: "Visible",
            codeEtatLivreId,
            codeGenreId,
            codeAuteurId,
            codeMembreUser: membreConnecte.codeUtilisateur,
        };

        console.log("ISBN: ", isbnLivre);
        console.log("Titre: ", titreLivre);
        console.log("Prix: ", prix);
        console.log("Image: ", image_url);
        console.log("Date: ", dateParutionLivre);
        console.log("Etat: ", codeEtatLivreId);
        console.log("Genre: ", codeGenreId);
        console.log("Auteur: ", codeAuteurId);
        console.log("Membre: ", membreConnecte.codeUtilisateur);

        const queryParameters = new URLSearchParams({
            isbnLivre: formData.isbnLivre,
            titreLivre: formData.titreLivre,
            prix: formData.prix,
            image_url: formData.image_url,
            dateParutionLivre: formData.dateParutionLivre,
            statut: formData.statut,
            codeMembreUser: formData.codeMembreUser,
            codeEtatLivreId: formData.codeEtatLivreId,
            codeGenreId: formData.codeGenreId,
            idAuteur: formData.codeAuteurId
        });

        try {
            const response = await fetch("http://localhost:5227/api/Annonce/CreerAnnonce?" + queryParameters.toString(), {
                method: 'POST',
            });

            if (response.ok) {
                resetForm(); // Vide le formulaire
                setErrorMessage(""); // Nettoie le précédent message d'erreur, s'il y a lieu
                setSuccessMessage("Annonce créée avec succès.");
            } else {
                setSuccessMessage(""); // Nettoie le précédent message de succès, s'il y a lieu
                setErrorMessage("Erreur lors de la création de l'annonce.");
            }
        } catch (error) {
            console.error("Error:", error);
            setSuccessMessage(""); // Nettoie le précédent message de succès, s'il y a lieu
            setErrorMessage("Erreur lors de la création de l'annonce.");
        }
    };

    // Récupère tous les états et genres et auteurs de la BDD
    useEffect(() => {
        // États
        fetch("http://localhost:5227/api/Etat/RecupererTousEtatsVisibles")
            .then((response) => response.json())
            .then((data) => {
                setEtats(data);
            })
            .catch((error) => {
                console.error("Error fetching visible Etats:", error);
                setEtats([]);
            });

        // Genres
        fetch("http://localhost:5227/api/Genre/RecupererTousGenresVisibles")
            .then((response) => response.json())
            .then((data) => {
                setGenres(data);
            })
            .catch((error) => {
                console.error("Error fetching visible Genres:", error);
                setGenres([]);
            });

        // Auteurs
        fetch("http://localhost:5227/api/Auteur/RecupererTousAuteursVisibles")
            .then((response) => response.json())
            .then((data) => {
                setAuteurs(data);
            })
            .catch((error) => {
                console.error("Error fetching visible Auteurs:", error);
                setAuteurs([]);
            });

        // Débogage
        console.log("Etats:", etats);
        console.log("Genres:", genres);
        console.log("Auteurs:", auteurs);
    }, []);

    return (
        <section>
            {estConnecter && membreConnecte ? (
                <div className="creer-une-annonce wrapper">
                    <div>
                        <h2>Créer une nouvelle annonce</h2>
                        <p>Vous êtes connecté·e en tant que <NavLink to='/Profil'><em><u><strong>{membreConnecte.username}</strong></u></em></NavLink></p>
                        <p>Veuillez indiquer toutes les informations concernant votre livre.
                            <em>(Tout les champs sont obligatoires)</em></p>
                    </div>
                    <form onSubmit={handleFormSubmit}>
                        <fieldset>
                            <input
                                type="text"
                                value={titreLivre}
                                placeholder="Titre"
                                onChange={(e) =>
                                    setTitreLivre(e.target.value)}
                                required
                            />
                            <input
                                type="text"
                                value={isbnLivre}
                                placeholder="ISBN"
                                onChange={(e) =>
                                    setIsbnLivre(e.target.value)}
                                required
                            />

                            <input
                                type="text"
                                value={image_url}
                                placeholder="URL de votre image (svp utilisez un hébergeur)"
                                onChange={(e) =>
                                    setImageUrl(e.target.value)}
                            />
                            <section className="creer-une-annonce__prix-annee">
                                <input
                                    type="number"
                                    value={prix}
                                    placeholder="Prix du livre"
                                    onChange={(e) =>
                                        setPrix(e.target.value)}
                                    required
                                />
                                <select
                                    value={year}
                                    onChange={(e) =>
                                        handleYearChange(e.target.value)}
                                >
                                    <option value="">Année de parution</option>
                                    {yearOptions.map((year) => (
                                        <option key={year} value={year}>
                                            {year}
                                        </option>
                                    ))}
                                </select>

                            </section>

                            <section className="creer-une-annonce__auteur">
                                <select
                                    value={codeAuteurId}
                                    onChange={(e) => setCodeAuteurId(e.target.value)}
                                    required
                                >
                                    <option value="">Auteur</option>
                                    {auteurs.map((auteur) => (
                                        <option key={auteur.idAuteur} value={auteur.idAuteur}>
                                            {auteur.nom}, {auteur.prenom}
                                        </option>
                                    ))}
                                </select>
                                <Link to={`/CreerAuteur`} className="ajouter-un-auteur__lien"><strong>Ajouter un auteur</strong></Link>
                            </section>
                            <section className="creer-une-annonce__filtre">
                                <select
                                    value={codeGenreId}
                                    onChange={(e) => setCodeGenreId(e.target.value)}
                                    required
                                >
                                    <option value="">Genre</option>
                                    {genres.map((genre) => (
                                        <option key={genre.idGenre} value={genre.idGenre}>
                                            {genre.description}
                                        </option>
                                    ))}
                                </select>
                                <select
                                    value={codeEtatLivreId}
                                    onChange={(e) => setCodeEtatLivreId(e.target.value)}
                                    required
                                >
                                    <option value="">État du livre</option>
                                    {etats.map((etat) => (
                                        <option key={etat.idEtat} value={etat.idEtat}>
                                            {etat.description}
                                        </option>
                                    ))}
                                </select>

                            </section>
                        </fieldset>
                        <button type="submit">Créer Annonce</button>
                    </form>
                    {successMessage && <p>{successMessage}</p>}
                    {errorMessage && <p>{errorMessage}</p>}
                </div>
            ) : (
                <p>Veuillez vous connecter pour accéder à cette page</p>
            )}
        </section>
    );
};

export default CreerAnnonce;
import React, { useState, useEffect } from "react";
import {NavLink, useParams} from "react-router-dom";
import { useAuth } from '../../AuthContext';
import { Link, useNavigate } from "react-router-dom";

const ModifierAnnonce = () => {
    const navigate = useNavigate();
    const { numeroReference } = useParams();
    const [editMode, setEditMode] = useState(false);
    const { estConnecter, membreConnecte } = useAuth();
    const [annonce, setAnnonce] = useState([]);
    const userId = membreConnecte ? membreConnecte.codeUtilisateur : null;

    // Section du formulaire
    const [isbnLivre, setIsbnLivre] = useState("");
    const [titreLivre, setTitreLivre] = useState("");
    const [prix, setPrix] = useState(0);
    const [image_url, setImageUrl] = useState("");
    // Tout le délire de l'année
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
        const formattedDate = `${selectedYear}-01-01`;
        setDateParutionLivre(formattedDate);
        setYear(selectedYear);
        console.log("formattedDate: ", formattedDate);
        console.log("selectedYear: ", selectedYear);
    };
    const [codeEtatLivreId, setCodeEtatLivreId] = useState(0);
    const [codeGenreId, setCodeGenreId] = useState(0);
    const [codeAuteurId, setCodeAuteurId] = useState(0);
    // Fin de la section formulaire

    const [etats, setEtats] = useState([]);
    const [genres, setGenres] = useState([]);
    const [auteurs, setAuteurs] = useState([]);

    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    console.log('userId:', userId);
    if (membreConnecte) {
        console.log('membreConnecte.codeUtilisateur:', membreConnecte.codeUtilisateur);
        console.log("annonces:", annonce);
        console.log("Code utilisateur de l'annonce :", annonce.codeMembreUser);
    } else {
        console.log('membreConnecte is null');
    }
    console.log('numeroReference:', numeroReference);

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
        // Récupère tous les états et genres et auteurs de la BDD
        // États
        fetch("http://localhost:5227/api/Etat/RecupererTousEtatsVisibles")
            .then((response) => response.json())
            .then((data) => {
                setEtats(data);
                console.log("États :", etats);
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
                console.log("Genres :", genres);
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
                console.log("Auteurs :", auteurs);
            })
            .catch((error) => {
                console.error("Error fetching visible Auteurs:", error);
                setAuteurs([]);
            });

        // Récupère les valeurs déjà sélectionnées pour l'annonce
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
                        console.log("API Response Data:", data);

                        setIsbnLivre(data[0].isbnlivre);
                        setTitreLivre(data[0].titreLivre);
                        setPrix(data[0].prix);
                        setImageUrl(data[0].image_Url);
                        setDateParutionLivre(data[0].dateParutionLivre.substring(0, 4));
                        setCodeEtatLivreId(data[0].codeEtatLivre.idEtat);
                        setCodeGenreId(data[0].genres[0].codeGenreId);
                        setCodeAuteurId(data[0].auteur.idAuteur);
                    }
                    console.log("Genre : ", data[0].genres.codeGenreId);
                } catch (error) {
                    console.error("Erreur lors de la récupération de l'annonce:", error);
                }
            };

            fetchAnnonce();
        }
    }, [numeroReference]);

    const toggleEditMode = () => {
        setEditMode(!editMode);
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        const formData = {
            isbnLivre,
            titreLivre,
            prix,
            image_url,
            dateParutionLivre: `${year}-01-01`,
            statut: "Visible",
            codeEtatLivreId,
            codeGenreId,
            codeAuteurId,
            codeMembreUser: membreConnecte.codeUtilisateur,
        };

        // Prepare la requête qui sera envoyée à la méthode UpdateAnnonce
        const queryParameters = new URLSearchParams({
            numeroReference: annonce.numeroReference,
            isbnLivre: formData.isbnLivre,
            titreLivre: formData.titreLivre,
            prix: formData.prix,
            image_url: formData.image_url,
            dateParutionLivre: formData.dateParutionLivre,
            statut: formData.statut,
            codeEtatLivreId: formData.codeEtatLivreId,
            codeGenreId: formData.codeGenreId,
            idAuteur: formData.codeAuteurId,
            codeMembreUser: formData.codeMembreUser
        });
        console.log("API request :", queryParameters);

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

        try {
            const response = await fetch("http://localhost:5227/api/Annonce/ModifierAnnonce?" + queryParameters.toString(), {
                method: 'POST',
            });

            if (response.ok) {
                setErrorMessage(""); // Nettoie le précédent message d'erreur, s'il y a lieu
                setSuccessMessage("Annonce modifiée avec succès.");
                fetchAnnonce();
            } else {
                setSuccessMessage(""); // Nettoie le précédent message de succès, s'il y a lieu
                setErrorMessage("Erreur lors de la modification de l'annonce.");
            }
        } catch (error) {
            console.error("Error:", error);
            setSuccessMessage(""); // Nettoie le précédent message de succès, s'il y a lieu
            setErrorMessage("Erreur lors de la modification de l'annonce.");
        }
        setEditMode(false);
        navigate(`/ModifierAnnonce/${annonce.numeroReference}`);
    };

    // Fonction pour archiver l'annonce
    const handleArchiveButtonClick = async () => {
        const isConfirmed = window.confirm("Êtes-vous sûr de vouloir archiver cette annonce? Cette action est irréversible.");

        if (isConfirmed) {
            try {
                const response = await fetch(`http://localhost:5227/api/Annonce/ModifierStatutAnnonce?numeroReference=${annonce.numeroReference}&statut=Archivé`, {
                    method: 'POST',
                });

                if (response.ok) {
                    console.log('Annonce archivée avec succès.');
                    navigate(`/Profil`);
                } else {
                    console.error('Erreur lors de l\'archivage de l\'annonce.');
                }
            } catch (error) {
                console.error('Erreur:', error);
            }
        }
    };


    return (
        <section>
            {estConnecter && membreConnecte ? (
                <>
                    {annonce ? ( // Check if annonce is not null
                        <>
                            {annonce.codeMembreUser == membreConnecte.codeUtilisateur ? (
                                <>
                                    {annonce.statut == "Visible" ? (
                                        <div>
                                            {editMode ? (
                                                <section className="creer-une-annonce wrapper">
                                                    <div>
                                                        <h2>Modifier votre annonce</h2>
                                                        <p>Vous êtes connecté·e en tant que 
                                                            <NavLink to='/Profil'>
                                                                <em><u><strong>{membreConnecte.username}</strong>
                                                                </u></em></NavLink></p>
                                                        <p><em>Veuillez indiquer les informations à modifier</em></p>
                                                    </div>

                                                    {successMessage && <p>{successMessage}</p>}
                                                    {errorMessage && <p>{errorMessage}</p>}
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
                                                                onChange={(e) => setImageUrl(e.target.value)}
                                                                required
                                                            />
                                                            <section className="creer-une-annonce__prix-annee">
                                                                <input
                                                                    type="number"
                                                                    value={prix}
                                                                    placeholder="Prix"
                                                                    onChange={(e) =>
                                                                        setPrix(e.target.value)}
                                                                    required
                                                                />
                                                                <select
                                                                    value={dateParutionLivre.substring(0, 4)}
                                                                    onChange={(e) =>
                                                                        handleYearChange(e.target.value)}
                                                                >
                                                                    <option value="">Année</option>
                                                                    {yearOptions.map((year) => (
                                                                        <option key={year} value={year} selected={year == dateParutionLivre}>
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
                                                                        <option key={auteur.idAuteur} value={auteur.idAuteur} selected={auteur.idAuteur == codeAuteurId}>
                                                                            {auteur.nom}, {auteur.prenom}
                                                                        </option>
                                                                    ))}
                                                                </select>
                                                                <Link to={`/CreerAuteur`} className="ajouter-un-auteur__lien"><strong>Ajouter un auteur</strong></Link>
                                                            </section>

                                                            <section className="creer-une-annonce__filtre">
                                                                <select
                                                                    value={codeEtatLivreId}
                                                                    onChange={(e) => setCodeEtatLivreId(e.target.value)}
                                                                    required
                                                                >
                                                                    <option value="">État du livre</option>
                                                                    {etats.map((etat) => (
                                                                        <option key={etat.idEtat} value={etat.idEtat} selected={etat.idEtat == codeEtatLivreId}>
                                                                            {etat.description}
                                                                        </option>
                                                                    ))}
                                                                </select>
                                                                <select
                                                                    value={codeGenreId}
                                                                    onChange={(e) => setCodeGenreId(e.target.value)}
                                                                    required
                                                                >
                                                                    <option value="">Genre</option>
                                                                    {genres.map((genre) => (
                                                                        <option key={genre.idGenre} value={genre.idGenre} selected={genre.idGenre == codeGenreId}>
                                                                            {genre.description}
                                                                        </option>
                                                                    ))}
                                                                </select>
                                                            </section>
                                                        </fieldset>
                                                        <button type="submit">Enregistrer</button>
                                                    </form>
                                                </section>
                                            ) : (
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
                                                            </article>
                                                            <article className="details-annonce">
                                                                <picture className="wrapper-img-annonce"><img src={annonce.image_Url} alt={annonce.titreLivre} height="400" /></picture>
                                                                <div className="wrapper-details-annonce">
                                                                    <p><span>ISBN -</span> {annonce.isbnlivre}</p>
                                                                    <p><span>TITRE -</span> {annonce.titreLivre}</p>
                                                                    <p><span>AUTEUR -</span> {annonce.auteur ? `${annonce.auteur.prenom} ${annonce.auteur.nom}` : "Auteur non disponible"}</p>
                                                                    <p><span>ANNÉE -</span> {formatYear(annonce.dateParutionLivre)}</p>
                                                                    <p><span>GENRE -</span> {annonce.genres ? annonce.genres.map(genre => genre.description).join(", ") : "Genre non disponible"}</p>
                                                                    <p><span>ÉTAT -</span> {annonce.etat ? annonce.etat.description : "État non disponible"}</p>
                                                                    <p><em>Annonce publiée le {formatDate(annonce.datePublicationAnnonce)}</em></p>
                                                                </div>
                                                            </article>
                                                            {/* TODO: Boutons - À FAIRE */}
                                                            <article className="action-annonce">
                                                                <button onClick={toggleEditMode}>Modifier l'annonce</button>
                                                                <button onClick={handleArchiveButtonClick}>Archiver l'annonce (ACTION IRRÉVERSIBLE)</button>
                                                            </article>
                                                        </div>
                                                    ) : (
                                                        <p>Chargement en cours...</p>
                                                    )}
                                                </div>
                                            )}
                                        </div>
                                    ) : (
                                        <>
                                            <h1><center>⚠ Cette annonce a été archivée ⚠</center></h1>
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
                                                        </div>
                                                    ) : (
                                                        <p>Un instant s'il vous plaît...</p>
                                                    )}
                                                </div>
                                            </section>
                                        </>
                                    )}
                                </>
                            ) : (
                                <p>Vous n'êtes pas autorisé à consulter cette page</p>
                            )}
                        </>
                    ) : (
                        <p>Cette annonce n'a pas pu être trouvée. Veuillez vérifier le numéro de référence.</p>
                        // Annonce == null
                    )}
                </>
            ) : (
                <p>Veuillez vous connecter pour accéder à cette page</p>
            )}
        </section>
    );
};

export default ModifierAnnonce;
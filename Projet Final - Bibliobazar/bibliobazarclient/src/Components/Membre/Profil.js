import React, { useState, useEffect } from "react";
import { useAuth } from '../../AuthContext';
import { Link, useNavigate } from "react-router-dom";
import userIcon from "../../Assets/img/img-profil-defaut.png"

const Profil = () => {
    const navigate = useNavigate();
    const { estConnecter, membreConnecte, logout } = useAuth();
    const [annonces, setAnnonces] = useState([]);
    const [archives, setArchives] = useState([]);
    const [favoris, setFavoris] = useState([]);
    const [formValues, setFormValues] = useState({
        nom: membreConnecte.nom,
        prenom: membreConnecte.prenom,
        telephone: membreConnecte.telephone,
        motDePasse: membreConnecte.motDePasse,
        confirmMotDePasse: membreConnecte.motDePasse,
    });
    const [errorMessage, setErrorMessage] = useState(null);
    const [afficherProfil, setAfficherProfil] = useState(false);


    useEffect(() => {
        if (estConnecter && membreConnecte) {
            // Fetch member's announcements
            getAnnoncesVisibles(membreConnecte.codeUtilisateur);
            getAnnoncesArchives(membreConnecte.codeUtilisateur);
            getFavoris(membreConnecte.codeUtilisateur);
        }
    }, [estConnecter, membreConnecte]);

    // Récupérer les annonces visibles
    const getAnnoncesVisibles = async (idMembre) => {
        try {
            const response = await fetch(
                `http://localhost:5227/api/Annonce/RecupererAnnoncesVisiblesParMembre?idMembre=${idMembre}`
            );

            if (!response.ok) {
                throw new Error("La réponse du serveur n'est pas bonne");
            }

            const data = await response.json();
            setAnnonces(data);
        } catch (error) {
            console.error("Erreur lors de la récupération des annonces:", error);
        }
    };

    // Récupérer les annonces archivées
    const getAnnoncesArchives = async (idMembre) => {
        try {
            const response = await fetch(
                `http://localhost:5227/api/Annonce/RecupererAnnoncesArchivesParMembre?idMembre=${idMembre}`
            );

            if (!response.ok) {
                throw new Error("La réponse du serveur n'est pas bonne");
            }

            const data = await response.json();
            setArchives(data);
        } catch (error) {
            console.error("Erreur lors de la récupération des annonces:", error);
        }
    };

    // Récupérer les favoris
    const getFavoris = async (idMembre) => {
        try {
            const response = await fetch(
                `http://localhost:5227/api/Favoris/RecupererFavorisParMembre?idMembre=${idMembre}`
            );

            if (!response.ok) {
                throw new Error("La réponse du serveur n'est pas bonne");
            }

            const data = await response.json();
            setFavoris(data);
        } catch (error) {
            console.error("Erreur lors de la récupération des favoris:", error);
        }
    };

    console.log("estConnecter:", estConnecter);
    console.log("membreConnecte:", membreConnecte);

    // Modifier les informations du membre
    const handleFormSubmit = async (e) => {
        e.preventDefault();
        if (formValues.motDePasse !== formValues.confirmMotDePasse) {
            setErrorMessage("Les mots de passe ne correspondent pas.");
            return;
        }

        try {
            const queryParams = new URLSearchParams({
                codeUtilisateur: membreConnecte.codeUtilisateur,
                nom: formValues.nom,
                prenom: formValues.prenom,
                courriel: membreConnecte.courriel,
                telephone: formValues.telephone,
                motDePasse: formValues.motDePasse,
            });

            const response = await fetch(
                `http://localhost:5227/api/Membre/MettreAJourMembre?${queryParams.toString()}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
            }
            );

            if (response.ok) {
                setErrorMessage("Votre profil a bien été mis à jour.");
            } else {
                setErrorMessage("Erreur lors de la mise à jour du profil.");
            }
        } catch (error) {
            console.error("Erreur:", error);
            setErrorMessage("Erreur lors de la mise à jour du profil.");
        }

    };

    // Archiver le compte membre (ou le supprime)
    const handleArchiveButtonClick = async () => {
        const isConfirmed = window.confirm("Êtes-vous absolument certain·e de vouloir supprimer votre compte? Cette action est irréversible.");

        if (isConfirmed) {
            const codeUtilisateur = membreConnecte.codeUtilisateur;
            try {
                const queryParams = new URLSearchParams({
                    codeUtilisateur,
                    statut: 'Inactif',
                });

                const response = await fetch(
                    `http://localhost:5227/api/Membre/ModifierStatutMembre?${queryParams.toString()}`, {
                    method: 'POST',
                });

                if (response.ok) {
                    setErrorMessage("Votre compte a été supprimé.");
                    navigate('/');
                    logout();
                    window.location.reload(); // Rafraîchir la page permet de se déconnecter...
                } else {
                    setErrorMessage("Erreur lors de la suppression du compte.");
                }
            } catch (error) {
                console.error("Erreur:", error);
                setErrorMessage("Erreur lors de la suppression du compte.");
            }
        }
    };

    const toggleProfil = () => {
        setAfficherProfil(!afficherProfil);
    };

    return (
        <section>
            {estConnecter && membreConnecte ? (

                <div className="profil">
                    <article className="profil__details">
                        <div className="profil__entete">
                            <h2 className="profile-entet__titre">Compte de
                                <em><strong> {membreConnecte.username}</strong></em></h2>
                        </div>
                        <div className="profil__details-corps">
                            <button onClick={toggleProfil}>
                                {afficherProfil ? "▼" : "Afficher profil ▲"}
                            </button>
                            <section
                                id="profil__masquer"
                                className={`mon-profil_wrapper ${
                                    afficherProfil ? "montrer-profil" : "mon-profil"
                                }`}
                                onClick={toggleProfil} >
                                {/*Empêcher la propagation du togle lorsqu'on veux modifier le formulaire*/}
                                <div onClick={(e) => e.stopPropagation()}>
                                    <picture className="photo-profil">
                                        {/* eslint-disable-next-line jsx-a11y/img-redundant-alt */}
                                        <img src={userIcon} alt="Photo de profil" id="photoProfilImg" />
                                    </picture>
                                    <section>
                                        <label htmlFor="nomUtilisateur" hidden="hidden">Nom d'utilisateur</label>
                                        <input type="text" id="nomUtilisateur" name="nomUtilisateur"
                                               placeholder="Nom d'utilisateur" value={membreConnecte.username} disabled/>
                                        <label htmlFor="membre" hidden="hidden">Membre</label>
                                        <input type="text" id="membre" name="membre" placeholder="Membre" value="Membre" disabled/>
                                    </section>
                                </div>
                                <form id="formProfil" name="formProfil" className="form-profil" onClick={(e) => e.stopPropagation()}>
                                    <fieldset>
                                        <section>
                                            <label htmlFor="nom">Nom</label>
                                            <input type="text" id="nom" name="nom" placeholder="Nom"
                                                   value={formValues.nom}
                                                   onChange={(e) =>
                                                       setFormValues({ ...formValues, nom: e.target.value })} />
                                            <label htmlFor="prenom">Prénom</label>
                                            <input type="text" id="prenom" name="prenom" placeholder="Prénom"
                                                   value={formValues.prenom}
                                                   onChange={(e) =>
                                                       setFormValues({ ...formValues, prenom: e.target.value })} />
                                        </section>
                                        <section>
                                            <label htmlFor="telephone">Téléphone</label>
                                            <input type="tel" id="telephone" name="telephone"
                                                   placeholder="Téléphone"
                                                   value={formValues.telephone}
                                                   onChange={(e) =>
                                                       setFormValues({ ...formValues, telephone: e.target.value })} />
                                            <label htmlFor="courriel">Courriel</label>
                                            <input type="email" name="courriel" id="courriel"
                                                   placeholder="Courriel" aria-label="Un courriel quelconque"
                                                   value={membreConnecte.courriel}
                                                   disabled/>
                                        </section>
                                        <section>
                                            <label htmlFor="motDePasse">Mot de passe</label>
                                            <input type="password" name="motDePasse" id="motDePasse"
                                                   placeholder="Nouveau mot de passe" aria-label="Un mot de passe quelconque"
                                                   value={formValues.motDePasse}
                                                   onChange={(e) =>
                                                       setFormValues({ ...formValues, motDePasse: e.target.value })} />
                                            <label htmlFor="confirmMotDePasse">Mot de passe</label>
                                            <input type="password" name="confirmMotDePasse" id="confirmMotDePasse"
                                                   placeholder="Confirmer le nouveau mot de passe"
                                                   aria-label="Un mot de passe quelconque confirmer"
                                                   value={formValues.confirmMotDePasse}
                                                   onChange={(e) =>
                                                       setFormValues({ ...formValues, confirmMotDePasse: e.target.value })} />

                                        </section>
                                    </fieldset>
                                    {errorMessage && <p>{errorMessage}</p>}
                                    <section id="modifProfil" className="modify-profil">
                                        <button type="reset" className="delete-compte delete-user-btn"
                                                name="supprimer" onClick={handleArchiveButtonClick}>Supprimer compte</button>
                                        <button id="submit" className="modifier_profil" name="modifier"
                                                onClick={handleFormSubmit}>Modifier</button>
                                    </section>
                                </form>
                            </section>
                        </div>
                    </article>
                    <section className="profil-annonces">
                        <div>
                            <article className="mes-annonces">
                                {annonces.length > 0 ? (
                                    <div className="mes-annonces__wrapper">
                                        <h2>Mes annonces</h2>
                                        <article className="mes-annonces__details">
                                            {annonces.map((annonce) => (
                                                <div key={annonce.numeroReference} className="">
                                                    <Link to={`/ModifierAnnonce/${annonce.numeroReference}`}>
                                                        {annonce && (
                                                            <img src={annonce.image_Url} alt={annonce.titreLivre}
                                                                 height="200px" className="" />
                                                        )}
                                                    </Link>

                                                    <div>
                                                        <Link to={`/ModifierAnnonce/${annonce.numeroReference}`}>
                                                            <strong>{annonce.titreLivre}</strong>
                                                            <span><em>[{annonce.statut}]</em></span>
                                                        </Link>
                                                        <p>{annonce.prix.toFixed(2)}$</p>
                                                        <p>{annonce.codeEtatLivre.description}</p>
                                                    </div>

                                                </div>
                                            ))}
                                        </article>
                                    </div>
                                ) : (
                                    <p>Vous n'avez pas encore d'annonces.</p>
                                )}
                            </article>
                            <article className="mes-archives">
                                {archives.length > 0 ? (
                                    <div className="mes-annonces__wrapper">
                                        <h2>Vos annonces archivées</h2>
                                        <article className="mes-annonces__details">
                                            {archives.map((archive) => (
                                                <div key={archive.numeroReference} className="">
                                                    <Link to={`/ModifierAnnonce/${archive.numeroReference}`}>
                                                        {archive && (
                                                            <img src={archive.image_Url} alt={archive.titreLivre}
                                                                 height="200px" className="" />
                                                        )}
                                                    </Link>

                                                    <div className="item-contenu__wrapper">
                                                        <Link to={`/ModifierAnnonce/${archive.numeroReference}`}>
                                                            <strong>{archive.titreLivre}</strong>
                                                            [{archive.statut}]
                                                        </Link>

                                                        <p>{archive.prix.toFixed(2)}$</p>
                                                        <p>{archive.codeEtatLivre.description}</p>
                                                    </div>
                                                </div>
                                            ))}
                                        </article>
                                    </div>
                                ) : (
                                    <p>Vous n'avez pas encore archivé d'annonces.</p>
                                )}
                            </article>
                        </div>
                        <div>
                            <article className="mes-favoris aside-favoris">
                                {favoris.length > 0 ? (
                                    <div className="mes-annonces__wrapper">
                                        <h2>Vos favoris</h2>
                                        <article className="mes-annonces__details">
                                            {favoris.map((favori) => (
                                                <div key={favori.CodeAnnonceNumRef} className="">
                                                    <Link to={`/Annonce/${favori.codeAnnonceNumRef}`}>
                                                        {favori && (
                                                            <img src={favori.annonceImageUrl}
                                                                 alt={favori.annonceTitreLivre}
                                                                 height="200" className="" />
                                                        )}
                                                    </Link>

                                                    <div>
                                                        <Link to={`/Annonce/${favori.codeAnnonceNumRef}`}>
                                                            <strong>{favori.annonceTitreLivre}</strong>
                                                            <span><em>[{favori.annonceStatut}]</em></span>
                                                        </Link>
                                                        <p>{favori.annoncePrix.toFixed(2)}$</p>
                                                        <p>{favori.etatLivreDescription}</p>
                                                    </div>

                                                </div>
                                            ))}
                                        </article>
                                    </div>
                                ) : (
                                    <p>Vous n'avez pas encore de favoris.</p>
                                )}
                            </article>
                        </div>
                    </section>
                </div>
            ) : (
                <p>Veuillez vous connecter pour accéder à cette page.</p>
            )}
        </section>
    );

};

export default Profil;
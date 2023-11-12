import React, { useState } from 'react';
import {NavLink, useNavigate} from 'react-router-dom';
import ValidationEmail from "../../../Assets/scripts/validationFormulaire";

const CreerUnCompte = () =>{

    const navigate = useNavigate();

    // Utilisation de l'état local (useState) pour gérer les données du formulaire
    const [formData, setFormData] = useState({
        nomUtilisateur: '',
        courriel: '',
        motDePasse: '',
        confirmMotDePasse: '',
        telephone: '',
        conditionGeneral: false,
        statut: 'Actif',
        nom: '',
        prenom: '',
    });


    // Utilisation de l'état local (useState) pour gérer les erreurs de validation
    const [validationErreurs, setValidationErreurs] = useState({});

    // Gestionnaire de changement de champ de formulaire
    const gestionnaireChangement = (e) => {
        const { name, value, type, checked } = e.target;
        // Gère correctement la valeur en fonction du type de champ (input ou checkbox)
        const inputValue = type === 'checkbox' ? checked : value;
        setFormData({
            ...formData,
            [name]: inputValue,
        });
    };

    // Gestionnaire de soumission du formulaire
    const gestionnaireSoumission = async (e) => {
        e.preventDefault();

        // Valider le formulaire avant de le soumettre
        const erreurs = {};

        if (!formData.nom) {
            erreurs.nom = 'Le nom est requis';
        }

        if (!formData.prenom) {
            erreurs.prenom = 'Le prénom est requis';
        }

        if (!formData.nomUtilisateur) {
            erreurs.nomUtilisateur = 'Le nom d\'utilisateur est requis';
        }

        if (!formData.courriel) {
            erreurs.courriel = 'Un courriel est requis';
        } else if (!ValidationEmail(formData.courriel)) {
            erreurs.courriel = 'Le format du courriel n\'est pas adéquat (ex: moncourriel@monadresse.xx)';
        }

        if (!formData.motDePasse) {
            erreurs.motDePasse = 'Un mot de passe est requis';
        }

        if (!formData.confirmMotDePasse) {
            erreurs.confirmMotDePasse = 'Confirmer votre mot de passe est requis';
        } else if (formData.confirmMotDePasse !== formData.motDePasse) {
            erreurs.confirmMotDePasse = 'Le mot de passe et sa confirmation doivent être identiques';
        }

        if (!formData.conditionGeneral) {
            erreurs.conditionGeneral = 'Vous devez accepter les conditions d\'utilisation';
        }

        setValidationErreurs(erreurs);
        const hasErrors = Object.keys(erreurs).length > 0; // vérifie les erreurs de validation

        if (!hasErrors) {
            //Si pas d'erreur, preparer les données
            // et faire la requête POST
            const queryParameters = new URLSearchParams({
                username: formData.nomUtilisateur,
                motDePasse: formData.motDePasse,
                courriel: formData.courriel,
                telephone: formData.telephone,
                statut: formData.statut,
                nom: formData.nom,
                prenom: formData.prenom,
            });

            try {
                const response = await fetch('http://localhost:5227/api/Membre/CreerMembre?' + queryParameters.toString(), {
                    method: 'POST',
                });

                if (response.status === 201) {
                    //TODO: À ENLEVER À LA FIN
                    console.log('Membre créé avec succès.');
                    //TODO: FIN

                    //Route à la page SeConnecter
                    navigate("/seConnecter?compteEstCreer=true");
                } else {
                    console.log('Erreur lors de la création du membre.');
                    //Rester sur la page page CreerUnCompte
                }
            } catch (error) {
                console.error('Erreur lors de la création du membre:', error);
                //Route à la page404
                navigate("/page404");
            }
        }
    };

    return(
        <section>
            <div className="creeruncompte wrapper">
                <div>
                    <h2>Créer un compte</h2>
                    <p>Veuillez entrer vos identifiants</p>
                </div>
                <form onSubmit={gestionnaireSoumission} name="creerUnCompte">
                    <fieldset>
                        <section>
                            <label htmlFor="nom">Nom</label>
                            <input type="text" name="nom" id="nom"
                                   placeholder="Nom" aria-label="Un nom quelconque"
                                   required
                                   value={formData.nom}
                                   onChange={gestionnaireChangement} />
                            {validationErreurs.nom &&
                                <p className="error">{validationErreurs.nom}</p>}

                            <label htmlFor="prenom">Prénom </label>
                            <input type="text" name="prenom" id="prenom"
                                   placeholder="Prénom" aria-label="Un prenom quelconque"
                                   required
                                   value={formData.prenom}
                                   onChange={gestionnaireChangement} />
                            {validationErreurs.prenom &&
                                <p className="error">{validationErreurs.prenom}</p>}
                        </section>

                        <section>
                            <label htmlFor="nomUtilisateur">Nom d'utilisateur</label>
                            <input type="text" name="nomUtilisateur" id="nomUtilisateur"
                                   placeholder="Nom d'utilisateur" aria-label="Un nom d'utilisateur quelconque"
                                   required
                                   value={formData.nomUtilisateur}
                                   onChange={gestionnaireChangement} />
                            {validationErreurs.nomUtilisateur &&
                                <p className="error">{validationErreurs.nomUtilisateur}</p>}

                            <label htmlFor="courriel">Courriel</label>
                            <input type="email" name="courriel" id="courriel"
                                   placeholder="Courriel" aria-label="un courriel quelconque"
                                   required
                                   value={formData.courriel}
                                   onChange={gestionnaireChangement} />
                            {validationErreurs.courriel &&
                                <p className="error">{validationErreurs.courriel}</p>}
                        </section>

                        <section>
                            <label htmlFor="motDePasse">Mot de passe</label>
                            <input type="password" name="motDePasse" id="motDePasse"
                                   placeholder="Mot de passe" aria-label="un motDePasse quelconque"
                                   required
                                   value={formData.motDePasse}
                                   onChange={gestionnaireChangement} />
                            {validationErreurs.motDePasse &&
                                <p className="error">{validationErreurs.motDePasse}</p>}

                            <label htmlFor="confirmMotDePasse">Mot de passe</label>
                            <input type="password" name="confirmMotDePasse" id="confirmMotDePasse"
                                   placeholder="Confirmer le mot de passe" aria-label="un motDePasse quelconque confirmer"
                                   required
                                   value={formData.confirmMotDePasse}
                                   onChange={gestionnaireChangement} />
                            {validationErreurs.confirmMotDePasse &&
                                <p className="error">{validationErreurs.confirmMotDePasse}</p>}
                        </section>

                            <label htmlFor="telephone">Téléphone</label>
                            <input type="tel" name="telephone" id="telephone"
                                   placeholder="Téléphone (optionnel)" aria-label="un numéro de téléphone quelconque"
                                   value={formData.telephone}
                                   onChange={gestionnaireChangement}
                            />
                            <article className="conditionGeneral">
                                <input type="checkbox" id="conditionGeneral" name="conditionGeneral"
                                       aria-label="Conditions général d'utilisation"
                                       checked={formData.conditionGeneral}
                                       onChange={gestionnaireChangement}
                                       className="checkConditionGeneral"/>
                                <label htmlFor="conditionGeneral" >
                                    En créant un compte, j'accepte les conditions <NavLink to=''> générales d'utilisation.</NavLink>
                                </label>
                                {validationErreurs.conditionGeneral &&
                                    <p className="error">{validationErreurs.conditionGeneral}</p>}
                            </article>
                            <input type="hidden" name="statut" value="Actif"/>
                    </fieldset>
                    <button type="submit" id="creerCompte" >Créer le compte</button>
                    <div className='connectervous-container'>
                        <em>Vous avez déjà un compte?<NavLink to='/SeConnecter'> Connectez-vous</NavLink></em>
                    </div>
                </form>

            </div>

        </section>
    );
};
export default CreerUnCompte;
import React, { useState, useEffect } from "react";
import {Link} from 'react-router-dom'


const Accueil = () => {
  const [annonces, setAnnonces] = useState([]);

  // Fonction pour récupérer les données des annonces visibles
  const fetchAnnonces = async () => {
    try {
      const response = await fetch("http://localhost:5227/api/Annonce/RecupererAnnoncesVisibles");
      if (!response.ok) {
        throw new Error("La réponse du serveur n'est pas bonne");
      }
      const data = await response.json();
      console.log("Data de l'API:", data);
      setAnnonces(data);
    } catch (error) {
        console.error("Erreur lors de la récupération des annonces:", error);
    }
  };
  // Récupérer les annonces visibles lorsque le composant est monté
  useEffect(() => {
      const fetchData = async () => {
          try {
              await fetchAnnonces();
              // Supprimer la classe 'preload' de l'élément body après avoir récupéré les annonces
              document.body.classList.remove('preload');
          } catch (error) {
              console.error("Erreur lors de la récupération des annonces:", error);
          }
      };
      fetchData();
  }, []);

  return (
    <section className="accueil wrapper">
      <article>
            <h2>PARTAGEZ<br />ÉCHANGEZ<br />LISEZ<br /></h2>
            <div>
              <p>Mettez en ligne les livres que vous ne voulez plus et trouvez dès à présent votre prochaine lecture.</p>
              <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit.
                  Dicta in rerum, blanditiis repellat, temporibus nesciunt,
                  mollitia impedit odit id sequi ipsa? Laborum esse velit laboriosam itaque sint aperiam ducimus alias.</p>
            </div>
        </article>
      <article className="accueil-card-wrapper wrapper">
        {annonces.map((annonce, index) => (
          <div key={index} className="accueil-card">
            {/*TODO mettre l'image dans un lien pour lorsqu'on clique sur l'image
                        on se dirige vers la page d'annonce du livre :
                            <a href={`/details/${annonce.id}`}>*/}
              <Link to={`/Annonce/${annonce.numeroReference}`} className="accueil-card__image">
                 {annonce && (
                    <img src={annonce.image_Url} alt={annonce.titreLivre} height="500"/>
                )}
              </Link>
              <article className="accueil-card__contenu">
                  {/* <div>*/}
                      {annonce ? (
                        <h3>{annonce.titreLivre}</h3>
                      ) : (
                        <h3>Un instant s'il vous plait...</h3>
                      )}
                      <p>
                        {annonce && (
                          <span>{annonce.prix.toFixed(2)}$</span>
                        )}
                      </p>
                      {annonce ? (
                        <p>{annonce.etat.description}</p>
                      ) : (
                        <p>Un instant s'il vous plait...</p>
                      )}
                  {/* </div>*/}
            </article>
          </div>
        ))}
      </article>
    {/*     <article className="wrapper">
            <h2>PARTAGEZ<br/>ÉCHANGEZ<br/>LISEZ<br/></h2>
            <div>
                <p>Mettez en ligne les livres que vous ne voulez plus et trouvez dès à présent votre prochaine lecture.</p>
                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Dicta in rerum, blanditiis repellat, temporibus nesciunt, mollitia impedit odit id sequi ipsa? Laborum esse velit laboriosam itaque sint aperiam ducimus alias.</p>
            </div>
        </article>
        <article className="accueil-card-wrapper wrapper">
            {annonces.map((annonce, index) => (
                <div key={index} className="accueil-card">
                    {/*TODO mettre l'image dans un lien pour lorsqu'on clique sur l'image
                    on se dirige vers la page d'annonce du livre :
                            <a href={`/details/${annonce.id}`}>

                    {/* Link pour créer un lien vers la page de détails de l'annonce.
                        TODO: METTRE À JOUR LA REDIRECTION AVEC LE TITRE DU LIVRE PASSÉE EN PARAMÈTRE POUR AFFICHER
                        LES INFORMATIONS DU LIVRES

                    <Link to={`/Annonce/${annonce.TitreLivre}`} className="accueil-card__image">
                        <img src={annonce.image_Url} alt={annonce.titreLivre}/>
                    </Link>
                    <article className="accueil-card__contenu">
                        <div>
                            <h3>{annonce.titreLivre}</h3>
                            <p><span>{annonce.prix.toFixed(2)}$</span></p>
                            <p>{annonce.etat.description}</p>
                        </div>
                    </article>
                </div>
            ))}
        </article>*/}
    </section>
  );
};

export default Accueil;

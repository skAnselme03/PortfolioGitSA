import React from "react";
import {Route } from 'react-router-dom';
import Acceuil from "../Acceuil/Acceuil";
const Page404 = () => {
    return(
        <section className={"Erreur404"}>
            <h2>Erreur 404</h2>
            <p>Oups! La page que vous cherchez n'a pas été trouvée.</p>

            <Route path="/" element={<Acceuil />} />
        </section>
    )
}
export default Page404;
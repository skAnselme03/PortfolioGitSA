import React from 'react';
import {NavLink} from 'react-router-dom';
const Navigation = () => {
    return (
        <nav>
           <ul>
               <NavLink to="/">
                <li>Accueil</li>
               </NavLink>
               <NavLink to="/question1">
                <li> Question 1</li>
               </NavLink>
               <NavLink to="/question2">
                <li> Question 2</li>
               </NavLink>
               <NavLink to="/question3">
                <li> Question 3</li>
               </NavLink>
            </ul> 
        </nav>
    );
};

export default Navigation;
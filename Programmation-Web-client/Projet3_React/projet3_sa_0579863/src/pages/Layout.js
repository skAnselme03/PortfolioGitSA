import {Outlet, Link, NavLink} from "react-router-dom";
import React from "react";

const Layout = () => {
  return (
    <>
    <div className="entete">
        <header>
        <h1>Projet 3: projet final REACT</h1>
      </header>
      <nav>
               <NavLink className="link link--underline link--underline--right" to="/">Accueil
               </NavLink>
               <NavLink className="link link--underline link--underline--right" to="/question1">Question 1
               </NavLink>
               <NavLink className="link link--underline link--underline--right" to="/question2">Question 2
               </NavLink>
               <NavLink className="link link--underline link--underline--right" to="/question3">Question 3
               </NavLink>
        </nav>
    </div>
    <Outlet />
    </>
  )
};

export default Layout;
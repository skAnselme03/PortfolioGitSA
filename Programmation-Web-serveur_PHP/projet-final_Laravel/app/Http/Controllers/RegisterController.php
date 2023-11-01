<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Person;

class RegisterController extends Controller
{

    public function showRegistrationForm()
    {
        return view('register');
    }

    public function register(Request $request)
    {
        // Validation des données du formulaire
        $validatedData = $request->validate([
            'username' => 'required',
            'email' => 'required|email|unique:Person',
            'password' => 'required',
        ], [
            'username.required' => 'Veuillez saisir un pseudo.',
            'email.required' => 'Veuillez saisir une adresse email.',
            'email.email' => 'Veuillez saisir une adresse email valide.',
            'email.unique' => 'Cette adresse email est déjà utilisée.',
            'password.required' => 'Veuillez saisir un mot de passe.',
        ]);

        // Création d'une nouvelle instance de Person avec les données validées
        $person = new Person();
        $person->UserName = $request->input('username');
        $person->Email = $request->input('email');
        $person->Password = $request->input('password');

        // Sauvegarde de l'instance dans la base de données
        $person->save();

        // Redirection vers la vue de connexion
        return redirect()->route('login');
    }
}
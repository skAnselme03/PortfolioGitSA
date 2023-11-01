<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Redirect;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\Controller;
use App\Models\Person;

class LoginController extends Controller
{
    public function showLoginForm()
    {
        return view('login');
    }

    public function login(Request $request)
    {
        session_start();

        $credentials = $request->validate([
            'email' => 'required|email',
            'password' => 'required',
        ]);

        $person = Person::where('Email', $credentials['email'])->first();

        if ($person && $person->Password === $credentials['password']) {
            // Authentication successful
            $_SESSION['username'] = $person->UserName;
            $_SESSION['id'] = $person->ID;

            // Regenerate the session ID
            // session_regenerate_id(true);

            // Redirect to the home page
            return redirect()->route('index');
        } else {
            // Authentication failed
            return redirect()->route('login')->with('error', 'Invalid credentials');
        }
    }
}
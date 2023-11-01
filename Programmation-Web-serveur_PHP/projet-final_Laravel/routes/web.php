<?php

// Note pour soi : Si l'erreur Target class does not exist apparaît, ça vient de là
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\RegisterController;
use App\Http\Controllers\LinkController;
use App\Http\Controllers\MyLinksController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

// Affichage de l'index
Route::get('/', [HomeController::class, 'index'])->name('index');

// Affichage du formulaire d'inscription
Route::get('/register', [RegisterController::class, 'showRegistrationForm'])->name('register');
// Soumission du formulaire d'inscription
Route::post('/register', [RegisterController::class, 'register']);

// Affichage du formulaire de connexion
Route::get('/login', [LoginController::class, 'showLoginForm'])->name('login');
// Soumission du formulaire de connexion
Route::post('/login', [LoginController::class, 'login']);

// Exécution du logout
Route::get('/logout', [HomeController::class, 'logout'])->name('logout');

// Affichage des liens en ordre anti-chronologique dans l'index
Route::get('/link/{id}', [HomeController::class, 'show'])->name('link.show');

// Affichage du formulaire de création d'un nouveau post avec lien
Route::get('/add-link', [LinkController::class, 'create'])->name('link.create');
// Ajout des données du nouveau lien dans la base de données
Route::post('/add-link', [LinkController::class, 'post'])->name('link.post');

// Ajout d'un upvote sur un post
Route::post('/link/{id}/upvote', [LinkController::class, 'upvote'])->name('link.upvote');
// Ajout d'un downvote sur un post
Route::post('/link/{id}/downvote', [LinkController::class, 'downvote'])->name('link.downvote');

// Ajout d'un commentaire sur un post
Route::post('/link/{id}/comment', [LinkController::class, 'comment'])->name('link.comment');

// Affichage des commentaires en ordre anti-chronologique dans un post
Route::get('/link/{id}', [LinkController::class, 'show'])->name('link.show');

// Affichage de la page des liens d'un utilisateur
Route::get('/my-links', [MyLinksController::class, 'index'])->name('my-links');
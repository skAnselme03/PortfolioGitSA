<?php

namespace App\Http\Controllers;

use App\Models\Comment;
use App\Models\Post;
use App\Models\Person;

class HomeController extends Controller
{
    public function index()
    {
        session_start();

        if (isset($_SESSION['username'])) {
            $links = Post::with('person')
                ->orderBy('ID', 'desc')
                ->get();
            
            $commentsCounts = Comment::select('PostID', \DB::raw('COUNT(*) as count'))
                ->groupBy('PostID')
                ->pluck('count', 'PostID');

            return view('index', compact('links', 'commentsCounts'));
        } else {
            return redirect()->route('login');
        }
    }

    public function show($id)
    {
        $link = Post::findOrFail($id);
        $commentsCount = Comment::where('PostID', $id)->count();
        return view('link', compact('link', 'commentsCount'));
    }

    public function logout()
    {
        session_start();
        session_destroy();
        return redirect()->route('login');
    }
}
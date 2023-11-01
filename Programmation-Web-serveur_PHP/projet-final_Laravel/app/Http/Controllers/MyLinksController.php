<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Post;
use App\Models\Comment;

class MyLinksController extends Controller
{
    public function index()
    {
        session_start();
        $userId = $_SESSION['id'];
        $myLinks = Post::where('personID', $userId)
                        ->orderBy('PublicationDate', 'desc')
                        ->get();
        $commentsCounts = Comment::select('PostID', \DB::raw('COUNT(*) as count'))
                        ->groupBy('PostID')
                        ->pluck('count', 'PostID');
        
        return view('mylinks', compact('myLinks', 'commentsCounts'));
    }
}

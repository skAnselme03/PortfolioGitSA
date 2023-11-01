<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Models\Post;
use App\Models\Comment;
use Illuminate\Support\Facades\DB;


class LinkController extends Controller
{
    public function create()
    {
        return view('create');
    }

    public function post(Request $request)
    {
        session_start();

        $validatedData = $request->validate([
            'link' => 'required',
            'description' => 'required',
        ]);

        $post = new Post;
        $post->link = $validatedData['link'];
        $post->description = $validatedData['description'];
        $post->publicationDate = now();
        $post->upVote = 0;
        $post->downVote = 0;
        $post->personID = $_SESSION['id'];
        $post->save();

        return redirect()->route('index')->with('success', 'Link added successfully.');
    }

    public function upvote($id)
    {
        DB::table('Post')->where('id', $id)->increment('UpVote');

        // Je ne sais pas pourquoi, cette méthode ne veut pas fonctionner
        // $post = Post::find($id);
        // $post->increment('UpVote');

        // Celle-là non plus
        // $post = Post::where('id', $id)->first();
        // $post->UpVote++; 
        // $post->save();

        return redirect()->back();
    }

    public function downvote($id)
    {
        DB::table('Post')->where('id', $id)->increment('DownVote');

        // Je ne sais pas pourquoi, cette méthode ne veut pas fonctionner
        // $post = Post::find($id);
        // $post->increment('DownVote');

        // Celle-là non plus
        // $post = Post::where('id', $id)->first();
        // $post->DownVote++; 
        // $post->save();

        return redirect()->back();
    }

    public function comment(Request $request, $id)
    {
        session_start();

        $validatedData = $request->validate([
            'commentaire' => 'required',
        ]);

        $comment = new Comment;
        $comment->Description = $validatedData['commentaire'];
        $comment->PublicationDate = now();
        $comment->PersonID = $_SESSION['id'];
        $comment->PostID = $id;
        $comment->save();

        return redirect()->back();
    }

    public function show($id)
    {
        $link = Post::findOrFail($id);
        $commentsCount = Comment::where('PostID', $id)->count();
        $comments = Comment::where('PostID', $id)
                            ->orderBy('id', 'desc')
                            ->get();
        return view('link', compact('link', 'comments', 'commentsCount'));
    }

}
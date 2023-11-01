<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Foundation\Auth\User as Authenticatable;
use App\Models\Post;
use App\Models\Comment;

class Person extends Authenticatable
{
    use HasFactory;

    protected $table = 'Person';
    public $timestamps = false;     // Il faut ajouter ça sinon Laravel ajoute des enregistrements pour des colonnes "created_at" et "updated_at"

    public function posts()
    {
        return $this->hasMany(Post::class, 'PersonID');
    }

    public function comments()
    {
        return $this->hasMany(Comment::class, 'PersonID');
    }
}

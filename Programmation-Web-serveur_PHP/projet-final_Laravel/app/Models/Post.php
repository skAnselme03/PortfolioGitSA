<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Post extends Model
{
    use HasFactory;

    protected $table = 'Post';
    protected $fillable = ['upVote', 'downVote'];
    public $timestamps = false;     // Il faut ajouter ça sinon Laravel ajoute des enregistrements pour des colonnes "created_at" et "updated_at"

    public function person()
    {
        return $this->hasOne(Person::class, 'ID', 'PersonID');
    }

    public function comments()
    {
        return $this->hasMany(Comment::class, 'PostID');
    }
}

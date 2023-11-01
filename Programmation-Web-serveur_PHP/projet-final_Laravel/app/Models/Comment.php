<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Comment extends Model
{
    use HasFactory;

    protected $table = 'Comment';
    public $timestamps = false;     // Il faut ajouter ça sinon Laravel ajoute des enregistrements pour des colonnes "created_at" et "updated_at"

    public function person()
    {
        return $this->belongsTo(Person::class, 'PersonID');
    }

    public function post()
    {
        return $this->belongsTo(Post::class, 'PostID');
    }
}

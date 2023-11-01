<?php
class Moovie
{
	/*
		**********************************
		*			ATTRIBUT			 *
		**********************************
	*/
	//
    private $id;
    private $title;
    private $genre;
    private $releaseDate;
    private $price;

	/*
		**********************************
		*			CONSTRUCTEUR		  *
		**********************************
	*/
	//
    public function __construct($id, $title, $genre, $releaseDate, $price)
    {
        $this->id = $id;
        $this->title = $title;
        $this->genre = $genre;
        $this->releaseDate = $releaseDate;
        $this->price = $price;
    }

 
	/*
		**********************************
		*			ACCESSEURS			  *
		*			MUTATEURS			  *
		**********************************
	*/
	//	

    public function getId()
    {
        return $this->id;
    }

    public function getTitle()
    {
        return $this->title;
    }

    public function setTitle($title)
    {
        $this->title = $title;
    }

    public function getGenre()
    {
        return $this->genre;
    }

    public function setGenre($genre)
    {
        $this->genre = $genre;
    }

    public function getReleaseDate()
    {
        return $this->releaseDate;
    }

    public function setReleaseDate($releaseDate)
    {
        $this->releaseDate = $releaseDate;
    }

    public function getPrice()
    {
        return $this->price;
    }

    public function setPrice($price)
    {
        $this->price = $price;
    }


	/*
		**********************************
		*			MÉTHODES			  *
		**********************************
	*/
	//
	 public function toString()
    {
        return "Movie: \n\tid = " . $this->id . "\n\ttitle = " . $this->title . 
               "\n\tgenre = " . $this->genre . "\n\treleaseDate = " . $this->releaseDate . 
               "\n\tprice = " . $this->price;
    }
}


?>
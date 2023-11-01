<?php
//classe hérité de la classe personne
class Electeur extends Personne{

	/*---------------------------------------- */
	/*	Définitions des propriétés (Attributs) */
	/*---------------------------------------- */
	public $bureau_de_vote;
	public $vote;
	/*---------------------------------------- */
	/*				Constructeur			   */
	/*---------------------------------------- */

    public function __construct($nom, $prenom, $bureau_de_vote) {
        parent::__construct($nom, $prenom);
        $this->bureau_de_vote = $bureau_de_vote;
		$this->vote = false;
    }
	/*---------------------------------------- */
	/*			Définitions des méthodes	   */
	/*---------------------------------------- */
	public function voter(bool $vote){
		$this->vote = $vote;
	}

	public function __toString(){
		if($this->vote){
			return parent::__toString()." a voter au bureau de vote de $this->bureau_de_vote";
		}
		return parent::__toString()." n'a pas voter";
	}
}
?>
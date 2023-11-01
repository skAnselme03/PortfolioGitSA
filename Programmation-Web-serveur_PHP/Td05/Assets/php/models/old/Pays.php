<?php
class country{
    public $name;
    public $continent_id;

    public function __construct($name, $continent_id)
    {
        $this->name = $name;
        $this->continent_id = $continent_id;
    } 
}
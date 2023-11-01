<?php
class city{
    public $id;
    public $name;
    public $is_capital;
    public $country_name;

    public function __construct($id, $name, $is_capital, $country_name)
    {
        $this->id = $id;
        $this->name = $name;
        $this->is_capital = $is_capital;
        $this->country_name = $country_name;
    } 
}
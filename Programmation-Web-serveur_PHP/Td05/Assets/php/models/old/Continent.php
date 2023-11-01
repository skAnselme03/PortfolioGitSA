<?php
class continent
{
    public $id;
    public $name;
    public $population;
    public $area;

    public function __construct($id, $name, $population, $area)
    {
        $this->id = $id;
        $this->name = $name;
        $this->population = $population;
        $this->area = $area;
    }
}

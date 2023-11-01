'use Strict';

/**
 * Mettre sous forme d'exposant tout valeur qu'importe le type (int, double, string ...) en créant l'élement <sup>
 * @param {*} argument : valeur à mettre sous forme d'éxposant
 * @returns la valeur en exposant
 */
function mettreEnExposant(argument) {
    return '<sup>' + argument + '</sup>';
}
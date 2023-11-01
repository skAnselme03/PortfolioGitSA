<?php

/**
	 * Retourne le mois littéral en français 
	 * 
	 * @param {date} $moisLitEng : mois litérral en anglais
	 * 
	 * @return {date} $mois littéreal en francais
	 */
	function moisLitteralFr($moisLitEng){
		 switch ($moisLitEng) {
			case 'January':
			case 1:
			case '01':
				return 'Janvier';
			case 'February':
			case 2:
			case '02':
				return 'Février';
			case 'March':
			case 3:
			case '03':
				return 'Mars';
			case 'April':
			case 4:
			case '04':
				return 'Avril';
			case 'May':
			case 5:
			case '05':
				return 'Mai';
			case 'June':
			case 6:
			case '06':
				return 'Juin';
			case 'July':
			case 7:
			case '07':
				return 'Juillet';
			case 'August':
			case 8:
			case '08':
				return 'Août';
			case 'September':
			case 9:
			case '09':
				return 'Septembre';
			case 'October':
			case 10:
			case '10':
				return 'Octobre';
			case 'November':
			case 11:
			case '11':
				return 'Novembre';
			case 'December':
			case 12:
			case '12':
				return 'Décembre';
			default:
				return '';
		}
	}

?>
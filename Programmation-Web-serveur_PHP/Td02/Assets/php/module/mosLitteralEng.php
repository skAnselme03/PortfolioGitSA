<?php

/**
	 * Retourne le mois littéral en anglais 
	 * 
	 * @param {date} $moisLitEng : mois litérral en francais
	 * 
	 * @return {date} $mois littéreal en anglais
	 */
	function moisLitteralAng($moisLitFr){
		switch ($moisLitFr) {
			case 'Janvier':
			case 1:
			case '01':
				return 'January';
			case 'Février':
			case 2:
			case '02':
				return 'February';
			case 'Mars':
			case 3:
			case '03':
				return 'March';
			case 'Avril':
				case 4:
				case '04':
				return 'April';
			case 'Mai':
			case 5:
			case '05':
				return 'May';
			case 'Juin':
			case 6:
			case '06':
				return 'June';
			case 'Juillet':
			case 7:
			case '07':
				return 'July';
			case 'Août':
			case 8:
			case '08':
				return 'August';
			case 'Septembre':
			case 9:
			case '09':
				return 'September';
			case 'Octobre':
			case 10:
			case '10':
				return 'October';
			case 'Novembre':
			case 11:
			case '11':
				return 'November';
			case 'Décembre':
			case 12:
			case '12':
				return 'December';
			default:
				return '';
		}
	}

?>
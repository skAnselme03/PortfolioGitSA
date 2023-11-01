<?php

		// Fonction pour générer une adresse e-mail aléatoire
		function generateRandomEmail($servers) {
			$characters = 'abcdefghijklmnopqrstuvwxyz';
			$charactersNum = '0123456789';
			$randomString = '';
			$randomInt = '';
			
			// Génération de la chaîne aléatoire de 8 caractères
			for ($i = 0; $i < 8; $i++) {
				$randomString .= $characters[rand(0, strlen($characters) - 1)];
				$randomInt .= $charactersNum[rand(0, strlen($charactersNum) - 1)];
			}
			// Sélection aléatoire d'un serveur de messagerie
			$server = $servers[array_rand($servers)];
			
			// Construction de l'adresse e-mail complète
			$email = $randomString .'_' . substr($randomInt,0,3) . '@' . $server;
			return $email;
		}

?>
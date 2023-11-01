def premiere_lettre_enMajuscule(mots):
    '''
        Mettre en majuscule le premier caractère
    :param mots: mots a modifié
    :return: mot modifier avec le premier caractère en majuscule
    '''
    if len(mots) > 1:
        # diviser la chaîne de caractères en une liste de mots
        tabMot = mots.split(' ')
        # itération pour mettre en majuscule
        # la première lettre de chaque mot
        for i in range(len(tabMot)):
            tabMot[i] = tabMot[i].capitalize()
        # joindre les mots en une seule chaîne de caractères
        # avec des espaces entre chaque mot.
        return ' '.join(tabMot)
    return mots.capitalize()


def num_telephone_to_format(numero_telephone):
    '''
        Formater un numéro de téléphone à 10 chiffre
    :param numero_telephone: numéro de teléphone fournis sous forme
    :return: le numéro formater (xxx) xxx-xxxx
    '''
    # Supprimer tous espace succeptible d'être présent dans la chaîne
    numero_telephone = numero_telephone.strip()

    # Extraire les trois premiers chiffres
    area_code = numero_telephone[:3]

    # Extraire les chiffres suivants
    premiere_parti = numero_telephone[3:6]
    deuxieme_parti = numero_telephone[6:]

    # Concaténer les parties avec les tirets et les parenthèses
    num_tel_formater = f'({area_code}) {premiere_parti}-{deuxieme_parti}'

    return num_tel_formater


def format_to_num_telephone(formant_num_tel):
    '''
        Prendre un numéro de téléphone formater et enlever toute forme de caractère spéciaux
    :param formant_num_tel: le numéro de téléphone formater
    :return: le numéro de télephone sous forme de chaine de caractère
    '''
    # Supprimer tous les caractères spéciaux et les espaces
    numero_telephone = formant_num_tel.replace("(", "").replace(")", "").replace("-", "").replace(" ", "")
    # Vérifier que le numéro de téléphone contient 10 chiffres
    if len(numero_telephone) != 10:
        raise ValueError("Le numéro de téléphone doit contenir 10 chiffres.")
    # Retourner le numéro de téléphone sans formatage sous forme de chaine de caractère
    return str(numero_telephone)

import pymysql
from passlib.hash import sha256_crypt

# TODO: appeler notre fichier auth.py comme suggérer par Daliane, plus facile à gerer
from website.authDali import *

# from auth import *

connection = pymysql.connect(
    host="localhost",
    user=utilisateur,
    password=mdp,  # Inserer votre mot de passe mysql
    db=db,  # Inserer le nom de votre bd
    autocommit=True
)

cursor = connection.cursor()


# ********************************
# ****         Users          ****
# ********************************

def hash_password(password):
    """
    :param password: password entré par le user lors de la créationj de son compte
    :return: le password hasher
    """
    return sha256_crypt.hash(password)


def insert_user(dictDonnees):
    '''
    Insérer les données utilisateur dans les tables Users et Adresses.
    Si l'adresse n'existe pas dans la bdd, insère une nouvelle adresse dans la table Adresses
    et récupère l'id de l'adresse insérée pour l'utiliser dans la table Users.

    :param dictDonnees : Dictionnaire contenant les données utilisateur
    '''
    # Vérifier si l'adresse existe déjà
    codeAdresse = adresse_existe(dictDonnees)
    # Si l'adresse n'exist pas dans la bdd inserer une nouvelle adresse
    if codeAdresse is None:
        '''numeroCivique= dictDonnees.get('numeroCivique', '')
        rue=dictDonnees.get('rue', '')
        ville=dictDonnees.get('ville', '')
        codePostal=dictDonnees.get('codePostal', '')
        province=dictDonnees.get('province', '')
        pays=dictDonnees.get('pays', '')'''
        # insrer l'adresse dans le tableau d'adresses
        requete = f"""INSERT INTO Adresses(NumCivique, Rue, Ville, Provinces, CodePostal, Pays)
                  VALUES (%s, %s, %s, %s, %s, %s);"""
        cursor.execute(requete, (dictDonnees.get('numeroCivique', ''),
                                 dictDonnees.get('rue', ''),
                                 dictDonnees.get('ville', ''),
                                 dictDonnees.get('province', ''),
                                 dictDonnees.get('codePostal', ''),
                                 dictDonnees.get('pays')))

        # Récupérer le nouvel id de l'adresse insérée
        codeAdresse = cursor.lastrowid

    # Insérer les données utilisateur dans la table
    # Users avec l'id de l'adresse correspondante
    new_requete = f"""INSERT INTO Users(Prenom, Nom, NumeroTelephone, 
                                                  Courriel, Username, Password, CodeAdresse)
                  VALUES
                  (%s, %s, %s, %s, %s, %s, %s);"""

    hashed_password = hash_password(dictDonnees.get('password'))

    cursor.execute(new_requete, (dictDonnees.get('prenom'),
                                 dictDonnees.get('nom'),
                                 dictDonnees.get('numeroTelephone'),
                                 dictDonnees.get('email'),
                                 dictDonnees.get('username'),
                                 hashed_password,
                                 codeAdresse))


def user_email_exist(email):
    '''
        vérifier qu'un compte éxiste dans la bdd par son adresse mail
        :param email : l'adresse mail saisi par l'utilisateur
        :return: eponse de la requête : information de l'utilisateur

        Ref-inspiration: https://codeshack.io/login-system-python-flask-mysql/
    '''

    requete = 'SELECT * FROM Users WHERE Courriel = %s;'
    cursor.execute(requete, (email,))
    # Fetch one record and return result
    account = cursor.fetchone()
    return account


def verifier_users(emailOrUsername):
    """
        vérifier qu'un compte éxiste dans la bdd
        :param emailOrUsername: l'adresse mail saisi par l'utilisateur
        :return: eponse de la requête: information de l'utilisateur

        Ref-inspiration: https://codeshack.io/login-system-python-flask-mysql/
    """

    requete = 'SELECT * FROM Users WHERE Courriel = %s;'
    cursor.execute(requete, (emailOrUsername,))
    # Fetch one record and return result
    account = cursor.fetchone()
    return account


def get_users(id=None):
    """
        Récupérer les données d'un utilisateur
    :param id: l'ID de l'utilisateur à récupérer
    :return: données de l'utilisateur
    """

    requete = 'SELECT * FROM Users WHERE idUser = %s;'
    cursor.execute(requete, (id,))
    # Fetch one record and return result
    user_account = cursor.fetchone()
    return user_account


def select_solde_points(idUser):
    request = f"""SELECT SoldePoints FROM Users WHERE idUser = {idUser};"""
    cursor.execute(request)
    solde = cursor.fetchone()
    soldePoints = solde[0]
    return soldePoints


def select_note_moyenne(idUser):
    request = f"""SELECT NoteMoyenne FROM Users WHERE idUser = {idUser};"""
    cursor.execute(request)
    note = cursor.fetchone()
    noteMoyenne = note[0]
    return noteMoyenne


def get_photo_user(idUser):
    '''
        trouver le photo de l'utilisateur
    :param idUser: id de l'utilisateur
    :return: données de l'utilisateur
    '''
    requete = 'SELECT * FROM Users WHERE idUser = %s'
    cursor.execute(requete, (idUser,))
    # Fetch one record and return result
    user_photo = cursor.fetchone()
    return user_photo


def modify_user(dictDonnees, idUser, passwordVerified):
    # Vérifier si l'adresse existe déjà
    codeAdresse = adresse_existe(dictDonnees)
    print('test: ', codeAdresse)
    # Si l'adresse n'exist pas dans la bdd inserer une nouvelle adresse
    if codeAdresse is None:
        # insrer l'adresse dans le tableau d'adresses
        requete = f"""INSERT INTO Adresses(NumCivique, Rue, Ville, Provinces, CodePostal, Pays)
                         VALUES
                         (%s, %s, %s, %s, %s, %s);"""
        print(dictDonnees.get('province', ''))
        cursor.execute(requete, (dictDonnees.get('numeroCivique', ''),
                                 dictDonnees.get('rue', ''),
                                 dictDonnees.get('ville', ''),
                                 dictDonnees.get('province', ''),
                                 dictDonnees.get('codePostal', ''),
                                 dictDonnees.get('pays')))
        # Récupérer le nouvel id de l'adresse insérée
        codeAdresse = cursor.lastrowid

    #vérifier si le mot de pase est le même que celui existant
    if(passwordVerified):
        #updater les données de l'utilisateur dans la bdd
        new_requete = """UPDATE Users SET Prenom = %s, Nom = %s,
                         NumeroTelephone = %s, Courriel = %s,
                         PhotoProfil = %s, CodeAdresse = %s
                         WHERE idUser = %s;"""

        cursor.execute(new_requete, (dictDonnees.get('prenom', ''),
                                     dictDonnees.get('nom', ''),
                                     dictDonnees.get('numeroTelephone', ''),
                                     dictDonnees.get('courriel', ''),
                                     dictDonnees.get('photoProfil', ''),
                                     codeAdresse,
                                     idUser))
    else:
        # Updater les données de l'utilisateur dans la BDD
        new_requete = """UPDATE Users SET Prenom = %s, Nom = %s,
                                NumeroTelephone = %s, Courriel = %s,
                                Password = %s, PhotoProfil = %s,
                                CodeAdresse = %s WHERE idUser = %s;"""

        hashed_password = hash_password(dictDonnees.get('nouveauPassword'))

        cursor.execute(new_requete, (dictDonnees.get('prenom', ''),
                                     dictDonnees.get('nom', ''),
                                     dictDonnees.get('numeroTelephone', ''),
                                     dictDonnees.get('courriel', ''),
                                     hashed_password,
                                     dictDonnees.get('photoProfil', ''),
                                     codeAdresse,
                                     idUser))
# ********************************
# ****        Adresses        ****
# ********************************


def adresse_existe(dictDonnees):
    '''
        Vérifie si un adresse existe grace au données fournis
    :param dictDonnees: données fournis sous forme json
    :return: l'adresse s'il existe
    '''

    requete = 'SELECT idAdresse FROM Adresses ' \
              'WHERE NumCivique = %s AND Rue = %s AND ' \
              'Ville = %s AND Provinces = %s AND ' \
              'CodePostal = %s AND Pays = %s;'
    cursor.execute(requete, (dictDonnees.get('numeroCivique', ''),
                             dictDonnees.get('rue', ''),
                             dictDonnees.get('ville', ''),
                             dictDonnees.get('codePostal', ''),
                             dictDonnees.get('province', ''),
                             dictDonnees.get('pays', ''),
                             )
                   )
    adresse = cursor.fetchone()
    return adresse[0] if adresse is not None else None


def get_addresse(id):
    """
        récupérer l'adresse d'un utilisateur
    :param id: CodeAdresse de la table Users
    :return: donneées composant l'adresse
    """
    requete = 'SELECT * FROM Adresses WHERE idAdresse = %s'
    cursor.execute(requete, (id,))
    # Fetch one record and return result
    user_adresse = cursor.fetchone()
    return user_adresse


def select_nom_vendeur(id_vendeur):
    request = f"""SELECT prenom FROM Users WHERE idUser = {id_vendeur}"""
    cursor.execute(request)
    vendeur = cursor.fetchone()
    nom_vendeur = vendeur[0]
    return nom_vendeur


def select_email_user(id_vendeur):
    request = f"""SELECT courriel FROM Users WHERE idUser = {id_vendeur}"""
    cursor.execute(request)
    vendeur = cursor.fetchone()
    email_vendeur = vendeur[0]
    return email_vendeur


def get_id_adresse(idUser):
    request = f"""SELECT CodeAdresse FROM Users WHERE idUser = {idUser}"""
    cursor.execute(request)
    vendeur = cursor.fetchone()
    code_adresse = vendeur[0]
    return code_adresse


# ********************************
# ****       Produits         ****
# ********************************

def insert_jouet(titre, description, etat, image, prix, categorie, categorie_age, vendeur):
    request = f"""INSERT INTO Produits (Titre, Description, Etat, PhotoProduit, Prix, Categorie, CategorieAge, 
    CodeVendeur) VALUE ({titre},{description},{etat},{image},{prix},{categorie},{categorie_age},{vendeur});"""
    cursor.execute(request)


def select_jouet(id_jouet):
    request = f"""SELECT * FROM Produits WHERE idProduit = {id_jouet};"""
    cursor.execute(request)
    jouet = cursor.fetchone()
    return jouet


def select_jouets():
    request = "SELECT * FROM Produits;"
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


def select_vendeur(idJouet):
    request = f"""SELECT CodeVendeur FROM Produits WHERE idProduit = {idJouet}"""
    cursor.execute(request)
    vendeur = cursor.fetchone()
    id_vendeur = vendeur[0]
    return id_vendeur


# Les 5 derniers jouets ajoutés dans la table
def select_derniers_jouets():
    request = f"""select * from produits ORDER BY idProduit DESC LIMIT 5;"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


# Pour la barre de recherche
def search_jouets(query):
    request = f"""SELECT * FROM Produits WHERE Titre LIKE '%{query}%';"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


def filter_jouets(categorie, categorieAge, etat):
    any_categorie = "ANY (SELECT DISTINCT categorie FROM Produits)"
    any_categorie_age = "ANY (SELECT DISTINCT categorieAge FROM Produits)"
    any_etat = "ANY (SELECT DISTINCT etat FROM Produits)"
    if categorie == "any":
        categorie = any_categorie
    else:
        categorie = "'" + str(categorie) + "'"
    if categorieAge == "any":
        categorieAge = any_categorie_age
    else:
        categorieAge = "'" + str(categorieAge) + "'"
    if etat == "any":
        etat = any_etat
    else:
        etat = "'" + str(etat) + "'"
    request = f"""SELECT * FROM Produits WHERE categorie = {categorie} AND categorieAge = {categorieAge} 
    AND etat = {etat}"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


# Pour le profil
def select_jouets_annonces(idUser):
    request = f"""SELECT * FROM Produits WHERE CodeVendeur = {idUser};"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


def select_jouets_wishlist(idUser):
    request = f"""SELECT * FROM Produits WHERE idProduit = ANY (SELECT CodeProduit FROM Wishlist WHERE CodeUser =
     {idUser});"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


def select_jouets_achetes(idUser):
    request = f"""SELECT * FROM ProduitsArchives WHERE CodeAcheteur={idUser};"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


def select_jouets_reserves(idUser):
    request = f"""SELECT * FROM Produits WHERE idProduit = ANY (SELECT CodeProduit FROM transactions WHERE 
    CodeVendeur={idUser} AND statut='Réservé');"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


def select_jouets_vendu(idUser):
    request = f"""SELECT * FROM ProduitsArchives WHERE CodeVendeur={idUser};"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets


def get_valeur_points(idJouet):
    request = f"""SELECT ValeurPoint FROM Produits WHERE idProduit = {idJouet};"""
    cursor.execute(request)
    valeur = cursor.fetchone()
    valeurPoints = valeur[0]
    return valeurPoints


def supprime_compte_bd(idUser):
    request = f"""DELETE FROM Users WHERE idUser = {idUser};"""
    cursor.execute(request)


# ********************************
# ****     Transactions       ****
# ********************************


def insert_transaction(date_transaction, statut, code_acheteur, code_produit):
    request = f"""INSERT INTO Transactions (DateTransaction, Statut, CodeAcheteur, CodeProduit) VALUE (
    '{date_transaction}','{statut}',{code_acheteur},{code_produit}) """
    cursor.execute(request)


def select_acheteur(idJouet):
    request = f"""SELECT CodeAcheteur FROM Transactions WHERE CodeProduit = {idJouet};"""
    cursor.execute(request)
    acheteur = cursor.fetchone()
    id_acheteur = acheteur[0]
    return id_acheteur


def select_transaction(idJouet):
    request = f"""SELECT idTransaction FROM Transactions WHERE CodeProduit = {idJouet} AND Statut='Vendu';"""
    cursor.execute(request)
    liste_code = [entry for entry in cursor.fetchall()]
    code_transaction = liste_code[0]
    return code_transaction


def get_statut(id):
    request = f"""SELECT Statut FROM Transactions WHERE CodeProduit = {id};"""
    cursor.execute(request)
    statut_list = cursor.fetchone()
    statut = statut_list[0]
    return statut


def get_transaction_id(id_produit):
    request = f"""SELECT idTransaction FROM Transactions WHERE codeProduit = {id_produit};"""
    cursor.execute(request)
    id_list = cursor.fetchone()
    id = id_list[0]
    return id


def update_statut_transaction(id):
    request = f""" UPDATE Transactions SET Statut = "Vendu" WHERE idTransaction = {id};"""
    cursor.execute(request)


# ********************************
# ****         Wishlist       ****
# ********************************

def insert_wishlist(code_produit, code_user):
    request = f"""INSERT INTO Wishlist (CodeProduit, CodeUser) VALUE ({code_produit},{code_user})"""
    cursor.execute(request)


def select_id_jouets_wishlist(idUser):
    request = f"""SELECT CodeProduit FROM Wishlist WHERE CodeUser =
     {idUser};"""
    cursor.execute(request)
    jouets = [entry[0] for entry in cursor.fetchall()]
    return jouets


# ********************************
# ****         Avis           ****
# ********************************


def insert_avis(message, note, code_transaction):
    request = f"""INSERT INTO Avis (Message, Note, CodeTransaction) VALUE ({message},{note}, {code_transaction})"""
    cursor.execute(request)


def select_avis(idUser):
    request = f"""SELECT * FROM Avis WHERE CodeTransaction = ANY (SELECT idTransaction FROM Transactions T JOIN 
    ProduitsArchives P ON CodeProduit=idProduit WHERE P.CodeVendeur={idUser});;"""
    cursor.execute(request)
    jouets = [entry for entry in cursor.fetchall()]
    return jouets

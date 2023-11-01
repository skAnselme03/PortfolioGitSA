from flask import (
    Flask,
    flash,
    g,
    render_template,
    request,
    send_from_directory,
    redirect,
    session,
    url_for
)

# TODO: mettre en commentaire car spécificité de mac os (SA)
# '''from database import (
#      insert_jouet, select_jouet,
#      select_jouets, select_derniers_jouets,
#      select_vendeur, search_jouets, select_nom_vendeur, filter_jouets, insert_transaction,
#      select_email_vendeur, insert_wishlist, verifier_users  # , verifier_users
#  )'''


from website.Utilisateur import Utilisateur
from website.database import *
from website.utilitaire import *

import os
# from database import *
# from Utilisateur import Utilisateur
# from utilitaire import *
from datetime import datetime
from flask_mail import Mail, Message

app = Flask(__name__)
app.config['SECRET_KEY'] = 'my_secret_key'

app.config['UPLOAD_IMG_JOUET'] = 'website/static/storage/img_jouet'
app.config['UPLOAD_IMG_PROFIL'] = 'website/storage/img_profil'
# TODO: particularité mac
# app.config['UPLOAD_IMG_JOUET'] = './static/storage/img_jouet'
# app.config['UPLOAD_IMG_PROFIL'] = './static/storage/img_profil'

app.config['MIME_TYPES'] = {'text/javascript': ['js']}
app.config['MYSQL_DB'] = '../templates/login'

app.config['MAIL_SERVER'] = 'smtp.gmail.com'
app.config['MAIL_PORT'] = 465
app.config['MAIL_USERNAME'] = 'petitkangourou23@gmail.com'
app.config['MAIL_PASSWORD'] = 'vtfonddbptydbwmn'
app.config['MAIL_USE_TLS'] = False
app.config['MAIL_USE_SSL'] = True

mail = Mail(app)

date = datetime.now()


@app.route('/')
def home():
    jouets = select_derniers_jouets()
    session_ouverte = False
    if g.user is not None:
        session_ouverte = True
    return render_template('home.html', jouets=jouets, session_ouverte=session_ouverte)


@app.before_request
def before_request():
    """
            Fonction exécutée avant chaque requête, comme défini par le décorateur
        :return :
        """
    # Objet global utilisé pour stocker des données
    # qui peuvent être utilisées dans toute l'application
    g.user = None
    if 'user_id' in session:
        user_list = [x for x in users if x.id == session['user_id']]
        if len(user_list) > 0:
            user = user_list[0]
            g.user = user


# Créationi d'une instance d'utilisateur
users = []
users.append(Utilisateur(id=None,
                         email='',
                         username='',
                         password=''))


# Password (celui donné en input), actual chiffré (celui dans la bd)
def verify_password(password, actual):
    return sha256_crypt.verify(password, actual)


@app.route('/login', methods=['GET', 'POST'])
def login():
    """
    :return:
    """

    # Message de retour si un problème survient...
    msg = ''
    # Vérifie si la requête POST de l'"email" ansi que le "password" exist
    # (lorsque l'utilisateur soumet le formulaire)
    if request.method == 'POST':
        # stocquer la réponse reçue du serveur dans un dictionnaire de données
        donnees_user = request.form.to_dict()

        session.pop('user_id', None)
        # Creer des variables d'accès
        courriel = donnees_user.get('email', '')
        motDePasse = donnees_user.get('password', '')

        # vérifier si ces comptes exists dans la BDD
        # cursor = mysql.connection.cursor(MySQLdb .cursors.DictCursor)
        compteVerifier = verifier_users(courriel)

        if compteVerifier is not None:
            # instanciation d'un utilisateur
            userstest = []
            userstest.append(Utilisateur(id=compteVerifier[0],
                                         email=compteVerifier[4],
                                         username=compteVerifier[5],
                                         password=compteVerifier[6]))
            utilisateur = [x for x in userstest
                           if (x.email == courriel or
                               x.username == courriel)][0]
            idUser = utilisateur.id
            # créer une session de donnée, acceder par une autre route
            if (courriel == utilisateur.email) or \
                    courriel == utilisateur.username:
                # recupérer les donnes complète de l'utilisateur
                # matching_users = get_users(utilisateur.id, utilisateur.username)
                matching_users = get_users(utilisateur.id)
                # récupérer l'adresse de l'utilisateur courant
                idAdresse = get_id_adresse(matching_users[0])
                matching_adresse = get_addresse(idAdresse)
                # réinstancier l'utilisateur avec tous ces attributs
                users.clear()
                users.append(Utilisateur(id=matching_users[0],
                                         prenom=matching_users[1],
                                         nom=matching_users[2],
                                         numeroTelephone=num_telephone_to_format(matching_users[3]),
                                         email=matching_users[4],
                                         username=matching_users[5],
                                         password=matching_users[6],
                                         codeAddresse=matching_users[8],
                                         numeroCivique=matching_adresse[1],
                                         rue=premiere_lettre_enMajuscule(matching_adresse[2]),
                                         ville=matching_adresse[3],
                                         province=matching_adresse[4],
                                         codePostal=matching_adresse[5],
                                         pays=matching_adresse[6]
                                         )
                             )

                # Récupérer l'utilisateur nouvellement modifié
                utilisateur = [x for x in users
                               if (x.id == idUser)][0]

                if verify_password(motDePasse, utilisateur.password):
                    # Redirection page profil de l'utilisateur
                    session['user_id'] = utilisateur.id
                    flash('Connexion réussi!', 'success')

                    return redirect(url_for('profil'))

                else:
                    # email / password incorrect
                    flash('Incorrect email/username/password!', 'error')

                    # afficher la page login si mauvais mot de passe inexistant
                    return render_template("login.html")
        else:
            flash('email/username non recconus!\n'
                  'Créer un compte si c\'est votre première visite!', 'error')
            return render_template("login.html")

    # afficher la page login si compte inexistant
    return render_template("login.html")


# route pour la deconnection
@app.route('/logout')
def logout():
    # enlever la session de donner, cela deconnecter automatiquement l'utilisateur
    session.pop('loggedin', None)
    session.pop('id', None)
    session.pop('username', None)
    g.user = None
    session['user_id'] = None
    # Redirection a la page de connection

    return redirect(url_for('login'))


@app.route('/signup', methods=['GET', 'POST'])
def sign_up():
    if request.method == 'POST':
        # stocquer la réponse reçue du serveur dans un dictionnaire de données
        donnees_user = request.form.to_dict()

        # vérifier si le nouvel utilisateur existe dans la bdd
        email_newUser = donnees_user.get('email', '')
        compteVerifier = user_email_exist(email_newUser)

        # si différent de None, l'utilisateur n'existe pas, on peut donc l'enregister dans la bdd
        # sinon il existe, on demande à l'utilisateur de se connecter
        if not compteVerifier:
            insert_user(donnees_user)
            # TODO: test à enlever: ne PAS OUBLIÉ DE METTRE LE STATUS DE LA REPONSE (reponse 2000)
            # print(not adresse_existe(donnees_user))
            flash('Inscription réussie, veuillez vous connecter!', 'success')
            return redirect(url_for('login'))
        else:
            flash('Ce compte existe déjà, veuillez vous connecter!', 'error')
            return redirect(url_for('sign_up'))

    return render_template("signup.html")


def afficher_jouets(jeux):
    """
    :param jeux : liste des jeux
    :return : rend la page catalogue avec les jeux
    """
    return render_template('catalogue.html', jouets=jeux)


@app.route('/catalogue')
# Route pour accéder au catalogue. Elle utilise la fonction afficher_jouet qui afficher les
# jouets sur la page catalogue.
def catalogue():
    jeux = select_jouets()
    return afficher_jouets(jeux)


@app.route('/recherche', methods=["POST"])
# Route pour la recherche dans la page catalogue et home. Elle utilise la fonction afficher_jouet qui afficher les
# jouets sur la page catalogue.
def recherche():
    query = request.form.getlist("query")[0]
    jouets = search_jouets(query)
    return afficher_jouets(jouets)


@app.route('/filtre', methods=["POST"])
# Route pour les filtres dans la page catalogue. Elle utilise la fonction afficher_jouet qui afficher les
# jouets sur la page catalogue.
def filtre():
    categorie = request.form.getlist("categorie")[0]
    categorieAge = request.form.getlist("categorieAge")[0]
    etat = request.form.getlist("etat")[0]

    jouets = filter_jouets(categorie, categorieAge, etat)
    return afficher_jouets(jouets)


@app.route('/profil')
def profil():
    if g.user is None:
        return redirect(url_for('login'))
    idUser = g.user.id
    jouets_annonces = select_jouets_annonces(idUser)
    jouets_wishlist = select_jouets_wishlist(idUser)
    jouets_achetes = select_jouets_achetes(idUser)
    jouets_reserves = select_jouets_reserves(idUser)
    jouets_vendus = select_jouets_vendu(idUser)
    avis = select_avis(idUser)
    SoldePoints = select_solde_points(idUser)
    photos_profil = get_photo_user(idUser)

    if photos_profil[7] != '':
        # afficher la photo de profil
        source_photo_profil = "../static/storage/img_profil/{filename}".format(filename=photos_profil[7])
    else:
        # afficher l'avatar si la photo de profil n'éxiste pas
        source_photo_profil = "../static/storage/img_profil/avatarDefault.png"

    avis_complete = True

    # if select_avis(idUser):
    #     avis_complete = True

    return render_template("profil.html", jouets_annonces=jouets_annonces, jouets_wishlist=jouets_wishlist,
                           jouets_achetes=jouets_achetes, jouets_reserves=jouets_reserves, jouets_vendus=jouets_vendus,
                           avis=avis, SoldePoints=SoldePoints, avis_complete=avis_complete,
                           source_photo_profil=source_photo_profil)


@app.route("/storage/img_profil/<filename>")
def get_profil_image(filename):
    return send_from_directory(app.config['UPLOAD_IMG_PROFIL'], filename)


@app.route("/modif_profil/<username>", methods=['POST'])
def modif_profil(username):
    if g.user is None or g.user['username'] != username:
        return redirect(url_for('login'))
    if request.method == 'POST':
        # stocquer la réponse reçue du serveur dans un dictionnaire de données
        donnees_user = request.form.to_dict()
        vrif_pwd = True # nouveau password == ancien password
        if (verify_password(donnees_user.get('nouveauPassword',''),g.user.password)):
            modify_user(donnees_user, g.user.id, vrif_pwd)# nouveau password == ancien password
        else:
            modify_user(donnees_user, g.user.id, (not vrif_pwd))# nouveau password != ancien password

        flash('Modification effectuée avec succès!', 'success')
        return redirect(url_for("login"))
    else:
        flash('Veuillez recommencer les modifications!', 'error')
        donnees_user = request.form.to_dict()
        donnees_user['username'] = username
        print('donnees user : ', donnees_user)
        print('test province', donnees_user.get('province', ''))
        return redirect(url_for("profil"))

    return redirect(url_for("profil"))


@app.route('/supprimer_compte')
# Route pour supprimer un compte client
def supprimer_compte():
    idUser = g.user.id
    supprime_compte_bd(idUser)
    flash("Votre compte a été supprimé.", "success")
    return render_template("signup.html")


@app.route('/ajouter', methods=["POST", "GET"])
# Route pour le formulaire pour ajouter un produit
def ajouter_jouet():
    if request.method == "POST":
        file = request.files['photoJouet']
        file.save(os.path.join(app.config['UPLOAD_IMG_JOUET'], file.filename))

        id_current_user = g.user.id

        titre = request.form.getlist("titre")[0]
        titre = "'" + titre.replace("'", "''") + "'"
        prix = "'" + request.form.getlist("prix")[0] + "'"
        img_jouet = "'" + file.filename + "'"
        categorie = "'" + request.form.getlist("categorie")[0] + "'"
        categorie_age = "'" + request.form.getlist("categorieAge")[0] + "'"
        etat = "'" + request.form.getlist("etat")[0] + "'"
        vendeur = id_current_user
        description = request.form.getlist("description")[0]
        description = "'" + description.replace("'", "''") + "'"

        insert_jouet(titre, description, etat, img_jouet, prix, categorie, categorie_age, vendeur)
        flash("Votre jouet a bien été ajouté au catalogue.", "success")

        return render_template("ajouter.html")
    return render_template("ajouter.html")


@app.route("/storage/img_jouet/<filename>")
def get_image(filename):
    return send_from_directory(app.config['UPLOAD_IMG_JOUET'], filename)


@app.route('/jouet/<id>')
# Route pour la page du jouet individuel
def jouet_individuel(id):
    jouet = select_jouet(id)
    photo_produit = jouet[4]
    vendeur = jouet[8]
    prenom_vendeur = str(select_nom_vendeur(vendeur))
    source_image = "../static/storage/img_jouet/{filename}".format(filename=photo_produit)
    id_jouet = id
    note = select_note_moyenne(vendeur)
    try:
        get_statut(id)
    except TypeError:
        statut_jouet = "disponible"
    else:
        statut_jouet = "reserve"
    if note == 0:
        note = "Ce vendeur n'a aucun avis pour le moment."

    return render_template('jouet.html', source_image=source_image, vendeur=prenom_vendeur, id_jouet=id_jouet,
                           note=note, jouet=jouet, statut_jouet=statut_jouet)


@app.route('/ajout_wishlist/<idJouet>', methods=["POST"])
# Route pour ajouter un jouet à la wishlist d'un user à partir du bouton "Ajouter à la wishlist"
def ajout_wishlist(idJouet):
    idUser = g.user.id
    #  Chercher id vendeur de idJouet
    idVendeur = select_vendeur(idJouet)
    wishlist = select_id_jouets_wishlist(idUser)
    id = int(idJouet)

    if idUser != idVendeur:  # Si le jouet n'appartient pas à l'acheteur
        if id not in wishlist:  # Si le jouet n'est pas déjà dans la wishlist de l'acheteur
            insert_wishlist(idJouet, idUser)
            flash("L'article a été ajouté à votre wishlist", "success")
            return redirect(url_for('jouet_individuel', id=idJouet))
        else:
            flash("Ce jouet est déjà dans votre wishlist.", "error")
            return redirect(url_for('jouet_individuel', id=idJouet))
    else:
        flash("Ce jouet vous appartient, vous ne pouvez pas l'ajouter à votre wishlist.", "error")
        return redirect(url_for('jouet_individuel', id=idJouet))


@app.route('/ajout_avis/<idJouet>', methods=["POST"])
# Route pour ajouter un avis à un utilisateur à partir du bouton "Envoyer un avis" dans la page profil.
def ajout_avis(idJouet):
    print(request.form.getlist("msg_avis"))
    message = request.form.getlist("msg_avis")[0]
    message = "'" + message.replace("'", "''") + "'"
    code_transaction = select_transaction(idJouet)[0]
    print(request.form.getlist("note"))
    note = request.form.getlist("note")[0]
    insert_avis(message, note, code_transaction)
    flash("Votre avis a été envoyé!", "success")
    return redirect(url_for("profil"))


@app.route('/reserver_jouet/<idJouet>', methods=["POST"])
# Route pour réserver un jouet à partir du bouton "Réserver" dans la page du jouet individuel.
def reserver_jouet(idJouet):
    idAcheteur = g.user.id
    #  Chercher id vendeur de idJouet
    idVendeur = select_vendeur(idJouet)

    # Chercher le solde de points actuel de l'acheteur
    solde = select_solde_points(idAcheteur)
    # Chercher prix en points du jouet
    prix = get_valeur_points(idJouet)

    if idAcheteur != idVendeur:  # Si le jouet n'appartient pas à l'acheteur
        if solde > prix:  # Si l'acheteur a assez de points pour se procurer ce jouet.
            #  Chercher nom vendeur de idJouet
            nom_vendeur = select_nom_vendeur(idVendeur)

            #  Chercher email vendeur de idJouet
            email_vendeur = select_email_user(idVendeur)

            courriel_acheteur = select_email_user(idAcheteur)

            #  Insert dans transaction
            current_datetime = str(date)
            x = current_datetime.split(".")
            date_transaction = x[0]

            statut = 'Réservé'
            insert_transaction(date_transaction, statut, idAcheteur, idJouet)

            #  Envoyer message au vendeur (https://pythonbasics.org/flask-mail/)
            subject = f"""Votre jouet #{idJouet} a été réservé."""
            msg = Message(subject, sender='petitkangourou23@gmail.com', recipients=[email_vendeur])
            msg.body = f"""Bonjour {nom_vendeur},
                Votre jouet #{idJouet} a été réservé.
                Veuillez entrer en contact avec la personne intéressée.
                Voici son courriel: {courriel_acheteur}"""
            mail.send(msg)
            flash("Votre demande a été envoyé au vendeur", "success")
            return redirect(url_for('jouet_individuel', id=idJouet))

        else:
            flash("Vous ne pouvez pas réserver ce jouet, votre solde de points est inférieur à la valeur du jouet!",
                  "error")
            return redirect(url_for('jouet_individuel', id=idJouet))
    else:
        flash("Ce jouet vous appartient, vous ne pouvez pas le réserver.", "error")
        return redirect(url_for('jouet_individuel', id=idJouet))


@app.route('/vendre_jouet/<idJouet>', methods=["POST"])
def vendre_jouet(idJouet):
    id = get_transaction_id(idJouet)
    update_statut_transaction(id)
    return redirect(url_for("profil"))


if __name__ == '__main__':
    app.run(debug=True)

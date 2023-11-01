"""
 Ref: https://www.youtube.com/watch?v=2Zz97NVbH0U
"""


class Utilisateur:

    def __init__(self, id=None, nom='', prenom='',
                 numeroTelephone='', email='',
                 username='', password='',
                 photoProfil='', codeAddresse=None,
                 numeroCivique=None, rue='', ville='',
                 province='', codePostal='', pays=''):
        self.id = id
        self.nom = nom
        self.prenom = prenom
        self.numeroTelephone = numeroTelephone
        self.email = email
        self.username = username
        self.password = password
        self.photoProfil = photoProfil
        self.codeAddresse = codeAddresse
        self.numeroCivique = numeroCivique
        self.rue = rue
        self.ville = ville
        self.province = province
        self.codePostal = codePostal
        self.pays = pays

    def __repr__(self):
        # Retourne une chaîne de caractères représentant
        # l'instance sous la forme" <User : username>"
        return f'<User:{self.username} >'

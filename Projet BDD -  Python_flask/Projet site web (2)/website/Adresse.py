class Adresse:
    def __init__(self, id=None, numero=None,rue='',ville='',
                 province='', codePostal='', pays=''):
        self.id = id
        self.numero = numero
        self.rue = rue
        self.ville = ville
        self.province = province
        self.codePostal = codePostal
        self.pays = pays
        
        
    def __repr__(self):
        # Retourne une chaîne de caractères représentant
        # l'instance sous la forme" <User : username>"
        return f'<Numero:{self.numero}, Rue:{self.rue} >'

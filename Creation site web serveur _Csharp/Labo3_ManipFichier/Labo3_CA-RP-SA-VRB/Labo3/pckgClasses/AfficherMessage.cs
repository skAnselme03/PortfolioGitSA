using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo3.pckgClasses
{
    /// <summary>Afficher le titre d'un test dans un format précis</summary>
    internal class AfficherMessage
    {
        string titreDuTest; //Le titre du test en question
        string caractere; //Le caracètre d'affichage
        bool commentairePrincipale; //Si vrai affichage format 3 ligne, sinon, une ligne

        //Constructeur
        public AfficherMessage(string titreDuTest, string caractere,
                               bool commentairePrincipale)
        {
            this.titreDuTest = titreDuTest;
            this.caractere = caractere;
            this.commentairePrincipale = commentairePrincipale;
        }

       public AfficherMessage() { }

        // Getter & Setter
        public string getTitreDuTest() { return titreDuTest; }
        public string getCaractere() { return caractere; }
        public bool isCommentairePrincipale() { return commentairePrincipale; }

        //Méthodes
        public void afficher_message()
        {
            string txt = titreDuTest;
            string caract = "";
            for (int i = 0; i < txt.Length; i++)
            {
                caract += caractere;
            }
            if (commentairePrincipale)
            {
                Console.WriteLine("\n");
                Console.WriteLine("/*{0}{0}{0}{0}{1}{0}{0}{0}{0}*/", caractere, caract);
                Console.WriteLine("/*{0}{0}{0} {1} {2}{0}{0}*/", caractere, titreDuTest, caractere);
                Console.WriteLine("/*{0}{0}{0}{0}{1}{0}{0}{0}{0}*/", caractere, caract);
                Console.WriteLine();

            }
            else
            {
                Console.WriteLine("\n");
                Console.WriteLine("/*{0}{0}{0} {1} {2}{0}{0}*/", caractere, titreDuTest, caractere);
                Console.WriteLine();
            }
        }
    }
}

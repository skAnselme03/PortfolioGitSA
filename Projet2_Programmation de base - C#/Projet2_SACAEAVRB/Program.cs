﻿bool programme = true;
bool verification = true;
List<string> prenoms = new List<string>();
List<string> noms = new List<string>();
List<int> salaires = new List<int>();
List<string> departements = new List<string>();
string reponse;


Console.WriteLine("//////////////////////////// INPUTS /////////////////////////////////////");
while (programme == true)
{
    // Entrer prénom
    Console.Write("Saisissez le prénom de l'employé:");
    prenoms.Add(Console.ReadLine());


    // Entrer nom
    Console.Write("Saisissez le nom de l'employé:");
    noms.Add(Console.ReadLine());


    // Entrer salaire
    while (verification == true)
    {
        Console.Write("Saisissez le salaire de l'employé:");
        int rep = Convert.ToInt32(Console.ReadLine());
        if (rep >= 0)
        {
            salaires.Add(rep);
            verification = false;
        }
        else
        {
            Console.WriteLine("Valeur inadéquate, le salaire ne peut pas être une valeur négative.");
        }
    }
    verification = true;


    // Entrer département
    while (verification == true)
    {
        Console.Write("Saisissez le département de l'employé:");
        string temp = Console.ReadLine();
        temp = temp.ToUpper();


        if (temp == "IT" || temp == "MARKETING" || temp == "HR")
        {
            departements.Add(temp);
            verification = false;
        }
        else
        {
            Console.WriteLine("Valeur inadéquate, veuillez réessayer (Les choix sont de IT, MARKETING, ou HR)");
        }
    }
    verification = true;
    Console.WriteLine();


    // Continuer - oui ou non
    int erreurs = 0;
    while (verification)
    {
        Console.Write("Voulez-vous saisir un autre employé oui / non?");
        string rep = Console.ReadLine();
        rep = rep.ToUpper();
        if (rep != "OUI" && rep != "NON")
        {
            erreurs++;
            Console.WriteLine("Erreur de saisie - veuillez écrire OUI ou NON");
            if (erreurs == 3)
            {
                verification = false;
                programme = false;
            }
        }
        else if (rep == "NON")
        {
            verification = false;
            programme = false;
        }
        else verification = false;
    }
    verification = true;
}


Console.WriteLine("//////////////////////////// OUTPUTS /////////////////////////////////////");
// Calcul salaires par départements
double salaireIT = 0;
double salaireMK = 0;
double salaireHR = 0;
int nbIT = 0;
int nbMK = 0;
int nbHR = 0;


for (int i = 0; i < salaires.Count; i++)
{
    if (departements[i] == "IT")
    {
        salaireIT += (Double)salaires[i];
        nbIT++;
    }


    if (departements[i] == "MARKETING")
    {
        salaireMK += (Double)salaires[i];
        nbMK++;
    }


    if (departements[i] == "HR")
    {
        salaireHR += (Double)salaires[i];
        nbHR++;
    }
}


int total = 0;
foreach (int element in salaires)
{
    total += element;
}
double moyenneTous = total / salaires.Count;


// les outputs
// format 2 chiffres après la virgules.
Console.WriteLine("Moyenne Salaire pour tous les départements c'est : " + moyenneTous);
Console.WriteLine("Max Salaire pour tous les départements c'est : " + "{0:0.00}", salaires.Max());
Console.WriteLine("Min Salaire pour tous les départements c'est : " + "{0:0.00}", salaires.Min());
Console.WriteLine();
Console.Write("Moyenne Salaire pour département IT : ");
if (salaireIT == 0)
{
    Console.WriteLine("Pas disponible");
}
else
{
    salaireIT /= nbIT;
    salaireIT = Math.Round(salaireIT, 2);
    Console.WriteLine("{0:0.00}", salaireIT); //Faire une fonction qui renvoie ce format avec 2 chiffres après la virgules
}

Console.Write("Moyenne Salaire pour département Marketing : ");
if (salaireMK == 0)
{
    Console.WriteLine("Pas disponible");
}
else
{
    salaireMK /= nbMK;
    salaireMK = Math.Round(salaireMK, 2);
    Console.WriteLine("{0:0.00}", salaireMK);
}
Console.Write("Moyenne Salaire pour département HR : ");
if (salaireHR == 0)
{
    Console.WriteLine("Pas disponible");
}
else
{
    salaireHR /= nbHR;
    salaireHR = Math.Round(salaireHR, 2);
    Console.WriteLine("{0:0.00}",salaireHR);
}
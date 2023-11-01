//bool programme = true;
//bool verification = true;
//List<string> prenoms = new List<string>();
//List<string> noms = new List<string>();
//List<int> salaires = new List<int>();
//List<string> departements = new List<string>();
//string reponse;
//double[] tabSalaireIT;
//double[] tabSalaireHR;
//double[] tabSalaireMarketing;
//int estHr = 0;
//int estIT = 0;
//int estMarketing = 0;



//Console.WriteLine("//////////////////////////// INPUTS /////////////////////////////////////");
//while (programme == true)
//{
//    // Entrer prénom
//    Console.WriteLine("Saisissez le prénom de l'employé:");
//    prenoms.Add(Console.ReadLine());


//    // Entrer nom
//    Console.WriteLine("Saisissez le nom de l'employé:");
//    noms.Add(Console.ReadLine());


//    // Entrer salaire
//    while (verification == true)
//    {
//        Console.WriteLine("Saisissez le salaire de l'employé:");
//        int rep = Convert.ToInt32(Console.ReadLine());
//        if (rep >= 0)
//        {
//            salaires.Add(rep);
//            verification = false;
//        }
//        else
//        {
//            Console.WriteLine("Valeur inadéquate, le salaire ne peut pas être une valeur négative.");
//        }
//    }
//    verification = true;


//    // Entrer département
//    while (verification == true)
//    {
//        Console.WriteLine("Saisissez le département de l'employé:");
//        string temp = Console.ReadLine();
//        temp = temp.ToUpper();


//        if (temp == "IT" || temp == "MARKETING" || temp == "HR")
//        {
//            departements.Add(temp);
//            verification = false;
//        }
//        else
//        {
//            Console.WriteLine("Valeur inadéquate, veuillez réessayer (Les choix sont de IT, MARKETING, ou HR)");
//        }
//    }
//    verification = true;


//    // Continuer - oui ou non
//    int erreurs = 0;
//    while (verification)
//    {
//        Console.WriteLine("Voulez-vous saisir un autre employé oui / non?");
//        string rep = Console.ReadLine();
//        rep = rep.ToUpper();
//        if (rep != "OUI" && rep != "NON")
//        {
//            erreurs++;
//            Console.WriteLine("Erreur de saisie - veuillez écrire OUI ou NON");
//            if (erreurs == 4)
//            {
//                verification = false;
//                programme = false;
//            }
//        }
//        else if (rep == "NON")
//        {
//            verification = false;
//            programme = false;
//        }
//        else verification = false;
//    }
//    verification = true;
//}

//public double tabSalaire(int salaire, string departement)
//{
//    double[] tabSalaire;

//    switch (departement)
//    {
//        case "it":
//            break;
//        case "hr":
//            break;
//        case "MARKETING":
//            break ;
//    }

//    return tabSalaire;
//}

//Console.WriteLine("//////////////////////////// OUTPUTS /////////////////////////////////////");
//for (int i = 0; i < prenoms.Count; i++)
//{
//    Console.WriteLine("Prénom de l'employé : " + prenoms[i]);
//    Console.WriteLine("Nom de l'employé : " + noms[i]);
//    Console.WriteLine("Salaire de l'employé : " + salaires[i]);
//    Console.WriteLine("Département de l'employé : " + departements[i]);
//    Console.WriteLine();
//}
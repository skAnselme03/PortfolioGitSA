/********************************************************************************
** Form generated from reading UI file 'OuvrageInterface.ui'
**
** Created by: Qt User Interface Compiler version 5.12.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_OUVRAGEINTERFACE_H
#define UI_OUVRAGEINTERFACE_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpinBox>

QT_BEGIN_NAMESPACE

class Ui_OuvrageInterface
{
public:
    QLabel *lblAuteur;
    QLineEdit *auteur;
    QLineEdit *titre;
    QLabel *lblTitre;
    QLabel *lblAnneeEdition;
    QSpinBox *annee;
    QLabel *lblVilleEdition;
    QLineEdit *ville;
    QLabel *lblNomEditeur;
    QLineEdit *editeur;
    QLabel *lblCodeISBN;
    QLineEdit *codeISBN;
    QPushButton *enregistrerOuvrage;

    void setupUi(QDialog *OuvrageInterface)
    {
        if (OuvrageInterface->objectName().isEmpty())
            OuvrageInterface->setObjectName(QString::fromUtf8("OuvrageInterface"));
        OuvrageInterface->resize(649, 423);
        lblAuteur = new QLabel(OuvrageInterface);
        lblAuteur->setObjectName(QString::fromUtf8("lblAuteur"));
        lblAuteur->setGeometry(QRect(20, 20, 181, 32));
        QFont font;
        font.setPointSize(10);
        font.setBold(true);
        font.setWeight(75);
        lblAuteur->setFont(font);
        lblAuteur->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        auteur = new QLineEdit(OuvrageInterface);
        auteur->setObjectName(QString::fromUtf8("auteur"));
        auteur->setGeometry(QRect(210, 20, 421, 32));
        titre = new QLineEdit(OuvrageInterface);
        titre->setObjectName(QString::fromUtf8("titre"));
        titre->setGeometry(QRect(210, 70, 421, 32));
        lblTitre = new QLabel(OuvrageInterface);
        lblTitre->setObjectName(QString::fromUtf8("lblTitre"));
        lblTitre->setGeometry(QRect(20, 70, 181, 32));
        lblTitre->setFont(font);
        lblTitre->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        lblAnneeEdition = new QLabel(OuvrageInterface);
        lblAnneeEdition->setObjectName(QString::fromUtf8("lblAnneeEdition"));
        lblAnneeEdition->setGeometry(QRect(20, 220, 181, 32));
        lblAnneeEdition->setFont(font);
        lblAnneeEdition->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        annee = new QSpinBox(OuvrageInterface);
        annee->setObjectName(QString::fromUtf8("annee"));
        annee->setGeometry(QRect(210, 220, 101, 32));
        annee->setMaximum(9999);
        lblVilleEdition = new QLabel(OuvrageInterface);
        lblVilleEdition->setObjectName(QString::fromUtf8("lblVilleEdition"));
        lblVilleEdition->setGeometry(QRect(20, 120, 181, 32));
        lblVilleEdition->setFont(font);
        lblVilleEdition->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        ville = new QLineEdit(OuvrageInterface);
        ville->setObjectName(QString::fromUtf8("ville"));
        ville->setGeometry(QRect(210, 120, 421, 32));
        lblNomEditeur = new QLabel(OuvrageInterface);
        lblNomEditeur->setObjectName(QString::fromUtf8("lblNomEditeur"));
        lblNomEditeur->setGeometry(QRect(20, 170, 181, 32));
        lblNomEditeur->setFont(font);
        lblNomEditeur->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        editeur = new QLineEdit(OuvrageInterface);
        editeur->setObjectName(QString::fromUtf8("editeur"));
        editeur->setGeometry(QRect(210, 170, 421, 32));
        lblCodeISBN = new QLabel(OuvrageInterface);
        lblCodeISBN->setObjectName(QString::fromUtf8("lblCodeISBN"));
        lblCodeISBN->setGeometry(QRect(20, 270, 181, 32));
        lblCodeISBN->setFont(font);
        lblCodeISBN->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        codeISBN = new QLineEdit(OuvrageInterface);
        codeISBN->setObjectName(QString::fromUtf8("codeISBN"));
        codeISBN->setGeometry(QRect(210, 270, 421, 32));
        enregistrerOuvrage = new QPushButton(OuvrageInterface);
        enregistrerOuvrage->setObjectName(QString::fromUtf8("enregistrerOuvrage"));
        enregistrerOuvrage->setGeometry(QRect(250, 350, 191, 51));
        QFont font1;
        font1.setPointSize(12);
        font1.setBold(true);
        font1.setWeight(75);
        enregistrerOuvrage->setFont(font1);
        enregistrerOuvrage->setAutoDefault(true);
        enregistrerOuvrage->setFlat(false);
        QWidget::setTabOrder(auteur, titre);
        QWidget::setTabOrder(titre, ville);
        QWidget::setTabOrder(ville, editeur);
        QWidget::setTabOrder(editeur, annee);
        QWidget::setTabOrder(annee, codeISBN);
        QWidget::setTabOrder(codeISBN, enregistrerOuvrage);

        retranslateUi(OuvrageInterface);
        QObject::connect(enregistrerOuvrage, SIGNAL(clicked()), OuvrageInterface, SLOT(validerEnregistrement()));

        enregistrerOuvrage->setDefault(false);


        QMetaObject::connectSlotsByName(OuvrageInterface);
    } // setupUi

    void retranslateUi(QDialog *OuvrageInterface)
    {
        OuvrageInterface->setWindowTitle(QApplication::translate("OuvrageInterface", "OuvrageInterface", nullptr));
        lblAuteur->setText(QApplication::translate("OuvrageInterface", "Nom auteur : ", nullptr));
        lblTitre->setText(QApplication::translate("OuvrageInterface", "Titre de  l'ouvrage : ", nullptr));
        lblAnneeEdition->setText(QApplication::translate("OuvrageInterface", "Ann\303\251e d'\303\251dition : ", nullptr));
        lblVilleEdition->setText(QApplication::translate("OuvrageInterface", "Ville d'\303\251dition : ", nullptr));
        lblNomEditeur->setText(QApplication::translate("OuvrageInterface", "Nom de l'\303\251diteur : ", nullptr));
        lblCodeISBN->setText(QApplication::translate("OuvrageInterface", "Code ISBN : ", nullptr));
        enregistrerOuvrage->setText(QApplication::translate("OuvrageInterface", "AJOUTER", nullptr));
    } // retranslateUi

};

namespace Ui {
    class OuvrageInterface: public Ui_OuvrageInterface {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_OUVRAGEINTERFACE_H

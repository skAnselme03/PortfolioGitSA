/********************************************************************************
** Form generated from reading UI file 'JournalInterface.ui'
**
** Created by: Qt User Interface Compiler version 5.12.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_JOURNALINTERFACE_H
#define UI_JOURNALINTERFACE_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpinBox>

QT_BEGIN_NAMESPACE

class Ui_JournalInterface
{
public:
    QLabel *lblAuteur;
    QLineEdit *auteur;
    QLineEdit *titre;
    QLabel *lblTitre;
    QLabel *lblNom;
    QLineEdit *nom;
    QLabel *lblVolume;
    QSpinBox *volume;
    QLabel *lblNumero;
    QSpinBox *numero;
    QLabel *lblPage;
    QSpinBox *page;
    QLabel *lblCodeISSN;
    QLineEdit *codeISSN;
    QPushButton *ajouterJournal;
    QLabel *lblAnnee;
    QSpinBox *annee;

    void setupUi(QDialog *JournalInterface)
    {
        if (JournalInterface->objectName().isEmpty())
            JournalInterface->setObjectName(QString::fromUtf8("JournalInterface"));
        JournalInterface->resize(789, 499);
        lblAuteur = new QLabel(JournalInterface);
        lblAuteur->setObjectName(QString::fromUtf8("lblAuteur"));
        lblAuteur->setGeometry(QRect(20, 20, 132, 32));
        QFont font;
        font.setBold(true);
        font.setWeight(75);
        lblAuteur->setFont(font);
        lblAuteur->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        auteur = new QLineEdit(JournalInterface);
        auteur->setObjectName(QString::fromUtf8("auteur"));
        auteur->setGeometry(QRect(160, 20, 601, 32));
        titre = new QLineEdit(JournalInterface);
        titre->setObjectName(QString::fromUtf8("titre"));
        titre->setGeometry(QRect(160, 70, 601, 32));
        lblTitre = new QLabel(JournalInterface);
        lblTitre->setObjectName(QString::fromUtf8("lblTitre"));
        lblTitre->setGeometry(QRect(20, 70, 132, 32));
        lblTitre->setFont(font);
        lblTitre->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        lblNom = new QLabel(JournalInterface);
        lblNom->setObjectName(QString::fromUtf8("lblNom"));
        lblNom->setGeometry(QRect(20, 120, 132, 32));
        lblNom->setFont(font);
        lblNom->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        nom = new QLineEdit(JournalInterface);
        nom->setObjectName(QString::fromUtf8("nom"));
        nom->setGeometry(QRect(160, 120, 601, 32));
        lblVolume = new QLabel(JournalInterface);
        lblVolume->setObjectName(QString::fromUtf8("lblVolume"));
        lblVolume->setGeometry(QRect(20, 170, 132, 32));
        lblVolume->setFont(font);
        lblVolume->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        volume = new QSpinBox(JournalInterface);
        volume->setObjectName(QString::fromUtf8("volume"));
        volume->setGeometry(QRect(160, 170, 72, 32));
        volume->setMaximum(999999);
        lblNumero = new QLabel(JournalInterface);
        lblNumero->setObjectName(QString::fromUtf8("lblNumero"));
        lblNumero->setGeometry(QRect(20, 220, 132, 32));
        lblNumero->setFont(font);
        lblNumero->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        numero = new QSpinBox(JournalInterface);
        numero->setObjectName(QString::fromUtf8("numero"));
        numero->setGeometry(QRect(160, 220, 72, 32));
        numero->setMaximum(999999);
        lblPage = new QLabel(JournalInterface);
        lblPage->setObjectName(QString::fromUtf8("lblPage"));
        lblPage->setGeometry(QRect(20, 270, 132, 32));
        lblPage->setFont(font);
        lblPage->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        page = new QSpinBox(JournalInterface);
        page->setObjectName(QString::fromUtf8("page"));
        page->setGeometry(QRect(160, 270, 72, 32));
        page->setMaximum(999999);
        lblCodeISSN = new QLabel(JournalInterface);
        lblCodeISSN->setObjectName(QString::fromUtf8("lblCodeISSN"));
        lblCodeISSN->setGeometry(QRect(20, 370, 132, 32));
        lblCodeISSN->setFont(font);
        lblCodeISSN->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        codeISSN = new QLineEdit(JournalInterface);
        codeISSN->setObjectName(QString::fromUtf8("codeISSN"));
        codeISSN->setGeometry(QRect(160, 370, 601, 32));
        ajouterJournal = new QPushButton(JournalInterface);
        ajouterJournal->setObjectName(QString::fromUtf8("ajouterJournal"));
        ajouterJournal->setGeometry(QRect(300, 430, 171, 51));
        ajouterJournal->setFont(font);
        lblAnnee = new QLabel(JournalInterface);
        lblAnnee->setObjectName(QString::fromUtf8("lblAnnee"));
        lblAnnee->setGeometry(QRect(20, 320, 132, 32));
        lblAnnee->setFont(font);
        lblAnnee->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);
        annee = new QSpinBox(JournalInterface);
        annee->setObjectName(QString::fromUtf8("annee"));
        annee->setGeometry(QRect(160, 320, 72, 32));
        annee->setMaximum(9999);
        QWidget::setTabOrder(auteur, titre);
        QWidget::setTabOrder(titre, nom);
        QWidget::setTabOrder(nom, volume);
        QWidget::setTabOrder(volume, numero);
        QWidget::setTabOrder(numero, page);
        QWidget::setTabOrder(page, annee);
        QWidget::setTabOrder(annee, codeISSN);
        QWidget::setTabOrder(codeISSN, ajouterJournal);

        retranslateUi(JournalInterface);
        QObject::connect(ajouterJournal, SIGNAL(clicked()), JournalInterface, SLOT(validerEnregistrement()));

        QMetaObject::connectSlotsByName(JournalInterface);
    } // setupUi

    void retranslateUi(QDialog *JournalInterface)
    {
        JournalInterface->setWindowTitle(QApplication::translate("JournalInterface", "JournalInterface", nullptr));
        lblAuteur->setText(QApplication::translate("JournalInterface", "Auteur : ", nullptr));
        lblTitre->setText(QApplication::translate("JournalInterface", "Titre du journal : ", nullptr));
        lblNom->setText(QApplication::translate("JournalInterface", "Nom du journal : ", nullptr));
        lblVolume->setText(QApplication::translate("JournalInterface", "Volume : ", nullptr));
        lblNumero->setText(QApplication::translate("JournalInterface", "Num\303\251ro : ", nullptr));
        lblPage->setText(QApplication::translate("JournalInterface", "Page : ", nullptr));
        lblCodeISSN->setText(QApplication::translate("JournalInterface", "Code ISSN : ", nullptr));
        ajouterJournal->setText(QApplication::translate("JournalInterface", "AJOUTER", nullptr));
        lblAnnee->setText(QApplication::translate("JournalInterface", "Ann\303\251e : ", nullptr));
    } // retranslateUi

};

namespace Ui {
    class JournalInterface: public Ui_JournalInterface {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_JOURNALINTERFACE_H

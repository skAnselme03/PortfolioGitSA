/********************************************************************************
** Form generated from reading UI file 'BibliographieInterface.ui'
**
** Created by: Qt User Interface Compiler version 5.12.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_BIBLIOGRAPHIEINTERFACE_H
#define UI_BIBLIOGRAPHIEINTERFACE_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_BibliographieInterface
{
public:
    QAction *actionSupprimer_une_reference;
    QAction *actionQuitter;
    QAction *actionOuvrage;
    QAction *actionJournal;
    QWidget *centralwidget;
    QTextEdit *affichageBiblio;
    QMenuBar *menubar;
    QMenu *menuOperations;
    QMenu *menuAjouter_une_reference;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *BibliographieInterface)
    {
        if (BibliographieInterface->objectName().isEmpty())
            BibliographieInterface->setObjectName(QString::fromUtf8("BibliographieInterface"));
        BibliographieInterface->resize(697, 515);
        actionSupprimer_une_reference = new QAction(BibliographieInterface);
        actionSupprimer_une_reference->setObjectName(QString::fromUtf8("actionSupprimer_une_reference"));
        actionQuitter = new QAction(BibliographieInterface);
        actionQuitter->setObjectName(QString::fromUtf8("actionQuitter"));
        actionOuvrage = new QAction(BibliographieInterface);
        actionOuvrage->setObjectName(QString::fromUtf8("actionOuvrage"));
        actionJournal = new QAction(BibliographieInterface);
        actionJournal->setObjectName(QString::fromUtf8("actionJournal"));
        centralwidget = new QWidget(BibliographieInterface);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        affichageBiblio = new QTextEdit(centralwidget);
        affichageBiblio->setObjectName(QString::fromUtf8("affichageBiblio"));
        affichageBiblio->setGeometry(QRect(20, 30, 651, 431));
        BibliographieInterface->setCentralWidget(centralwidget);
        menubar = new QMenuBar(BibliographieInterface);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 697, 22));
        menuOperations = new QMenu(menubar);
        menuOperations->setObjectName(QString::fromUtf8("menuOperations"));
        menuAjouter_une_reference = new QMenu(menuOperations);
        menuAjouter_une_reference->setObjectName(QString::fromUtf8("menuAjouter_une_reference"));
        BibliographieInterface->setMenuBar(menubar);
        statusbar = new QStatusBar(BibliographieInterface);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        BibliographieInterface->setStatusBar(statusbar);

        menubar->addAction(menuOperations->menuAction());
        menuOperations->addAction(menuAjouter_une_reference->menuAction());
        menuOperations->addAction(actionSupprimer_une_reference);
        menuOperations->addAction(actionQuitter);
        menuAjouter_une_reference->addAction(actionOuvrage);
        menuAjouter_une_reference->addAction(actionJournal);

        retranslateUi(BibliographieInterface);
        QObject::connect(actionQuitter, SIGNAL(triggered()), BibliographieInterface, SLOT(close()));
        QObject::connect(actionOuvrage, SIGNAL(triggered()), BibliographieInterface, SLOT(dialogOuvrage()));
        QObject::connect(actionJournal, SIGNAL(triggered()), BibliographieInterface, SLOT(dialogJournal()));
        QObject::connect(actionSupprimer_une_reference, SIGNAL(triggered()), BibliographieInterface, SLOT(dialogSupprimer()));

        QMetaObject::connectSlotsByName(BibliographieInterface);
    } // setupUi

    void retranslateUi(QMainWindow *BibliographieInterface)
    {
        BibliographieInterface->setWindowTitle(QApplication::translate("BibliographieInterface", "BibliographieInterface", nullptr));
        actionSupprimer_une_reference->setText(QApplication::translate("BibliographieInterface", "Supprimer une reference", nullptr));
        actionQuitter->setText(QApplication::translate("BibliographieInterface", "Quitter", nullptr));
        actionOuvrage->setText(QApplication::translate("BibliographieInterface", "Ouvrage", nullptr));
        actionJournal->setText(QApplication::translate("BibliographieInterface", "Journal", nullptr));
        menuOperations->setTitle(QApplication::translate("BibliographieInterface", "Operations", nullptr));
        menuAjouter_une_reference->setTitle(QApplication::translate("BibliographieInterface", "Ajouter une reference", nullptr));
    } // retranslateUi

};

namespace Ui {
    class BibliographieInterface: public Ui_BibliographieInterface {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_BIBLIOGRAPHIEINTERFACE_H

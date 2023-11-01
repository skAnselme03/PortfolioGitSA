/********************************************************************************
** Form generated from reading UI file 'SupprimerUneReference.ui'
**
** Created by: Qt User Interface Compiler version 5.12.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SUPPRIMERUNEREFERENCE_H
#define UI_SUPPRIMERUNEREFERENCE_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>

QT_BEGIN_NAMESPACE

class Ui_SupprimerUneReference
{
public:
    QLabel *label;
    QLineEdit *idASupprimer;
    QPushButton *supprimer;

    void setupUi(QDialog *SupprimerUneReference)
    {
        if (SupprimerUneReference->objectName().isEmpty())
            SupprimerUneReference->setObjectName(QString::fromUtf8("SupprimerUneReference"));
        SupprimerUneReference->resize(418, 172);
        label = new QLabel(SupprimerUneReference);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(20, 30, 371, 31));
        idASupprimer = new QLineEdit(SupprimerUneReference);
        idASupprimer->setObjectName(QString::fromUtf8("idASupprimer"));
        idASupprimer->setGeometry(QRect(20, 60, 361, 28));
        idASupprimer->setAutoFillBackground(false);
        supprimer = new QPushButton(SupprimerUneReference);
        supprimer->setObjectName(QString::fromUtf8("supprimer"));
        supprimer->setGeometry(QRect(20, 120, 90, 28));

        retranslateUi(SupprimerUneReference);
        QObject::connect(supprimer, SIGNAL(clicked()), SupprimerUneReference, SLOT(validerSupprimer()));

        QMetaObject::connectSlotsByName(SupprimerUneReference);
    } // setupUi

    void retranslateUi(QDialog *SupprimerUneReference)
    {
        SupprimerUneReference->setWindowTitle(QApplication::translate("SupprimerUneReference", "SupprimerUneReference", nullptr));
        label->setText(QApplication::translate("SupprimerUneReference", "Entreez le Num\303\251ro ISSN ou ISBN de la r\303\251f\303\251rence \303\240 supprimer:", nullptr));
        supprimer->setText(QApplication::translate("SupprimerUneReference", "Supprimer", nullptr));
    } // retranslateUi

};

namespace Ui {
    class SupprimerUneReference: public Ui_SupprimerUneReference {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SUPPRIMERUNEREFERENCE_H

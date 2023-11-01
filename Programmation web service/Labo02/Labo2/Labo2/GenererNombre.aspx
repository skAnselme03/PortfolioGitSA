<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="GenererNombre.aspx.cs" Inherits="Labo2.GenererNombre" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Génerer un nombre quelconque</title>
    <link  rel="stylesheet" href="style.css" type="text/css"/>
</head>
<body>
    <h1>Génerer un nombre </h1>
    <form id="form1" runat="server">
        <section>
            <asp:Label ID="MinLbl" runat="server" Text="Minimum : "></asp:Label>
            <asp:TextBox ID="MinTxtB" runat="server"></asp:TextBox>
        </section>
        <section>
            <asp:Label ID="MaxLbl" runat="server" Text="Maximum : "></asp:Label>
            <asp:TextBox ID="MaxTxtB" runat="server"></asp:TextBox>
        </section>
        <section>
            <asp:Label ID="Reslbl" runat="server" Text=""></asp:Label>
        </section>
        <asp:Button ID="BtnGenerer" runat="server" Text="Générer" Onclick="BtnGenerer_Click"/>
    </form>
</body>
</html>

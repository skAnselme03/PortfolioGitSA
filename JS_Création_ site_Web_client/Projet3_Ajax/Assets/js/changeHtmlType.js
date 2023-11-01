function changerTypeHtml(strID, strType) {
    let objb = document.getElementById(strID);
    if (objb == null) {
        alert('Attention... balise "' + strID + '" inexistante !');
    } else {
        if (strType != null) {
            objb.setAttribute('type', strType);
        } else {
            return objb.type;
        }
    }
}
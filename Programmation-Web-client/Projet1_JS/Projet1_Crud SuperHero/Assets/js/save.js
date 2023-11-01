function Telecharger(table_id = "id1", separator = ",") {

    let rows = document.querySelectorAll("table#" + table_id + " tr");

    let csv = [];

    for (let i = 0; i < rows.length; i++) {
        let row = [],
            cols = rows[i].querySelectorAll("td, th");

        for (let j = 0; j < cols.length; j++) {
            let data = cols[j].innerText.replace(/(\r\n|\n|\r)/gm, "").replace(/(\s\s)/gm, " ")
            data = data.replace(/"/g, `""`);
            row.push(`"` + data + `"`);
        }
        csv.push(row.join(separator));
    }
    let csv_string = csv.join("\n");
    let filename = "export_" + table_id + "_" + new Date().toLocaleDateString() + ".csv";
    let link = document.createElement("a");
    link.style.display = "none";
    link.setAttribute("target", "_blank");
    link.setAttribute("href", "data:text/csv;charset=utf-8," + encodeURIComponent(csv_string));
    link.setAttribute("download", filename);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}
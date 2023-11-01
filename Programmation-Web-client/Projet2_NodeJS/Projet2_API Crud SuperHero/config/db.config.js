'use strict';
const mysql = require('mysql');
const dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'userCrud',
    password: 'crud123_oer223',
    database: 'bd_superhero'
});
dbConn.connect(function(err) {
    if (err) throw err;
    console.log("BD connectée!");
});
module.exports = dbConn;
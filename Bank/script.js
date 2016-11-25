function readLoginData() {
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;

    if (login == "123" && password == "456") {
        window.location.href = "file:///home/marcin/Programming/krypto/Kryptografia/Bank/transfer.html";

    }
}

function readTransferData() {
    var account = document.getElementById("account").value;
    var text = document.getElementById("transferText").value;
    var amount = document.getElementById("amount").value;
    window.location.href = "file:///home/marcin/Programming/krypto/Kryptografia/Bank/confirmation.html";

    document.getElementById("account").innerHTML = account;
    document.getElementById("transferText").innerHTML = transferText;
    document.getElementById("amount").innerHTML = amount;
}

function confirm(){
  var oTable = document.getElementById("report").value;

  var rowLength = oTable.rows.length;

//loops through rows
for (i = 0; i < rowLength; i++){

   //gets cells of current row
   var oCells = oTable.rows.item(i).cells;

   //gets amount of cells of current row
   var cellLength = oCells.length;

   //loops through each cell in current row
   for(var j = 0; j < cellLength; j++){
      console.log(oCells.value);
   }
}

}

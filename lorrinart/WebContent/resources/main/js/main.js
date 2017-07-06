var globalCount=0;
var navbar = document.getElementsByClassName('navbar');
function myFunction() {
	document.getElementById("demo").innerHTML = "Paragraph changed.";
	console.log("This is Demo");
}

function isAllChecked() {
	var client = document.getElementById('clients').getElementsByClassName('client');
	
	if(document.getElementById('selectAll').checked){
	for(var i=1; i<client.length;i++){
		client[i].childNodes[0].childNodes[0].checked=true;
		client[i].classList.add("tr-selected");
		//client[i].setAttribute("selected","true");
		navbar[0].style.display = 'block';
	}
	globalCount=client.length-1;
	
	}else if(!(document.getElementById('selectAll').checked))	{
		
		for(var i=1; i<client.length;i++){
			client[i].childNodes[0].childNodes[0].checked=false;
			client[i].classList.remove("tr-selected");
	//		client[i].setAttribute("selected","false");
		
		globalCount=0;
			}
		}
	selectCounter();
	}


function isChecked(elmnt) {
	console.log('clicked');
	if (elmnt.classList.contains("tr-selected")) {	
		elmnt.childNodes[0].childNodes[0].checked=false;
	elmnt.classList.remove("tr-selected");
	//	console.log(elmnt.classList);
	//	globalCount-=1;
	} else {
	elmnt.childNodes[0].childNodes[0].checked=true;
	elmnt.classList.add("tr-selected");
	//	console.log(elmnt.classList);
//		globalCount+=1;
	}
	selectCounter();
}

function selectCounter(){
	if(globalCount>0){
		navbar[0].style.display = 'block';
		console.log('numarul '+ globalCount);
	}else{
		navbar[0].style.display = 'none';
		console.log('numarul '+ globalCount);
	}
	
}

function allClients(jsonClients) {
	console.log(jsonClients);
	var i, x = "";
	// var myObj =
	// [{"client_id":8,"nameOne":"sss","nameTwo":"sss","nameOneImage":"sss","nameTwoImage":"sss","linkOne":"sss","linkTwo":"sss","username":"dsadas","clientPass":"sss"},{"client_id":11,"nameOne":"sss","nameTwo":"sss","nameOneImage":"sss","nameTwoImage":"sss","linkOne":"sss","linkTwo":"sss","username":"dsadassadasdasss","clientPass":"ssss"},{"client_id":13,"nameOne":"sss","nameTwo":"sss","nameOneImage":"sss","nameTwoImage":"sss","linkOne":"sss","linkTwo":"sss","username":"dsada","clientPass":"sssss"},{"client_id":14,"nameOne":"nameOne","nameTwo":"nameTwo","nameOneImage":"nameOneImage","nameTwoImage":"nameTwoImage","linkOne":"linkOne","linkTwo":"linkTwo","username":"dsdasdasdsadas","clientPass":"clientPasss"},{"client_id":9,"nameOne":"nameOne","nameTwo":"nameTwo","nameOneImage":"nameOneImage","nameTwoImage":"nameTwoImage","linkOne":"linkOne","linkTwo":"linkTwo","username":"dsaddsasdasda","clientPass":"clientPass"}];
	var myObjs = JSON.parse(myObj);
	console.log("THIS IS cu S: " + myObjs);
	console.log("THIS IS: " + myObj);
	console.log("THIS IS: " + x);
	for (i = 0; i < myObj.length; i++) {
		var obj = myObj[i];
		x += "<h2>" + obj.client_id + obj.nameOne + "</h2>";
		console.log(x);
	}

	document.getElementById("All").innerHTML = x;
}

function showTab(evt, button) {
	var i, tabcontent, tablinks;
	// var json = JSON.parse(data);
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";

	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}

	console.log("before active");
	evt.currentTarget.className += " active";
	console.log("bedore style display block");
	document.getElementById(button).style.display = "block";
}

function ready() {
	console.log("before default open");
	document.getElementById("defaultOpen").click();
}

document.onreadystatechange = function() {
	if (document.readyState === "complete") {
		ready();
	}
};

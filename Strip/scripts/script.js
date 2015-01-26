function changeTitle(){
	var text = document.getElementById('titleText');
	var x = text.value
	var z = document.getElementsByClassName('title');
	var i;
	for(i=0;i<z.length;i++){
		z[i].innerHTML = x;
	}
}

function changePosLeft(){
	var x = document.getElementById('leftmid');
	x.style.textAlign = "left";
	var z = document.getElementsByClassName('title');
	var i=0;
	for(i=0;i<z.length;i++){
		z[i].style.textAlign = "left";
	}
	document.getElementById("ce").checked = false;
	document.getElementById("ri").checked = false;
}

function changePosMid(){
	var x = document.getElementById('leftmid');
	x.style.textAlign = "center";
	var z = document.getElementsByClassName('title');
	var i=0;
	for(i=0;i<z.length;i++){
		z[i].style.textAlign = "center";
	}
	document.getElementById("le").checked = false;
	document.getElementById("ri").checked = false;
}

function changePosRight(){
	var x = document.getElementById('leftmid');
	x.style.textAlign = "right";
	var z = document.getElementsByClassName('title');
	var i=0;
	for(i=0;i<z.length;i++){
		z[i].style.textAlign = "right";
	}
	document.getElementById("ce").checked = false;
	document.getElementById("le").checked = false;
}

function color(){
	document.getElementById('red').max = 255;
	document.getElementById('green').max = 255;
	document.getElementById('blue').max = 255;
	document.getElementById('opac').max = 100;

	var r = document.getElementById('red').value;
	console.log("red: "+r);
	var g = document.getElementById('green').value;
	console.log("green: "+g);
	var b = document.getElementById('blue').value;
	console.log("blue: "+b);
	var o = document.getElementById('opac').value;
	console.log("opacity: "+o);

	o = o/100;

	var br8k = "rgba("+r+","+g+","+b+","+o+")"

	document.getElementById('left').style.backgroundColor = br8k;
	document.getElementById('mid').style.backgroundColor = br8k;
	document.getElementById('right').style.backgroundColor = br8k;

	if(r>=255 && g>=255 && b>=255){
		document.getElementById('left').style.color = "black";
		document.getElementById('mid').style.color = "black";
		document.getElementById('right').style.color = "black";
		document.getElementById('titleText').style.border = "1px solid black"
		document.getElementById('txtrea').style.border = "1px solid black"
		
	}
	else{
		document.getElementById('left').style.color = "white";
		document.getElementById('mid').style.color = "white";
		document.getElementById('right').style.color = "white";
		document.getElementById('titleText').style.border = "0px"
		document.getElementById('txtrea').style.border = "0px"
		
	}
}

function text(){
	var x = document.getElementById('txtrea').value;
	document.getElementById('text').innerHTML = x;
}

function theme(){
	var x = document.getElementById('selector');
	var url = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/animals.jpeg)";
	var smurl = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/animalsmall.jpeg)";

	if(x.value == "Animals"){
		url = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/animals.jpeg)";
		smurl = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/animalsmall.jpeg)";
	}
	else if(x.value == "Food"){
		url = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/food.jpeg)";
		smurl = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/foodsmall.jpeg)";
	}
	else if(x.value == "Sports"){
		url = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/sports.jpeg)";
		smurl = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/sportsmall.jpeg)";
	}
	else if(x.value == "People"){
		url = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/people.jpeg)";
		smurl = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/peoplesmall.jpeg)";
	}
	else if(x.value == "Tech"){
		url = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/tech.jpeg)";
		smurl = "url(https://dl.dropboxusercontent.com/u/93185541/Strip/imgs/techsmall.jpeg)";
	}
	document.getElementById('picture').style.background = smurl+" 100% no-repeat";
	document.getElementById('main').style.background = url+" 100% no-repeat";
}

function logo1(){
	document.getElementById('uni').innerHTML = "&#9821;";
}
function logo2(){
	document.getElementById('uni').innerHTML = "&#9822;";
}
function logo3(){
	document.getElementById('uni').innerHTML = "&#9823;";
}
function logo4(){
	document.getElementById('uni').innerHTML = "&#9824;";
}
function logo5(){
	document.getElementById('uni').innerHTML = "&#9825;";
}
function logo6(){
	document.getElementById('uni').innerHTML = "&#9826;";
}
function logo7(){
	document.getElementById('uni').innerHTML = "&#9827;";
}
function logo8(){
	document.getElementById('uni').innerHTML = "&#9828;";
}

function fb(){
	console.log(document.getElementById("facebook").checked);
	if(document.getElementById("facebook").checked) {
		document.getElementById("face").style.visibility = "visible";
	} 
	else {
		document.getElementById("face").style.visibility = "hidden";
	}
}

function tw(){
	if(document.getElementById("twitter").checked) {
		document.getElementById("twit").style.visibility = "visible";
	} 
	else {
		document.getElementById("twit").style.visibility = "hidden";
	}
}

function ins(){
	if(document.getElementById("instagram").checked) {
		document.getElementById("inst").style.visibility = "visible";
	} 
	else {
		document.getElementById("inst").style.visibility = "hidden";
	}
}

function pi(){
	if(document.getElementById("pinterest").checked) {
		document.getElementById("pint").style.visibility = "visible";
	} 
	else {
		document.getElementById("pint").style.visibility = "hidden";
	}
}
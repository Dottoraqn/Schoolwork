//script!!

//flickr shit

var sunny = "https://dl-web.dropbox.com/get/Pictures/sun.png?_subject_uid=93185541&w=AADGQHVoAqaEC3gNSple0sc3tUH2GwFlMPyzIlK5yxNtcQ";
var partiallyCloudy = '';
var rainy = '';
var thunderStorms = '';
var snow = '';
var cloudy='';

var flikAPI = "f96d4b795c4171d457eff94babe27244";
var flikSEC = "d89349182646dabb";

var cityn = "Weather";

function city(){
	cityn = "<?php echo $cityName?>"

	if(cityn != "Weather"){
		console.log(cityn);
		document.title = cityn;
	}
	else{
		console.log(cityn);
		document.title = "Weather";
	}
}

var contain;
function grab(){
	link = document.getElementById('hidden').innerHTML;
	contain = "<script src='"+link+"''></script>";
	document.getElementById('hidden').innerHTML = contain;
	city();
}

// var cityID;
// var fore;
// var wDiv;

// function foo(data){
// 	woeid = data.query.results.Result.woeid;
// 	console.log(woeid);
// 	//document.getElementById('returnW').innerHTML = cityID;
// 	console.log(cityID);
// 	//document.getElementById('weatherContainer').innerHTML = woeid;
// }

// function moo(data){
// 	for(var i=0;i<5;i++){
// 		fore[i] = query.resutls.channel.item.forecast.i;
// 		console.log(fore[i]);
// 	}
// 	for(var i=0;i<5;i++){
// 		wDiv = document.getElementById('weather').innerHTML;
// 		document.getElementById('weather').innerHTML = wDiv + fore[i] + "<br>";
// 		console.log(document.getElementById('weather').innerHTML);
// 	}
// }

// var weatherScript = "<script src='https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D" + cityID + "&format=json&diagnostics=true&callback=moo'></script>";

var space;
var woeidScriptFoo;

function insertTwo(){
	space = document.getElementById('hidden').innerHTML;
	woeidScriptFoo = "<script src='"+space+"'></script>";
	document.getElementById('returnW').innerHTML = woeidScriptFoo;
	// document.getElementById('weatherContainer').innerHTML = weatherScript;
	// console.log(weatherScript);
	console.log(woiedScriptFoo);
}
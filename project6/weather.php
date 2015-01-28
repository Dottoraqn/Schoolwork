<!DOCTYPE html>
<html>
	<head>
		<title id="cityName">Weather</title>
		<link rel="stylesheet" href="css/weatherstyle.css">
		<script type="text/javascript" src="scripts/scripts.js"></script>
		<script>
			//TODO
			/*
			make images
			import flickr api
			import restaurants
			import news
			*/
			var picUrl;

			var linkWeather;
			var cityWeather;
			var otherWeather;

			var sunny = "https://dl-web.dropbox.com/get/Pictures/sun.png?_subject_uid=93185541&w=AADGQHVoAqaEC3gNSple0sc3tUH2GwFlMPyzIlK5yxNtcQ";
			var partiallyCloudy = 'https://dl-web.dropbox.com/get/Pictures/sunwcloud.png?_subject_uid=93185541&w=AABibdaRHGHnJ0_-SWdGNEBlDHMyDZ_6amSREYjWgm1T6w';
			var rainy = 'https://dl-web.dropbox.com/get/Pictures/rain%20cloud.png?_subject_uid=93185541&w=AAC3TRVIO51UnUoieIr40jJ30w5sLMcSCmwqENaIkshKEQ';
			var thunderStorms = 'https://dl-web.dropbox.com/get/Pictures/lighting.png?_subject_uid=93185541&w=AADeHcZexsx1yZqvgaVArnVF09bzoIltYxgU-CzXZuTB_A';
			var snow = 'https://dl-web.dropbox.com/get/Pictures/snow.png?_subject_uid=93185541&w=AAADfHYyUEtDMQPc-tRwuLalfEe1iazc7KZG-gRbQgSf2A';
			var cloudy = 'https://dl-web.dropbox.com/get/Pictures/cloud.png?_subject_uid=93185541&w=AABkqyyA6hr8UiNVPk11ONYapYrSJbi3FjUeP1tJvHM8Gg';
			var cloudymoon = 'https://dl-web.dropbox.com/get/Pictures/cloudymoon.png?_subject_uid=93185541&w=AACCmWWvEqRLF5ClPnaG61al-PLq5qg4qLOW-zxrcEw5rA';
			var moony = 'https://dl-web.dropbox.com/get/Pictures/moon.png?_subject_uid=93185541&w=AADgi8ORAB_YTA__VoEm_oZRxo-ECgeJnHyu6z7e9cK4ew';
			
			var isDay;
			var time;

			function turnBlack(){
				document.getElementsByName('city')[0].style.color = "black";
			}
			function turnBack(){
				document.getElementsByName('city')[0].style.color = "#BBBBBB";
			}

			function computeTime(){
				var d = new Date();
				h = d.getHours();
				m = d.getMinutes();
				time = (h*60)+m;
				if(time > 420 && time < 1080){
					isDay = true;
				}
				else{
					isDay = false;
				}
				//console.log(time +","+isDay);
			}
			
			function foo(data){
				if(data.query.results.Result.woeid == undefined){
					document.getElementById('main').style.background = "url(http://loremPixel.com/1400/1400)";
					alert("Please Enter a valid City and State.");
					location.href = "http://localhost/project6/weather.php";
				}

				woeid = data.query.results.Result.woeid;
				//console.log(woeid);
				var weatherScript = document.createElement("SCRIPT");
				linkWeather = 'https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D' + woeid + '&format=json&diagnostics=true&callback=moo';
				weatherScript.src = linkWeather;
				document.getElementById('hidden2').appendChild(weatherScript);
				//console.log(weatherScript);
				//place();
			}

			function boo(data){
				//I AM SO SORRY I DON'T KNOW WHY IT MADE ME HARDCODE IT
				icon1 = data.query.results.results[0].image.tbUrl;
				rName1 = data.query.results.results[0].titleNoFormatting;
				type1 = data.query.results.results[0].content;
				pstUrl1 = data.query.results.results[0].unescapedUrl;

				icon2 = data.query.results.results[1].image.tbUrl;
				rName2 = data.query.results.results[1].titleNoFormatting;
				type2 = data.query.results.results[1].content;
				pstUrl2 = data.query.results.results[1].unescapedUrl;

				icon3 = data.query.results.results[2].image.tbUrl;
				rName3 = data.query.results.results[2].titleNoFormatting;
				type3 = data.query.results.results[2].content;
				pstUrl3 = data.query.results.results[2].unescapedUrl;

				icon4 = data.query.results.results[3].image.tbUrl;
				rName4 = data.query.results.results[3].titleNoFormatting;
				type4 = data.query.results.results[3].content;
				pstUrl4 = data.query.results.results[3].unescapedUrl;

				// icon5 = data.query.results.results[4].image.tbUrl;
				// rName5 = data.query.results.results[4].titleNoFormatting;
				// type5 = data.query.results.results[4].content;
				// pstUrl5 = data.query.results.results[4].unescapedUrl;

				// icon6 = data.query.results.results[5].image.tbUrl;
				// rName6 = data.query.results.results[5].titleNoFormatting;
				// type6 = data.query.results.results[5].content;
				// pstUrl6 = data.query.results.results[5].unescapedUrl;

				var insertDiv = "<div class='re'>\
					<img class='icon' src='"+icon1+"' alt='icon'>\
					<div class='rName'><a href='"+pstUrl1+"'>"+rName1+"</a></div>\
					<div class='rInfo'>"+type1+"</div>\
				</div>\
				<div class='re'>\
					<img class='icon' src='"+icon2+"' alt='icon'>\
					<div class='rName'><a href='"+pstUrl2+"'>"+rName2+"</a></div>\
					<div class='rInfo'>"+type2+"</div>\
				</div>\
				<div class='re'>\
					<img class='icon' src='"+icon3+"' alt='icon'>\
					<div class='rName'><a href='"+pstUrl3+"'>"+rName3+"</a></div>\
					<div class='rInfo'>"+type3+"</div>\
				</div>\
				<div class='re'>\
					<img class='icon' src='"+icon4+"' alt='icon'>\
					<div class='rName'><a href='"+pstUrl4+"'>"+rName4+"</a></div>\
					<div class='rInfo'>"+type4+"</div>\
				</div>";
				//console.log(insertDiv);
				var contentBlurg = document.getElementById('rest').innerHTML;
				document.getElementById('rest').innerHTML = contentBlurg + insertDiv;
			}

			function jsonFlickrApi(data){
				photoInfo = data.photos.Photo;
				for(var i=0;i<photoInfo.length;i++){
					id = photoInfo[i].id;
					frmId = photoInfo[i].farm;
					svId = photoInfo[i].server;
					sec = photoInfo[i].secret;
					picUrl = "https://farm"+frmId+".staticflickr.com/"+svId+"/"+id+"_"+sec+".jpg";
					var back = document.getElementById('main');
					back.style.background = "url("+picUrl+")";
					back.style.backgroundSize = "100% 100%";
					back.style.backgroundRepeat = "no-repeat";
					//console.log(picUrl);
				}
			}

			function moo(data){
				computeTime();
				forecast = data.query.results.channel.item.forecast;	 
				//console.log(forecast);
				for(i=0; i<forecast.length; i++){
					var day = "day"+i;
					var bg = "bg"+i;
					document.getElementById(day).innerHTML = document.getElementById(day).innerHTML + "<br>" + "<br> <b>" + forecast[i].day + "</b><br>" + "<br>" + forecast[i].date + "<br>" + "Hi: " + forecast[i].high + "&deg; F" +  "<br>" + "Lo: " + forecast[i].low + "&deg; F" + "<br>" + forecast[i].text;
			
					var box = document.getElementById(bg);
					var otherBox = document.getElementById(day);
					box.style.backgroundSize = "200px 200px";
					box.style.backgroundRepeat = "no-repeat";
					box.style.borderRadius = '50%';
					box.style.bottom = "0";
					otherBox.style.marginLeft = "5%";

					// if(time > 420 && time < 1080){
					// 	otherBox.style.background = "background: radial-gradient(rgba(0,142,181,0), rgba(0,142,181,.7))";
					// }

					if(forecast[i].text == 'Sunny' || forecast[i].text == 'Clear' || forecast[i].text == 'Mostly Clear'){
						if(isDay || i != 0) box.style.backgroundImage = "url("+sunny+")";
						else box.style.backgroundImage = "url("+moony+")";
					}
					else if(forecast[i].text == 'Partly Cloudy' || forecast[i].text == 'Mostly Sunny' || forecast[i].text == 'AM Clouds/PM Sun'){
						if(isDay || i != 0) box.style.backgroundImage = "url("+partiallyCloudy+")";
						else box.style.backgroundImage = "url("+cloudymoon+")";
					} 
					else if(forecast[i].text == 'Cloudy' || forecast[i].text == 'Mostly Cloudy'){
						box.style.backgroundImage = "url("+cloudy+")";
					} 
					else if(forecast[i].text == 'Rain' || forecast[i].text == 'Showers'){
						box.style.backgroundImage = "url("+rainy+")";
					}
					else if(forecast[i].text == 'Isolated Thunderstorms'){
						box.style.backgroundImage = "url("+thunderStorms+")";
					}
					else if(forecast[i].text == 'Snow'){
						box.style.backgroundImage = "url("+snow+")";
					}
					else {
						box.style.backgroundImage = "none";
					}
				}
			}

		</script>
	</head>
	<body >
		<div id="main">
			<div id="top">
				<form id="displayInfo" action="<?php echo $_SERVER['PHP_SELF']; ?>" method="get">
					<div id="formz">Type the name of a city: <br><input type="text" name="city" style="font-size:30px;color:#BBBBBB;text-align:center;" value="<?php echo isset($_REQUEST['city']) ? $_REQUEST['city']:'City, State'?>" id="city" onfocus="turnBlack()" onblur="turnBack()">
					<?php
						$cityName = "Atlanta";
						if(isset($_REQUEST['city']))
							$cityName = $_REQUEST['city'];
					?>
					</div>
				</form>
				<div id="news">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum reprehenderit a quaerat aliquam est nihil temporibus hic consequuntur? Facere dicta eligendi ipsa similique omnis sequi earum adipisci nisi possimus, fugiat?</div>
				<div id="rest"><div id="newsTitle">News for <?php echo $cityName?>:</div>
					<!-- <div class="re">
						<img class="icon" src="http://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png" alt="icon">
						<div class="rName">Rathbun's</div>
						<div class="rInfo">Bar and grille</div>
					</div> -->
				</div>
			</div>
			<div id="hidden2"></div>
			<div id="hidden3"><script src="https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=e7b211b0bfbf94b3727c48dfe6a4361c&sort=relevance&text= <?php echo $cityName?>+skyline&radius=800&per_page=1&page=1&format=json"></script></div>

			<div id="hidden4"><script src="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20google.news%20where%20q%20%3D%20%22<?php echo $cityName?>%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=boo"></script></div>

			<div id="hidden"><script src="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.placefinder%20where%20text%3D%22<?php echo $cityName?>%22&format=json&diagnostics=true&callback=foo"></script></div>
			
			
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
			<div id="container">
				<div class="day" id="day0"><div class="bg" id="bg0"></div></div>
				<div class="day" id="day1"><div class="bg" id="bg1"></div></div>
				<div class="day" id="day2"><div class="bg" id="bg2"></div></div>
				<div class="day" id="day3"><div class="bg" id="bg3"></div></div>
				<div class="day" id="day4"><div class="bg" id="bg4"></div></div>
			</div>
		</div>
	</body>
</html>

<!-- 
geo.placefinder
weather.forcast
flickr
 -->
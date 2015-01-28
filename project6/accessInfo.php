<!DOCTYPE html>
<html>
<head>
	<title>GetInfo</title>
	<script type="text/javascript" src="scripts/scripts.js"></script>
	<script type="text/javascript">
		var woeidScript = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.placefinder%20where%20text%3D%22" + "<?php echo $cityName?>" + "%22&format=json&diagnostics=true&callback=foo";
		
		function insert(){
			document.getElementById('hidden').innerHTML = woeidScript;
			// document.getElementById('weatherContainer').innerHTML = weatherScript;
			// console.log(weatherScript);
			console.log(woiedScript);
		}
	</script>
</head>
<body onload="insert(); insertTwo();">
	<div id="formz">
		<form id="displayInfo" action="<?php echo $_SERVER['PHP_SELF']; ?>" method="get">
			<div id="input">Type the name of a city: <input type="text" name="city" value="<?php echo isset($_REQUEST['city']) ? $_REQUEST['city']:'City, State'?>" id="city">

			<?php
				$cityName = "Atlanta";
				if(isset($_REQUEST['city']))
					$cityName = $_REQUEST['city'];
			?>
			</div>
		</form>
	</div>

	<p>Get WOEID</p>
	<div id="hidden"></div>
	<div id="returnW"></div>

	<p>Get Weather</p>
	<div id="weatherContainer">
		

	</div>

	<div id="weather"></div>
	
</body>
</html>
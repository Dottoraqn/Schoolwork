<html>
	<head><title>Php and Forms</title></head>
	<style>
		*{
			font-family: Helvetica;
		}
	</style>
	<script type="text/javascript"></script>

	<body id="all">

		<h1>PHP and Forms</h1>

		<form id="displayInfo" action="<?php echo $_SERVER['PHP_SELF']; ?>" method="get">
			<h1 class="titleText">Title Text</h1>

			Enter the title text:
			<input style="color:black" type="text" name="inputText" id="inputText" value="
			<?php echo isset($_REQUEST['inputText']) ? $_REQUEST['inputText']:'Title Text'?>"></br></br>
			
			Choose the position of the title text:
			L<input type="radio" value="left" name="align" <?php if (isset($_REQUEST['align']) && $_REQUEST['align']=="left") echo "checked";?>>
			C<input type="radio" value="center" name="align" <?php if (isset($_REQUEST['align']) && $_REQUEST['align']=="center") echo "checked";?>>
			R<input type="radio" value="right" name="align" <?php if (isset($_REQUEST['align']) && $_REQUEST['align']=="right") echo "checked";?>></br></br>
			
			<div>Choose a background color:</br>
			R: <input type="range" name="red" min="0" max="255"></br>
			G: <input type="range" name="green" min="0" max="255"></br>
			B: <input type="range" name="blue" min="0" max="255"></br>
			O: <input type="range" name="opac" min="0" max="10"></div></br>
			
			<textarea name="txtrea" id="txtrea" style="color:black" rows="2" cols="30" <?php echo $_REQUEST['txtrea']?>>
			</textarea></br></br>
			
			Select an image: <select name="drop" id="selector" style="color:black">
				<option value="Animals">Animals</option>
				<option value="Food">Food</option>
				<option value="Sports">Sports</option>
				<option value="People">People</option>
				<option value="Tech">Tech</option>
			</select></br></br>
			
			Click on a logo:</br>
			&#9821;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9821;") echo "checked";?> value="&#9821;">
			&#9822;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9822;") echo "checked";?> value="&#9822;">
			&#9823;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9823;") echo "checked";?> value="&#9823;">
			&#9824;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9824;") echo "checked";?> value="&#9824;">
			&#9825;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9825;") echo "checked";?> value="&#9825;">
			&#9826;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9826;") echo "checked";?> value="&#9826;">
			&#9827;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9827;") echo "checked";?> value="&#9827;">
			&#9828;<input type="radio" name="symbols" <?php if (isset($_REQUEST['symbols']) && $_REQUEST['symbols']=="&#9828;") echo "checked";?> value="&#9828;">
			</br></br>

			Choose a social media icon:</br>
			Facebook:<input name="social[]" type="checkbox" value="facebook" id="facebook"> 
			Instagram:<input name="social[]" type="checkbox" value="instagram" id="instagram"> 
			Pinterest:<input name="social[]" type="checkbox" value="pinterest" id="pinterest"> 
			Twitter:<input name="social[]" type="checkbox" value="twitter" id="twitter">
			
			</br></br>
			<input type="submit" name="submit" value="Submit">

		</form>

		<h2>Your Input:</h2>
		<div id="print">replace me</div>
		<script>
		<?php
			$titleTextSel = "<br>";
			$align = "<br>";
			$hex = "<br>";
			$txt = "<br>";
			$drop = "<br>";
			$symb = "<br>";
			$sm = "<br>";
			//echo "<h2>Your Input:</h2>";
			if (isset($_REQUEST["inputText"])){ 
				$titleTextSel = $_REQUEST["inputText"]; 
				//echo $titleTextSel . '<br>'; 
			}
			if (isset($_REQUEST["align"])){ 
				$align = $_REQUEST["align"]; 
				//echo $align . '<br>'; 
			}
			if (isset($_REQUEST["red"]) && isset($_REQUEST["green"]) && isset($_REQUEST["blue"])){ 
				$hex = "rgb()";
				if(isset($_REQUEST["opac"])){
					$op = "1";
					if($_REQUEST["opac"]==10){
						$op = "1.0";
					}
					else{
						$op = "0.".$_REQUEST["opac"];
					}
					$hex = "rgba(".$_REQUEST["red"].",".$_REQUEST["green"].",".$_REQUEST["blue"].",".$op.")";
				}
				else{
					$hex = "rgb(".$_REQUEST["red"].",".$_REQUEST["green"].",".$_REQUEST["blue"].")";	
				}
					//echo $hex . '<br>'; 
			}
			if (isset($_REQUEST["txtrea"])){ 
				$txt = $_REQUEST["txtrea"]; 
				//echo $txt . '<br>'; 
			}
			if (isset($_REQUEST["drop"])){ 
				$drop = $_REQUEST["drop"]; 
				//echo $drop . '<br>'; 
			}
			if (isset($_REQUEST["symbols"])){ 
				$symb = $_REQUEST["symbols"]; 
				//echo $symb . '<br>'; 
			}
			if (isset($_REQUEST["social"])){
				$smarr = $_REQUEST["social"];
				$sm = "social media: ";
				if(array_search("facebook", $smarr) > -1){
					$sm = $sm."facebook ";
					echo "document.getElementById('facebook').checked = true;";
				}
				if(array_search("instagram", $smarr) > -1){
					$sm = $sm."instagram ";
					echo "document.getElementById('instagram').checked = true;";
				}
				if(array_search("pinterest", $smarr) > -1){
					$sm = $sm."pinterest ";
					echo "document.getElementById('pinterest').checked = true;";
				}
				if(array_search("twitter", $smarr) > -1){
					$sm = $sm."twitter ";
					echo "document.getElementById('twitter').checked = true;";
				}
			}
		?>
		</script>

		<!-- IMPORTANT: http://stackoverflow.com/questions/5895842/how-to-assign-php-variable-value-to-javascript-variable -->
	<script type="text/javascript">
		var title = "<?php echo $titleTextSel;?>";
		var align = "<?php echo $align;?>";
		var hex = "<?php echo $hex;?>";
		var txt = "<?php echo $txt;?>";
		var drop = "<?php echo $drop;?>";
		var symb = "<?php echo $symb;?>";
		var sm = "<?php echo $sm;?>";
		console.log(title + "<br>" + align + "<br>" + hex + "<br>" + txt + "<br>" + drop + "<br>" + symb + "<br>" + sm);
		test();
		colorChange();
		function test(){
			document.getElementById('print').innerHTML = title + "<br>" + align + "<br>" + hex + "<br>" + txt + "<br>" + drop + "<br>" + symb + "<br>" + sm;
			document.getElementById('txtrea').innerHTML = "value";
		}
		function colorChange(){
			document.getElementById("all").style.background = hex;
		}
	</script>
	</body>
</html>
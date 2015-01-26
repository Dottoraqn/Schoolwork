function makeHeader(){
	var headerHTML = '<h1 id="header">Project 2</h1>\
			<div id="nav">\
				<ul>\
					<li><div class="item"><a href="2col.html">2 Col</a></div></li>\
					<li><div class="item"><a href="3col.html">3 Col</a></div></li>\
					<li><div class="item"><a href="3box.html">3 Box</a></div></li>\
					<li><div class="item"><a href="5box.html">5 Box</a></div></li>\
					<li><div class="item"><a href="feat.html">Feat</a></div></li>\
				</ul>\
			</div>';
	document.getElementById('top').innerHTML = headerHTML;
	var thisPage = document.getElementById('title').innerHTML;
	console.log(this);
	//alert(url);
	document.getElementById(thisPage).setAttribute('class','active');
	insertNav();
}

function insertNav(){
	var link = document.querySelector('link[rel="import"]');
	var content = link.import;
	var navi = content.querySelector("#ddm");
	document.getElementById('top').appendChild(navi);
}

function showSubMenu(obj){
	var sub = obj.getElementsByName('div')[1];
	sub.style.visibility = 'visible';
	sub.style.height = '100%';
}

function hideSubMenu(obj){
	var sub = obj.getElementsByName('div')[1];
	sub.style.visibility = 'hidden';
	sub.style.height = '0';
}
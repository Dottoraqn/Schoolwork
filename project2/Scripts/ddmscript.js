function insertNav(){
	var link = document.querySelector('link[rel="import"]');
	var content = link.import;
	var navi = content.querySelector("#ddm");
	document.getElementById('top').appendChild(navi);
}

function showSubMenu(obj){
	var sub = obj.getElementsByTagName('div')[1];
	sub.style.visibility = 'visible';
	sub.style.height = '100%';
}

function hideSubMenu(obj){
	var sub = obj.getElementsByTagName('div')[1];
	sub.style.visibility = 'hidden';
	sub.style.height = '0%';
}
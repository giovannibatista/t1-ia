function hideButton(button){
	/*button.style.display='none';
	document.getElementById('next').style.display='';
	document.getElementById('insertTrashCan').style.display='';
	document.getElementById('insertRecharges').style.display='';*/
}

function createMatrix(){
	console.info('Chamando createMatix...');
	createMatix();
}

function createMatrixTestMoving(){
	createMatrixTestMoving();
}

function nextBlock(){
	var interval = document.getElementById('form:interval');
	setInterval(function(){
		console.info('Chamando next...');
		next();
	}, interval.value);

}

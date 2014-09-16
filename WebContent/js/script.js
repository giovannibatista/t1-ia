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
	console.info('Chamando createMatrixTestMoving...');
	createMatrixTestMoving();
}

function nextBlock(){
	console.info('Chamando next...');
	setInterval(function(){
		console.info('Chamando next...');
		next();
	}, 1000);

}
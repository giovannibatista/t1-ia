var interval;

function create() {
	document.getElementById('tableAgents').style.display = '';
	document.getElementById('nextBlockBtn').style.display = '';
	document.getElementById('nextBtn').style.display = '';
	createMatrix();
}

function nextBlock() {
	var intervalParameter = document.getElementById('form:interval');
	document.getElementById('nextBlockBtn').style.display = 'none';
	document.getElementById('nextBtn').style.display = 'none';
	document.getElementById('stopIntervalBtn').style.display = '';
	interval = setInterval(function() {
		console.info('Chamando next...');
		next();
	}, intervalParameter.value);

}

function stopInterval() {
	document.getElementById('nextBlockBtn').style.display = '';
	document.getElementById('nextBtn').style.display = '';
	document.getElementById('stopIntervalBtn').style.display = 'none';
	clearInterval(interval);
}
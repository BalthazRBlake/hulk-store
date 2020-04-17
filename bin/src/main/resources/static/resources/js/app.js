const btnAddPro = document.getElementById('addPro');
const selectedPro = document.getElementById('selectedPro');
const productsList = document.getElementById('toAddPro');
//const btnReturnPro = document.querySelector('.returnBtn');

loadEventListeners();

function loadEventListeners(){
	btnAddPro.addEventListener('click', addProduct);
	selectedPro.addEventListener('change', getItem);
	//btnReturnPro.addEventListener('click', returnProduct);
}

function getItem(e){
	e.preventDefault();
	if(e.target.selectedIndex){
		btnAddPro.disabled = false;
	}else{
		btnAddPro.disabled = true;
	}
}

function addProduct(e){
	e.preventDefault();

	btnAddPro.disabled = true;
	
	let idProduct = selectedPro.value;
	selectedPro.remove(selectedPro.selectedIndex);
	
	const addedProduct = document.getElementById('idPro'+idProduct);
	addedProduct.classList.remove("d-none");
}

/*function returnProduct(e){
	e.preventDefault();
	if(e.target.classList.contains("retrunBtn")){
		let parentId = e.target.parentElement.parentElement;
		console.log(parentId);
	}
	//parentId.classList.add("d-none");
	
	let option = document.createElement("option");
	option.text = "Kiwi";
	//x.add(option);
}*/
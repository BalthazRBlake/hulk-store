const btnAddPro = document.getElementById('addPro');
const selectedPro = document.getElementById('selectedPro');
const productsList = document.getElementById('toAddPro');
let product;
let addedProducts=[];
productsTo = [];

loadEventListeners();

function loadEventListeners(){
	btnAddPro.addEventListener('click', addProduct);
	selectedPro.addEventListener('change', getItem);
}

function getItem(e){
	let text = e.target.selectedOptions[0].text;
	let value = e.target.value;
	if(value){
		btnAddPro.disabled = false;
	}
	product = value + " " + text;
	
	console.log(productsTo);
}

function addProduct(e){
	e.preventDefault();
	btnAddPro.disabled = true;
	let proArr = product.split(" ");
	//console.log(proArr);
	selectedPro.remove(selectedPro.selectedIndex);
	
	/*productsTo.push({
		"id":proArr[0],
		"item":proArr[1],
		"hero":proArr[2],
		"brand":proArr[3]
	});*/
	
	/*const row = document.createElement('tr');
	  row.innerHTML = `
	    <input type="hidden" value="${proArr[0]}" readonly>
	    <td>
	      <input type="text" value="${proArr[1]}" readonly> 
	    </td>
	    <td>
	      <input type="text" value="${proArr[2]}" readonly>
	    </td>
	    <td>
	      <input type="text" value="${proArr[3]}" readonly>
	    </td>
	    <td>
	      <input type="number" th:field="*{product.units}" required>
	    </td>
	  `;
	  
	  productsList.appendChild(row);*/
}
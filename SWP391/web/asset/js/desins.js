
var maxQuantity = 0;

function checkQuantity() {
    var quantityInput = document.getElementsByName("txtQuantity")[0];
    if (parseInt(quantityInput.value) >= parseInt(maxQuantity)) {

        quantityInput.value = maxQuantity; // Set giá trị về số lớn nhất
       
    } else if (parseInt(quantityInput.value) < 1) {

        quantityInput.value = 1; // Set giá trị về số lớn nhất
       

    }
}
function showQuantity(radio) {
    maxQuantity = radio.dataset.maxquantity;
    var quantityInput = document.getElementsByName("txtQuantity")[0];
    quantityInput.max = maxQuantity;
    quantityInput.value = 1;
    document.getElementById("maxQuantity").innerText = maxQuantity;
}

function incrementValue() {
    var quantityInput = document.getElementsByName("txtQuantity")[0];
    if (parseInt(quantityInput.value) <= parseInt(maxQuantity)) {
        quantityInput.value = parseInt(quantityInput.value) + 1;
    } else {
        quantityInput.value = maxQuantity; // Set giá trị về số lớn nhất
        
    }
}

function decrementValue() {
    var quantityInput = document.getElementsByName("txtQuantity")[0];
    if (parseInt(quantityInput.value) > parseInt(quantityInput.min)) {
        quantityInput.value = parseInt(quantityInput.value) - 1;
    } else if (parseInt(quantityInput.value) <= 1) {
        quantityInput.value = 1;
        
    }
}

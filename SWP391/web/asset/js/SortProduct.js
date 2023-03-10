const productList = document.querySelector('.product-list-container');
const sortSelect = document.querySelector('#sort-by');

// Add an event listener to the sort select element
sortSelect.addEventListener('change', (event) => {
    // Get the selected sort option value
    const selectedSortOption = event.target.value;

    // Get the list of product items
    const productItems = Array.from(productList.querySelectorAll('.item-product'));

    // Sort the product items based on the selected sort option
    switch (selectedSortOption) {
        case 'price-asc':
            productItems.sort((a, b) => {
                const priceA = parseFloat(a.querySelector('.product-price').textContent);
                const priceB = parseFloat(b.querySelector('.product-price').textContent);
                return priceA - priceB;
            });
            break;
        case 'price-des':
            productItems.sort((a, b) => {
                const priceA = parseFloat(a.querySelector('.product-price').textContent);
                const priceB = parseFloat(b.querySelector('.product-price').textContent);
                return priceB - priceA;
            });
            break;
        case 'name-a-z':
            productItems.sort((a, b) => {
                const nameA = a.querySelector('.product-name').textContent.toUpperCase();
                const nameB = b.querySelector('.product-name').textContent.toUpperCase();
                return nameA.localeCompare(nameB);
            });
            break;
        case 'name-z-a':
            productItems.sort((a, b) => {
                const nameA = a.querySelector('.product-name').textContent.toUpperCase();
                const nameB = b.querySelector('.product-name').textContent.toUpperCase();
                return nameB.localeCompare(nameA);
            });
            break;
        default:
            break;
    }

    // Clear the product list container
    productList.innerHTML = '';

    // Add the sorted product items back to the product list container
    productItems.forEach((productItem) => {
        productList.appendChild(productItem);
    });
});

sortSelect.addEventListener('change', (event) => {
    const selectedSortOption = event.target.value;
    sessionStorage.setItem('selectedSortOption', selectedSortOption);
    
});

document.addEventListener('DOMContentLoaded', () => {
    const selectedSortOption = sessionStorage.getItem('selectedSortOption');
    if (selectedSortOption) {
        sortSelect.value = selectedSortOption;
        // trigger the change event to sort the product items
        sortSelect.dispatchEvent(new Event('change'));
    }
});



    
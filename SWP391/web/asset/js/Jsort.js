$(document).ready(function () {
    // Listen for changes to the select element
    $('#sort-by').on('change', function () {
        // Get the selected value
        var selectedValue = $(this).val();

        // Sort the product items based on the selected value
        if (selectedValue === 'price-asc') {
            $('.product-item').sort(function (a, b) {
                return parseInt($(a).find('.product-price').text()) - parseInt($(b).find('.product-price').text());
            }).appendTo('.product-list-container');
        } else if (selectedValue === 'price-des') {
            $('.product-item').sort(function (a, b) {
                return parseInt($(b).find('.product-price').text()) - parseInt($(a).find('.product-price').text());
            }).appendTo('.product-list-container');
        } else if (selectedValue === 'name-a-z') {
            $('.product-item').sort(function (a, b) {
                return $(a).find('.product-name').text().localeCompare($(b).find('.product-name').text());
            }).appendTo('.product-list-container');
        } else if (selectedValue === 'name-z-a') {
            $('.product-item').sort(function (a, b) {
                return $(b).find('.product-name').text().localeCompare($(a).find('.product-name').text());
            }).appendTo('.product-list-container');
        }
    });
});



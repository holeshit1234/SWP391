function showConfirmationDialog(url) {
    if (confirm("Are you sure you want to delete this payment?")) {
        window.location.href = url;
    }
}



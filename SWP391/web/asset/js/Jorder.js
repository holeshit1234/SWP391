function confirmCancelOrder(url) {
    var confirmBox = document.createElement('div');
    confirmBox.classList.add('confirm-box');
    confirmBox.classList.add('alert'); // Add alert class

    var message = document.createElement('p');
    message.innerHTML = 'Are you sure you want to cancel this order?';
    confirmBox.appendChild(message);

    var buttons = document.createElement('div');
    buttons.classList.add('button-container'); // Add button container class

    var cancelButton = document.createElement('button');
    cancelButton.innerHTML = 'Cancel (back)';
    cancelButton.classList.add('cancel-button'); // Add cancel button class
    cancelButton.addEventListener('click', function () {
        confirmBox.parentNode.removeChild(confirmBox);
    });
    buttons.appendChild(cancelButton);

    var confirmButton = document.createElement('button');
    confirmButton.innerHTML = 'Cancel Order';
    confirmButton.classList.add('cancel-button'); // Add confirm button class
    confirmButton.addEventListener('click', function () {
        window.location.href = url;
        confirmBox.parentNode.removeChild(confirmBox);
    });
    buttons.appendChild(confirmButton);

    confirmBox.appendChild(buttons);

    document.body.appendChild(confirmBox);
}

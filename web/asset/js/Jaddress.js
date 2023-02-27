function confirmDelete(url) {
    var confirmBox = document.createElement('div');
    confirmBox.classList.add('confirm-box');
    confirmBox.classList.add('alert'); // Add alert class


    var message = document.createElement('p');
    message.innerHTML = 'Are you sure you want to delete this address?';
    confirmBox.appendChild(message);

    var buttons = document.createElement('div');
    buttons.classList.add('button-container'); // Add button container class

    var okButton = document.createElement('button');
    okButton.innerHTML = 'OK';
    okButton.classList.add('ok-button'); // Add OK button class
    okButton.addEventListener('click', function () {
        window.location.href = url;
        confirmBox.parentNode.removeChild(confirmBox);
    });
    buttons.appendChild(okButton);

    var cancelButton = document.createElement('button');
    cancelButton.innerHTML = 'Cancel';
    cancelButton.classList.add('cancel-button'); // Add cancel button class
    cancelButton.addEventListener('click', function () {
        confirmBox.parentNode.removeChild(confirmBox);
    });
    buttons.appendChild(cancelButton);

    confirmBox.appendChild(buttons);

    document.body.appendChild(confirmBox);
}
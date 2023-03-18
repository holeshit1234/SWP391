function confirmDelete(url) {
    var confirmBox = document.createElement('div');
    confirmBox.classList.add('confirm-box');
    confirmBox.classList.add('alert'); // Add alert class


    var message = document.createElement('p');
    message.innerHTML = 'Are you sure you want to delete this comment?';
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

function confirmFlag(url) {
    var confirmBox = document.createElement('div');
    confirmBox.classList.add('confirm-box');
    confirmBox.classList.add('alert'); // Add alert class

    var message = document.createElement('p');
    message.innerHTML = 'Please tell us the reason you report this comment.';
    confirmBox.appendChild(message);

    var inputField = document.createElement('input');
    inputField.type = 'text';
    inputField.name = 'reason';
    inputField.placeholder = 'Enter reason for reporting...';
    confirmBox.appendChild(inputField);

    var buttons = document.createElement('div');
    buttons.classList.add('button-container'); // Add button container class

    var okButton = document.createElement('button');
    okButton.innerHTML = 'OK';
    okButton.classList.add('ok-button'); // Add OK button class
    okButton.addEventListener('click', function () {
        var reason = inputField.value;
        window.location.href = url + '&reason=' + encodeURIComponent(reason);
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
function confirmEdit(url) {
    var confirmBox = document.createElement('div');
    confirmBox.classList.add('confirm-box');
    confirmBox.classList.add('alert'); // Add alert class

    var message = document.createElement('p');
    message.innerHTML = 'Are you sure you want to edit this item?';
    confirmBox.appendChild(message);

    var inputField = document.createElement('input');
    inputField.type = 'text';
    inputField.name = 'description';
    inputField.placeholder = 'Enter new description...';
    confirmBox.appendChild(inputField);

    var buttons = document.createElement('div');
    buttons.classList.add('button-container'); // Add button container class

    var okButton = document.createElement('button');
    okButton.innerHTML = 'OK';
    okButton.classList.add('ok-button'); // Add OK button class
    okButton.addEventListener('click', function () {
        var description = inputField.value;
        window.location.href = url + '&description=' + encodeURIComponent(description);
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

$(".col-lg-12").slice(0, 3).show();
$(".loadMore").on("click", function () {
    $(".col-lg-12:hidden").slice(0, 3).show();
    if ($(".col-lg-12:hidden").length == 0) {
        $(".loadMore").fadeOut();
    }
})



class starsReview extends HTMLElement {
    constructor() {
        super();
        this.drawStars();
    }
    drawStars() {
        this.innerHTML = '';
        let value = parseFloat(this.getAttribute('value'));
        let max = parseInt(this.getAttribute('max'));

        if (value > max)
            value = max;

        let nWholes = Math.floor(value);
        let nParts = value % 1 == 0 ? 0 : 1;
        let nEmpty = max - nWholes - nParts;

        for (let i = 0; i < nWholes; i++) {
            var iElement = document.createElement('i');
            iElement.setAttribute('index', i);
            iElement.classList.add('fa');
            iElement.classList.add('fa-star');
            this.append(iElement)
        }

        for (let i = 0; i < nParts; i++) {
            var iElement = document.createElement('i');
            iElement.setAttribute('index', i + nWholes);
            iElement.classList.add('fa');
            iElement.classList.add('fa-star-half-o');
            this.append(iElement)
        }

        for (let i = 0; i < nEmpty; i++) {
            var iElement = document.createElement('i');
            iElement.setAttribute('index', i + nWholes + nParts);
            iElement.classList.add('fa');
            iElement.classList.add('fa-star-o');
            this.append(iElement)
        }
    }
}
window.customElements.define('starts-review', starsReview)


let zoom = document.querySelector('.main-image');
let imgZoom = document.getElementById('myImage');

zoom.addEventListener('mousemove', (event) => {
    imgZoom.style.opacity = 1;
    let positionPx = event.x - zoom.getBoundingClientRect().left;
    let positionX = (positionPx / zoom.offsetWidth) * 100;

    let positionPy = event.y - zoom.getBoundingClientRect().top;
    let positionY = (positionPy / zoom.offsetHeight) * 100;

    imgZoom.style.setProperty('--zoom-x', positionX + '%');
    imgZoom.style.setProperty('--zoom-y', positionY + '%');

    let transformX = -(positionX - 50) / 3.5;
    let transformY = -(positionY - 50) / 3.5;
    imgZoom.style.transform = `scale(1.5) translateX(${transformX}%) translateY(${transformY}%)`;
})
zoom.addEventListener('mouseout', () => {
    imgZoom.style.opacity = 0;
})





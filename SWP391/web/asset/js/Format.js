

$(document).ready(function () {
    $('#txtEmail').on('blur', function () {
        var email = $(this).val();
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (emailRegex.test(email)) {
            $(this).removeClass('error');
            $(this).next('.error-message').hide();
        } else {
            $(this).addClass('error');
            $(this).next('.error-message').html('Please enter a valid email address.').show();
        }

        if (!$('#txtEmail').hasClass('error') && !$('.error-message.phone').is(':visible')) {
            $('input[name="btAction"]').show();
        } else {
            $('input[name="btAction"]').hide();
        }
    });
    $('input[name="txtPhone"]').on('input', function () {
        var phone = $(this).val();
        var phoneRegex = /^\d{10}$/;
        if (!phoneRegex.test(phone)) {
            $('.error-message.phone').text('Please enter a valid 10-digit phone number.').css('color', 'red').show();
            $('input[name="btAction"]').hide();
        } else {
            $('.error-message.phone').hide();
            $('input[name="btAction"]').show();
        }
    }
    );
});


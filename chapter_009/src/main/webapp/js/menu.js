idBtn = 'login';

$(document).ready(function () {
    getLogin();

});

$(function () {
    $('.btf').click(function () {
        goToUser();

    });
});


function getLogin() {
    $.ajax({
        type: "get",
        url: "./authentic",
        dataType: "json",
        complete: function (data) {
            var json = JSON.parse(data.responseText);
            var login = json['login'];
            var idAuthor = json['idAuthor'];
            logoBox(login);
        }
    });
}

function goToUser() {
   var path =  '/ad';

    if (idBtn == 'profile') {
        path = '/ad';
    }
    window.location.href = path;
}

function logoBox(login) {
    var btnText = 'Enter profile';
    idBtn = 'profile';
    if (login == null) {
        login = 'guest';
        btnText = 'Sign in or create an account';
        idBtn = 'login';
    }
    $('p.demo-container').html('Hello, ' + login);
    $('p.btf').html('<button type="button" class="btn btn-primary btn btn-default">' +
        btnText + '</button>');
}

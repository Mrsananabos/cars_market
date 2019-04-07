idBtn = 'login';

$(document).ready(function () {
    getLogin();
    getCars();

});

$(function () {
    $('.btf').click(function () {
        goToUser();

    });
});

function getCars() {
    // $("td").remove();
    $.ajax({
        url: "./cars",
        method: "get",
        dataType: "json",
        complete:
            function (data) {
                var json = JSON.parse(data.responseText);
                for (var i = 0; i < json.length; i++) {
                    alert('<td><img src="img/'+ json[i][6] + '" width="120" height="80" alt="car"></td>');
                    $('#table tr:last').after('<tr><td><img src="img/'+ json[i][6] + '" width="120" height="80" alt="car"></td><td>' + json[i][0] + '</td><td>' + json[i][1] +'</td><td>' + json[i][2] +'</td><td>' + json[i][3] +'</td><td>' + json[i][4] +'</td><td>' + json[i][5] + '</td><td>' + json[i][7] + '</td><td>' + json[i][8] + '</td></tr>');
                }
            }
    })
}


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

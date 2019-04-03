
$(function () {
    $('#enter').click(function () {
        createUser();
    });
});

$(function () {
    $('#createProfile').click(function () {
        createNewUser();
    });
});

function createNewUser() {
    var login = $('#newLogin').val();
    var password = $('#newPassword').val();
    if (validate(login, password)) {
        createNewProf(login, password);
    }
}

function createUser() {
    var login = $('#login').val();
    var password = $('#password').val();
    if (validate(login, password)) {
        goToProfile(login, password);
    }
}

function validate(login, password) {
    var result = true;
    if (login == '') {
        alert("Please, fill in the 'login' field");
        result = false;
    }
    if (password == '') {
        alert("Please, fill in the 'password' field");
        result = false;
    }
    return result;
}

function createNewProf(login, password) {
    $.ajax({
        type: "post",
        url: "./authentic",
        dataType: "json",
        data: {login: login,
            password: password},
        complete: function (data) {
            var json = JSON.parse(data.responseText);
            var status = json['status'];
            if (status == '1') {
                window.location.href = "/ad";
            } else {
                alert('Sorry, login is already taken')
            }

        }
    });
}

function goToProfile(login, password) {
    $.ajax({
        type: "post",
        url: "./authoriz",
        dataType: "json",
        data: {login: login,
               password: password},
        complete: function (data) {
            var json = JSON.parse(data.responseText);
            var status = json['status'];
            if (status == '1') {
                window.location.href = "/ad";
            } else {
                alert('Invalid data')
            }

        }
    });
}
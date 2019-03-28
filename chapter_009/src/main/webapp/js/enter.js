$(document).ready(function () {
    $('#back').click(function () {
        window.location.href = "/menu.html"
    })
});

function signin() {
    var login = $('#login').val();
    var password = $('#password').val();
    if (validate(login, password)) {
        var client = createClient(login, password);
        sendTo(client)
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
function createClient(login, password) {
    return {
        login: login,
        password: password,
    };
}
function sendTo(client) {
    $.ajax({
        type: "post",
        url: "./signin",
        dataType: "json",
        data: JSON.stringify(client),
        complete: function (data) {
            var json = JSON.parse(data.responseText);
            if (json['role'] == "admin") {
                window.location.href = "http://localhost:8080/chapter_006/admin.html"
            }
            if (json['role'] == "user") {
                var id = json['id'];
                window.location.href = "http://localhost:8080/chapter_006/user.html?id="+id;
            }
            if (json['role'] == "error") {
                $("#error").text ("ERROR. PLEASE CHECK YOUR LOGIN AND PASSWORD")
            }
        }
    });}
login = '';
pathImage ='';

$(function () {
    var url = document.location.href;
    login = url.split("login=")[1];
    getLogin();
    loadMarks();
    loadTransmission();
    loadBodyType();
    getCars(login);
});

$(function () {
    $('#mark').change(function () {
        var mark = $(this).val();
        loadModels(mark);
    });
});

$(function () {
    $('#back').click(function () {
        window.location.href = "/market";
    });
});

function getCars() {
    $(".cars").empty();
    $.ajax({
        url: "./cars",
        method: "get",
        dataType: "json",
        data:  {data: login},
        success:
            function (data) {
                var img = document.createElement("img");
                for (var i = 0; i < data.length; i++) {
                    $('.cars').append('<tr><td><img src="image/'+ data[i].pathImage + '" width="200" height="150" alt="car"></td><td>' + data[i].mark + '</td><td>' + data[i].model +'</td><td>' + data[i].transmission +'</td><td>' + data[i].bodyType +'</td><td>' + data[i].yearIssue +'</td><td>' + data[i].price + '</td><td>' + data[i]['isSold'] + '</td><td><button class="btn btn-primary btn btn-default" onclick="isSold(' + data[i]['id'] + ')">Car sold</button></td>></tr>');
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
            var login = json.login;
            logoBox(login);
        }
    });
}

function  logoBox(login) {
    if (login == "null") {
        window.location.href = "/authoriz";
    }
    $('p.login').html('Hello, ' + login);
    $('.logout').append('<button type="button" class="btn btn-primary btn btn-default" onclick="logout()">Logout</button>');
}

function logout() {
    $.ajax({
        type: "post",
        url: "./logout",
        dataType: "json",
        data:  {logout: 'logout'},
        complete: function (data) {
            window.location.href = "/market";
        }
    });
}

$(function () {
    $('#upload').click(function () {
        var form = $("#myFile");
        var data = new FormData();
        data.append("file", form[0].files[0]);
        sendPhoto(data);
    });
});

function sendPhoto(photo) {
    $.ajax({
        type: "post",
        encType: "multipart/form-data",
        url: "/upload",
        cache: false,
        processData: false,
        contentType: false,
        data: photo,
        success: function (msg) {
            var response = JSON.parse(msg);
            var status = response.status;
            if (status == "1") {
                pathImage = response.url;
                alert("File has been uploaded successfully");
            } else {
                alert("Couldn't upload file");
            }
        },
        error: function (msg) {
            alert("Couldn't upload file");
        }
    })
}

$(function () {
    $('#ad').click(function () {
        createAd();
    });
});

function sendTo(carAd) {
    $.ajax({
        type: "post",
        url: "./ad",
        dataType: "json",
        data: JSON.stringify(carAd) ,
        complete: function () {
            $('#success').text("The ad has been added successfully");
            getCars(login);
        }
    });
}

function createAd() {
    var mark = $('#mark').find('option:selected').text();
    var model = $('#model').find('option:selected').text();
    var trans = $('#trans').find('option:selected').text();
    var body = $('#body').find('option:selected').text();
    var year = $('#year').val();
    var price = $('#price').val();
    var photo = $('#photo').val();
    if (validate(mark, model, trans, body, year, price, login, pathImage)) {
        var carAd = newAd(mark, model, trans, body, year, price, login, pathImage);
        cleanFields();
        sendTo(carAd);
    }
}

function cleanFields() {
    $('#mark').val('');
    $('#model').val('');
    $('#trans').val('');
    $('#body').val('');
    $('#year').val('');
    $('#price').val('');
    $('#photo').val('');
}


function validate(mark, model, trans, body, year, price, login, pathImage) {
    var result = true;
    if (mark == '') {
        alert("Please, fill in the 'Mark' field");
        result = false;
    }
    if (model == '') {
        alert("Please, fill in the 'Model' field");
        result = false;
    }
    if (trans == '') {
        alert("Please, fill in the 'Transmission' field");
        result = false;
    }
    if (body == '') {
        alert("Please, fill in the 'Body Type' field");
        result = false;
    }
    if (year == '') {
        alert("Please, fill in the 'Year' field");
        result = false;
    }
    if (price == '') {
        alert("Please, fill in the 'Price' field");
        result = false;
    }
    if (login == '') {
        alert("Error, user not found");
        result = false;
    }
    if (pathImage == '') {
        alert("Please, upload car photo");
        result = false;
    }
    return result;
}

function newAd(mark, model, trans, body, year, price, login, pathImage) {
    return {
        mark: mark,
        model: model,
        transmission: trans,
        bodyType: body,
        price: price,
        yearIssue: year,
        author: login,
        pathImage: pathImage

    };
}

function loadMarks() {
    $.ajax({
        url: "./parts",
        method: "get",
        data: {part: 'mark'},
        dataType: "json",
        complete:
            function (data) {
                var json = JSON.parse(data.responseText);
                for (var i = 0; i < json.length; i++) {
                    $('#mark').find('option:last').after('<option value=' + json[i]['id'] + '>' + json[i]['name'] + '</option>');
                }
            }
    })
}

function loadModels(mark) {
    $.ajax({
        url: "./parts",
        method: "get",
        data: {
            part: 'model',
            mark: mark
        },
        dataType: "json",
        complete:
            function (data) {
                var select = $("#model");
                select.empty();
                select.append("<option value=\"zero\"></option>");
                var json = JSON.parse(data.responseText);
                for (var i = 0; i < json.length; i++) {
                    $('#model').find('option:last').after('<option value=' + json[i]['id'] + '>' + json[i]['name'] + '</option>');
                }
            }
    })
}

function loadTransmission() {
    $.ajax({
        url: "./parts",
        method: "get",
        data: {part: 'trans'},
        dataType: "json",
        complete:
            function (data) {
                var json = JSON.parse(data.responseText);
                for (var i = 0; i < json.length; i++) {
                    $('#trans').find('option:last').after('<option value=' + json[i]['id'] + '>' + json[i]['name'] + '</option>');
                }
            }
    })
}

function loadBodyType() {
    $.ajax({
        url: "./parts",
        method: "get",
        data: {part: 'body'},
        dataType: "json",
        complete:
            function (data) {
                var json = JSON.parse(data.responseText);
                for (var i = 0; i < json.length; i++) {
                    $('#body').find('option:last').after('<option value=' + json[i]['id'] + '>' + json[i]['name'] + '</option>');
                }
            }
    });
}

function isSold(id) {
    $.ajax({
        url: "./sold",
        method: "post",
        data: {id: id},
        dataType: "json",
        complete:
            function () {
                getCars(login);
            }
    });
}
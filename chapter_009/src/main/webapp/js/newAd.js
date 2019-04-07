login = '';
url = '';

$(function () {
    getLogin();
    loadMarks();
    loadTransmission();
    loadBodyType();
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

function getLogin() {
    $.ajax({
        type: "get",
        url: "./authentic",
        dataType: "json",
        complete: function (data) {
            var json = JSON.parse(data.responseText);
            login = json.login;
            logoBox(login);
        }
    });
}

function logoBox(login) {
    var btnText = 'Enter profile';
    idBtn = 'profile';
    if (login == null) {
        window.location.href = "/authoriz";
    }
    $('p.login').html('Hello, ' + login);
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
            if (status == 1) {
                url = response.url;
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
        success: function () {
            $('#success').text("The ad has been added successfully");
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
    if (validate(mark, model, trans, body, year, price, login, url)) {
        var carAd = newAd(mark, model, trans, body, year, price, login, url);
        sendTo(carAd);
    }
}


function validate(mark, model, trans, body, year, price, login, url) {
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
    if (url == '') {
        alert("Please, upload car photo");
        result = false;
    }
    return result;
}

function newAd(mark, model, trans, body, year, price, login, imageURL) {
    return {
        mark: mark,
        model: model,
        transmission: trans,
        bodyType: body,
        yearIssue: year,
        price: price,
        user: login,
        pathImage: imageURL
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
                var models = json[0]['models'];
                for (var i = 0; i < models.length; i++) {
                    $('#model').find('option:last').after('<option value=' + models[i]['id'] + '>' + models[i]['name'] + '</option>');
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
    })
}
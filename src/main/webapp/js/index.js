$(document).ready(function(){
    getAll();
});

$(document).ready(function () {
    $('#notdone').change(function () {
        if (this.checked) {
            getNotDone();
        } else {
            getAll();
        }
    })
});

$(document).ready(function () {
    $(".btn").click(function () {
        createItem();
    })
});

function createItem() {
    var desc = $('#desc').val();
    if (validate(desc)) {
        sendTo(desc);
    }
    getAll();
}

function validate(value) {
    var result = true;
    if (value == '') {
        alert("Please, enter correct value of item");
        result = false;
    }
    return result;
}


function sendTo(desc) {
    $.ajax({
        type: "post",
        url: "./create",
        data: {desc: desc},
        success: function () {

        }
    });
}

function itemDone(id) {
    $.ajax({
        type: "post",
        url: "./done",
        data: {id: id},
        success: function () {
            getAll()
        }
    });
}

function getNotDone() {
    $("td").remove();
    $.ajax({
        url: "./info",
        method: "get",
        dataType: "json",
        complete:
            function (data) {
                var json = JSON.parse(data.responseText);
                for (var i = 0; i < json.length; i++) {
                    if (json[i]['done'] == false) {
                        $('#table tr:last').after('<tr id="' + json[i]['id'] + '"><td>' + json[i]['id'] + '</td><td>' + json[i]['desc'] + '</td><td>' + json[i]['created'] + '</td><td>' + json[i]['done'] + '</td><td><button  class="btn btn-success" class="btn btn-default" type="button" onclick="itemDone(' + json[i]['id'] + ')"><font-size="7">✓</font></button></td></tr>');
                    }
                }
            }
    })
}

function getAll() {
    $("td").remove();
    $.ajax({
        url: "./info",
        method: "get",
        dataType: "json",
        complete: function (data) {
            var json = JSON.parse(data.responseText);
            for (var i = 0; i < json.length; i++) {
                $('#table tr:last').after('<tr id="' + json[i]['id'] + '"><td>' + json[i]['id'] + '</td><td>' + json[i]['desc'] + '</td><td>' + json[i]['created'] + '</td><td>' + json[i]['done'] + '</td><td><button  class="btn btn-success" class="btn btn-default" type="button" onclick="itemDone(' + json[i]['id'] + ')"><font-size="7">✓</font></button></td></tr>');
            }
        }
    });
}
$(document).ready(function () {
    $(".btn").click(function () {
        sendTo();
    })
});

    function sendTo() {
        $.ajax({
            type: "get",
            url: "./signin",
            dataType: "json",
            complete: function (data) {
                var json = JSON.parse(data.responseText);
                for (var i = 0; i < json.length; i++) {
                    alert(json[i]['models'][0]['name']);
                }
            }
        });
    }
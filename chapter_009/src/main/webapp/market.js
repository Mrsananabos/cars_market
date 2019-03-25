// $(document).ready(function () {
//     $(".btn").click(function () {
//         sendTo();
//     })
// });
//
//     function sendTo() {
//         $.ajax({
//             type: "post",
//             url: "./signin",
//             dataType: "json",
//             complete: function (data) {
//                 var json = JSON.parse(data.responseText);
//                 for (var i = 0; i < json.length; i++) {
//                     var models = json.models[1];
//                 }
//             }
//         });
//     }
$(document).ready(function () {
    ratePost();
});

var rateUrl = "/post/rate";
var latestUrl = "/post/latest/9";

function ratePost() {
    $('.btn-rate').click(function () {
        var dataObject = JSON.stringify({
            postId: $(this).closest('input').find('.post-id'),
            // postId: $('#postId').val(),
            grade: $(this).val()
        });
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: rateUrl,
            type: "POST",
            data: dataObject,
            success: function(data){
               location.reload();
            }
        })
    })

}
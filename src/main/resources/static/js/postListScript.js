$(document).ready(function () {
    colorRatePost();
});

var rateUrl = "/post/rate";
var commentUrl = "/post/comment";

function colorRatePost() {
    var animationTime = 100;
    var colours = ["bd2c33", "e49420", "ecdb00", "3bad54", "1b7db9"];

    var ratingInfobox = $("<div />")
        .attr("id", "ratinginfo")
        .insertAfter($("ul").closest(".rating"));

    var colourizeRatings = function (ratingLi) {
        var nrOfRatings = $(ratingLi).index();

        ratingLi.siblings().each(function () {
            if ($(this).index() <= nrOfRatings) {
                $(this).stop().animate({backgroundColor: "#" + colours[nrOfRatings]}, animationTime);
            }
        });
    };

    $(".rating li").hover(function () {

        ratingInfobox
            .empty()
            .stop()
            .animate({opacity: 1}, animationTime);

        colourizeRatings($(this));
    }, function () {

        ratingInfobox
            .stop()
            .animate({opacity: 1}, animationTime);

        $(".rating li").stop().animate({backgroundColor: "#333"}, animationTime);
    });

    $(".rating li").click(function (e) {
        e.preventDefault();
        showToastrInfo();
        var gradeDiv = $(this).parents().find(".post-grade");
        var dataObject = JSON.stringify({
            postId: $(this).parent().attr('data-id'),
            grade: $(this).index() + 1
        });
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: rateUrl,
            type: "POST",
            data: dataObject,
            success: function (data) {
                gradeDiv.text(data.substr(0, 4))
            }
        })
    });
}

function showToastrInfo() {
    toastr.success("Post rated!", "Title", {
        "timeOut": "2000",
        "extendedTImeout": "2000",
        "closeButton": true
    });
}

$(document).on("click", ".btn-post-comment", function () {
    var dataObject = JSON.stringify({
        postId: $(this).attr("data-id"),
        content: $(this).parent().prev().find(".comment-content").val()
    });
    var index = $(this).attr("data-index");
    var comment = $(this).parents().find(".comment").prev().find('.comments-number[data-index=' + index + ']');
    $.ajax({
        contentType: "application/json; charset=utf-8",
        url: commentUrl,
        type: "POST",
        data: dataObject,
        success: function (data) {
            comment.html(data + " comments");
        }
    });

});











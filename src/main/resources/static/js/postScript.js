$(document).ready(function () {
    colorRatePost();
    openCommentPopup();
});

var rateUrl = "/post/rate";
var latestUrl = "/post/latest/9";


function colorRatePost() {
    var animationTime = 100;
    var colours = ["bd2c33", "e49420", "ecdb00", "3bad54", "1b7db9"];

    // var ratingInfobox = $("<div />")
    //     .attr("id", "ratinginfo")
    //     .insertAfter($("ul").closest(".rating"));

    var colourizeRatings = function (ratingLi) {
        var nrOfRatings = $(ratingLi).index();

        ratingLi.siblings().each(function () {
            if ($(this).index() <= nrOfRatings) {
                $(this).stop().animate({backgroundColor: "#" + colours[nrOfRatings]}, animationTime);
            }
        });
    };

    $(".rating li").hover(function () {
        var ratingInfobox = $("<div />")
            .attr("id", "ratinginfo")
            .insertAfter($(this).parent());

        ratingInfobox
            .empty()
            .stop()
            .animate({opacity: 1}, animationTime);

        $("<p />")
            .html($(this).html())
            .appendTo(ratingInfobox);

        colourizeRatings($(this));
    });
    $(".rating li").click(function (e) {
        e.preventDefault();
        showToastrInfo();
        var gradeDiv = $(this).parent().prev().find("#grade");
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

function openCommentPopup() {
    $(".btn-comment").on("click", function(){

    var width = 400;
    var height = 100;

    var center_left = (screen.width / 2) - (width / 2);
    var center_top = (screen.height / 2) - (200);
    var w = window.open("", "Title", "scrollbars=1, width="+width+", height="+height+", left="+center_left+", top="+center_top);
    var $w = $(w.document.body);
    $w.html($("#comment-modal").html());
    })
}

$(".btn-post-comment").on("click", function() {
    var dataObject = JSON.stringify({
        content:$("#text-comment").val(),
        postId:$(this).attr("data-id")
    });
    $.ajax({
        contentType: "application/json; charset=utf-8",
        url: rateUrl,
        type: "POST",
        data: dataObject,
        success: function (data) {
            console.log("success")
        }
    })

})




$(document).ready(function () {
    colorRatePost();
});

var rateUrl = "/post/rate";
var latestUrl = "/post/latest/9";


function colorRatePost() {
    var animationTime = 100;
    var colours = ["bd2c33", "e49420", "ecdb00", "3bad54", "1b7db9"];

    var ratingInfobox = $("<div />")
        .attr("id", "ratinginfo")
        .insertAfter($("#rating"));

    var colourizeRatings = function (nrOfRatings) {
        $('#rating li').each(function () {
            if ($(this).index() <= nrOfRatings) {
                $(this).stop().animate({backgroundColor: "#" + colours[nrOfRatings]}, animationTime);
            }
        });
    };

    $("#rating li").hover(function() {

        ratingInfobox
            .empty()
            .stop()
            .animate({ opacity : 1 }, animationTime);

        $("<p />")
            .html($(this).html())
            .appendTo(ratingInfobox);

        colourizeRatings($(this).index());
    }, function() {

        ratingInfobox
            .stop()
            .animate({ opacity : 0 }, animationTime);

        $("#rating li").stop().animate({ backgroundColor : "#333" } , animationTime);
    });
    $("#rating li").click(function(e) {
        e.preventDefault();
        var dataObject = JSON.stringify({
            postId : $(this).parent().attr('data-id'),
            grade: $(this).index() + 1
        });
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: rateUrl,
            type: "POST",
            data: dataObject,
            success:window.location.reload()
        })
    });

    // $("#rating li")
    //     .on("mouseleave", function() {
    //     $('#ratinginfo').remove();
    // })
}



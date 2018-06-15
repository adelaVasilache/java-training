$(document).ready(function () {
    console.log("test");
});

var commentUrl = "/post/comment";

$(document).on("click","#postComment", function () {
    var content = $("#postContent");
    var commentNumber = $("#comments-number");
    var dataObject = JSON.stringify({
        postId: $(content).attr("data-id"),
        content: $(content).val()
    });
    $.ajax({
        contentType: "application/json; charset=utf-8",
        url: commentUrl,
        type: "POST",
        data: dataObject,
        success: function (data) {
            var dt = new Date();
            var date = dt.getFullYear() + "-" + dt.getMonth() + "-" + dt.getDate() + " " + dt.getHours();
            commentNumber.html(data + " comments");
            $("#commentsList").prepend("<li class=\"list-group-item\">\n" +
                "                        <div class=\"d-flex w-100 justify-content-between\">\n" +
                "                            <h5 class=\"mb-1\">\n" +
                "                                user:\n" +
                "                            </h5>\n" +
                "                            <p class=\"mb-1\">test</p>\n" +
                "                            <small>"+ getCurrentTime()+"</small>\n" +
                "                        </div>\n" +
                "                    </li>");
        }
    });
});

function getCurrentTime(){
    var dt = new Date(),
        month = dt.getMonth() + 1,
        day = dt.getDate(),
        year = dt.getFullYear(),
        hour = dt.getHours(),
        minute = dt.getMinutes(),
        second = dt.getSeconds(),
        millisecond = dt.getMilliseconds();

    return [year, month, day].join("-") +" "+ [hour, minute, second].join(":") + "." + millisecond;
}

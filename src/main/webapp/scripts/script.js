
function getKey() {
    var res;
    $.ajax({
        url: "myserv"
        , type: "get"
        , async: false
    }).done(function (data) {
        res = data;
    });
    return res;
};

$(document).ready(function () {
    console.log("ready!!");
    $("#loginForm").submit(function (event) {
        event.preventDefault();
        event.stopPropagation();

        var params = JSON.parse(getKey());

        console.log("string key: " + params.public);

        var rsa = new RSAKey();
        rsa.setPublic(params.public, '10001');
        var rawPassword = $("#password").val();
        var encryptedPassword = rsa.encrypt(rawPassword);

        $("#passwordHidden").val(encryptedPassword);

        $(this).off("submit");
        this.submit();
    });

});
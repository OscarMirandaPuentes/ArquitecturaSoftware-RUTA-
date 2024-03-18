function saludar() {
    let a = $("#a").val();
    let b = $("#b").val();
    let myData = {
        a: a,
        b: b
    };
    $.ajax({
        url: 'hola',
        type: 'GET',
        data: myData,
        success: function (r) {
            paintAns(r);
        }
    });
}

function paintAns(ans) {
    $("#resultado").html("<h2>El resultado es: " + ans + "</h2>");
}

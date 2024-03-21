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

function jugar() {
    let id = $("#id").val();
    let pos = $("#posCarta").val();
    let accion = $("#accion").val(); // Corregir aquí de "action" a "accion"
    let myData = {
        id: id,
        pos: pos,
        accion: accion
    };
    $.ajax({
        url: 'jugar',
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

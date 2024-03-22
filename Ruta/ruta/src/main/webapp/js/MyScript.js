function saludar() {
    let id = $("#id").val();
    let nombre = $("#nombre").val();
    let myData = {
        id: id,
        nombre: nombre
    };
    $.ajax({
        url: 'start',
        type: 'POST',
        data: myData,
        success: function (r) {
            paintAns("INICIAR pj");
        }
    });
}

function iniciar() {

    $.ajax({
        url: 'start',
        type: 'GET',
        success: function (r) {
            paintAns("INICIAR");
        }
    });
}

function jugar() {
    let id = $("#id2").val();
    let posCarta = $("#posCarta").val();
    let accion = $("#accion").val(); // Corregir aquí de "action" a "accion"
    let myData = {
        id: id,
        posCarta: posCarta,
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

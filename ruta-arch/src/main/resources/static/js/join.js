var id = document.querySelector('input[type="number"]');

id.addEventListener('input', function() {
    var valor = id.value;
    
    if (valor.includes('e') || valor.includes('+') || valor.includes('-')) {
        id.value = '';
    }
});

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('form');
    
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        localStorage.setItem('idPartida', id.value); // Se almacena en memoria local

        if (id.value != '') {
            joinPJ(localStorage.email, localStorage.idPartida)
        }
    });
});

function joinPJ(email, idPartida) {
    let myData = {
        email: email,
        idPartida: idPartida,
    };
    
    $.ajax({
        url: '/api/jugadores/join',
        type: 'POST',
        data: JSON.stringify(myData),  // Convert the data object to a JSON string
        contentType: 'application/json',  // Specify the content type as JSON
        success: function (r) {
            redirect(r);
        }
    });
}

function redirect(response){
    console.log(response)
    if(response == ""){
        window.location.href = 'enter.html';
    }
    else{
        let parts = response.split(" ");
            let id = parts[1];
            localStorage.idPlayer = id;
            window.location.href = 'tablero.html';
    }
}

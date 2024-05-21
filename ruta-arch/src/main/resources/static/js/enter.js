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

        if (id.value != '') {
            ingresarPJ(localStorage.email, localStorage.idPartida, id.value)
            
        }
    });
});

function ingresarPJ(email, idPartida, numEquipo) {
    let myData = {
        email: email,
        idPartida: idPartida,
        numEquipo: numEquipo
    };
    
    $.ajax({
        url: '/api/jugadores',
        type: 'POST',
        data: JSON.stringify(myData),  // Convert the data object to a JSON string
        contentType: 'application/json',  // Specify the content type as JSON
        success: function (r) {
            console.log("Personaje agregado");
            let parts = r.split(" ");
            let id = parts[1];
            localStorage.idPlayer = id;
            window.location.href = 'tablero.html';
        }
    });
}
 


function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }
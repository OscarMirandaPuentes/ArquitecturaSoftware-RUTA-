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
        ajaxUserInfo();
    });

    var btnIniciar = document.getElementById('btn-iniciar');
    btnIniciar.addEventListener('click', function() {
        if (id.value != '') {
            iniciar();
            window.location.href = 'tablero.html';
        }
    });
});

function iniciar() {
    $.ajax({
        url: '/api/start',
        type: 'GET',
        success: function (r) {
            console.log("Juego iniciado")
        }
    });
}

function ajaxUserInfo() {
    // Obtener el correo electrónico del localStorage
    var email = localStorage.email;

    // Verificar si se encontró un correo electrónico en el localStorage
    if (email) {
    // Datos que deseas enviar al servidor
    var requestData = {
        "email": email
    };
        $.ajax({
            url: "/api/user/info",
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(requestData),
            dataType: "json",
            beforeSend: function(xhr) {
                // Obtener el token de la cookie
                var token = getCookie("access_token");
                if (token) {
                    // Agregar el token JWT a la cabecera de autorización
                    xhr.setRequestHeader("Authorization", "Bearer " + token);
                }
            },
            success: function (r) {
                var name = r.name
                console.log(name)
                ingresarPJ(id.value, name)
            }

        });

    }
}


function ingresarPJ(id, nombre) {
    console.log(id)
    let myData = {
        id: id,
        nombre: nombre
    };
    
        // Agregar la cookie al encabezado de la solicitud
        $.ajax({
            url: '/api/start',
            type: 'POST',
            data: myData,
            success: function (r) {
                console.log("Personaje agregado")
            }
        });
} 

        
function iniciarJuego() {
    iniciar()
    console.log('El juego ha comenzado');
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
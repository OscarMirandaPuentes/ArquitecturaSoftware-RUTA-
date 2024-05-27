var cartaId
var id = localStorage.getItem('id')

document.addEventListener('DOMContentLoaded', function () {
    
    $(document).ready(function(){

        $('.mano a').on('click', function() {
            cartaId = $(this).find('img').attr('id');
        });

        $('#usar').on('click', function() {
            var posCarta = parseInt(cartaId.match(/\d+/)[0]) - 1;
            jugar(localStorage.idPartida, localStorage.idPlayer , posCarta, 0)
            $.modal.close();
            alert('Has elegido usar la carta.');
        });

        $('#descartar').on('click', function() {
            var numeroCarta = parseInt(cartaId.match(/\d+/)[0] - 1);
            jugar(localStorage.idPartida, localStorage.idPlayer , numeroCarta, 1)
            $.modal.close();
            alert('Has elegido descartar la carta.');
        });

        $('#refrescar').on('click', function() {
            refresh(id)
            //location.reload();
        });

        $('#regresar').on('click', function() {
            window.location.href = 'home.html'
        });
    });


    setName()
});

function jugar(idPartida, idPlayer, posCarta, accion) {
    let myData = {
        idPartida: idPartida,
        idPlayer: idPlayer,
        posCarta: posCarta,
        accion: accion
    };
    $.ajax({
        url: '/api/jugada',
        type: 'POST',
        data: JSON.stringify(myData),
        contentType: 'application/json',  // Specify the content type as JSON
        success: function (r) {
            console.log(r)
            refresh()
        }
    });
}

function refresh() {
    let idPartida = localStorage.idPartida; // Assuming you store idPartida in localStorage
    let idPlayer = localStorage.idPlayer; // Assuming you store idPlayer in localStorage
    $.ajax({
        url: `/api/visual/partida/${idPartida}/jugador/${idPlayer}`,
        type: 'GET',
        dataType: 'json',
        success: function (r) {
            console.log(r);
            updateWin(r);
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
            // Handle error accordingly
        }
    });
}


function updateWin(datos){
    for (const [carta, valor] of Object.entries(datos)) {
        reemplazarURLCarta(carta, valor); // Llamar a la función reemplazarURLCarta con cada clave y valor
    }
}

function setName() {
    // Obtener el correo electrónico del localStorage
    var userEmail = localStorage.email;

    // Verificar si se encontró un correo electrónico en el localStorage
    if (userEmail) {
    // Datos que deseas enviar al servidor
    var requestData = {
        email: userEmail
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
                document.getElementById('nombreJ').textContent = r.name;
            }

        });

    }
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



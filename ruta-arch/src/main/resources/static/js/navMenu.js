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
                console.log(r)
                document.getElementById('nombreU').textContent = r.name
            },
        });
}
}

document.addEventListener('DOMContentLoaded', function () {
    
    ajaxUserInfo()
    document.getElementById('inicio').addEventListener('click', function () {
        window.location.href ='home.html';
    });

    document.getElementById('partidas').addEventListener('click', function () {
        window.location.href ='games.html';
        
    });

    document.getElementById('movimientos').addEventListener('click', function () {
        
        window.location.href ='history.html';
    });

    document.getElementById('perfil').addEventListener('click', function () {
        
        window.location.href ='userProfile.html';
    });

    document.getElementById('cerrar_sesion').addEventListener('click', function () {
        limpiarCookies();
        window.location.href ='login-register.html';
    });

    document.getElementById('cerrar_sesion2').addEventListener('click', function () {
        limpiarCookies();
        window.location.href ='login-register.html';
    });

    
});

function limpiarCookies() {
    document.cookie.split(";").forEach(function(c) {
    document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
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
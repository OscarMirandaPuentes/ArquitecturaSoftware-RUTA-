document.addEventListener('DOMContentLoaded', function () {
    
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

function nombreMenu() {
    let myData = {
        action: "EMAIL"
    };
    var email = document.cookie.replace(/(?:(?:^|.*;\s*)email\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    
        // Agregar la cookie al encabezado de la solicitud
        $.ajax({
            url: '/ruta/info',
            type: 'POST',
            data: myData,
            xhrFields: {
                withCredentials: true
            },
            headers: {
                'Cookie': 'email=' + email 
            },
            success: function (r) {
                console.log(r)
                document.getElementById('nombreU').textContent = r
            }
        });
    } 
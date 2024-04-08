document.addEventListener('DOMContentLoaded', function () {
    
    document.getElementById('inicio').addEventListener('click', function () {
        let pathname = window.location.pathname;

        // Dividir la ruta en partes usando el separador "/"
        let partes = pathname.split("/");
        if (partes[0] === "") {
            partes.shift();
        }
        if (partes.length === 1) {
            window.location.href ='html/home.html';
        } else {
            pagina = "home.html";
        }
        
    });

    document.getElementById('partidas').addEventListener('click', function () {
        let pathname = window.location.pathname;

        // Dividir la ruta en partes usando el separador "/"
        let partes = pathname.split("/");
        if (partes[0] === "") {
            partes.shift();
        }
        if (partes.length === 1)  {
            window.location.href ='html/games.html';
        } else {
            pagina = "games.html";
        }
    });

    document.getElementById('movimientos').addEventListener('click', function () {
        let pathname = window.location.pathname;

        // Dividir la ruta en partes usando el separador "/"
        let partes = pathname.split("/");
        if (partes[0] === "") {
            partes.shift();
        }
        if (partes.length === 1)  {
            window.location.href ='html/history.html';
        } else {
            pagina = "history.html";
        }
    });

    document.getElementById('perfil').addEventListener('click', function () {
        let pathname = window.location.pathname;

        // Dividir la ruta en partes usando el separador "/"
        let partes = pathname.split("/");
        if (partes[0] === "") {
            partes.shift();
        }
        if (partes.length === 1) {
            window.location.href ='html/userProfile.html';
        } else {
            pagina = "userProfile.html";
        }
    });

    document.getElementById('cerrar_sesion').addEventListener('click', function () {
        limpiarCookies();
        let pathname = window.location.pathname;

        // Dividir la ruta en partes usando el separador "/"
        let partes = pathname.split("/");
        if (partes[0] === "") {
            partes.shift();
        }
        if (partes.length === 1)  {
            window.location.href ='html/login-register.html';
        } else {
            pagina = "login-register.html";
        }
    });

    document.getElementById('cerrar_sesion2').addEventListener('click', function () {
        limpiarCookies();
        let pathname = window.location.pathname;

        // Dividir la ruta en partes usando el separador "/"
        let partes = pathname.split("/");
        if (partes[0] === "") {
            partes.shift();
        }
        if (partes.length === 1)  {
            window.location.href ='html/login-register.html';
        } else {
            pagina = "login-register.html";
        }
    });
});

function limpiarCookies() {
    document.cookie.split(";").forEach(function(c) {
    document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/");
    });
}

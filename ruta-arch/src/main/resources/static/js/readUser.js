const nombreUsuario = document.querySelector('#nombre-usuario');
const nombre = document.querySelector('#nombre');
const apodoPerfil = document.querySelector('#apodo-usuario');
const correoPerfil = document.querySelector('#correo-usuario');
cargarPerfilUsuario();

function cargarPerfilUsuario() {
    // petición AJAX para obtener los datos del perfil del usuario desde el servidor
    ajaxUserInfo()
    .then(function(response) {
        console.log('Registro exitoso:', response);
    })
    .catch(function(error) {
        console.error('Error en la solicitud:', error);
    });
    
    const perfilUsuario = {
        nombre: 'Nombre de Usuario',
        apodo: 'Apodo de Usuario',
        correo: 'correo@corr.com'
    };

    nombre.textContent = perfilUsuario.nombre;
    nombreUsuario.textContent = perfilUsuario.nombre;
    apodoPerfil.textContent = perfilUsuario.apodo;
    correoPerfil.textContent = perfilUsuario.correo;
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
    return new Promise((resolve, reject) => {
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
                resolve(r);
            },
            error: function (err) {
                reject(err);
            }
        });
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
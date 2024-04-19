const nombreUsuario = document.querySelector('#nombre-usuario');
const nombre = document.querySelector('#nombre');
const apodoPerfil = document.querySelector('#apodo-usuario');
const correoPerfil = document.querySelector('#correo-usuario');
cargarPerfilUsuario();

function cargarPerfilUsuario() {
    // petición AJAX para obtener los datos del perfil del usuario desde el servidor
    
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
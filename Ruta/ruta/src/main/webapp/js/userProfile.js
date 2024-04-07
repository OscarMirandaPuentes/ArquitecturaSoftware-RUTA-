var x = document.getElementById("profile");
var y = document.getElementById("edit-form");
const btnEdit = document.getElementById('editar');
const btnCancel = document.getElementById('cancelar');
const btnImg = document.getElementById('cambiar')

document.addEventListener('DOMContentLoaded', function() {
    
    const nombreUsuario = document.querySelector('#nombre-usuario');
    const nombre = document.querySelector('#nombre');
    const apodoPerfil = document.querySelector('#apodo-usuario');
    const correoPerfil = document.querySelector('#correo-usuario');
    const botonEditarPerfil = document.querySelector('#editar');
    const botonEliminarCuenta = document.querySelector('#eliminar');
    const botonCerrarSesion = document.querySelectorAll('#cerrar_sesion');

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

    botonEditarPerfil.addEventListener('click', function() {
        console.log('editar')
    });

    // Evento para eliminar la cuenta del usuario
    botonEliminarCuenta.addEventListener('click', function() {
        console.log('Eliminar cuenta');
    });

    // Evento para cerrar sesión
    botonCerrarSesion.forEach(boton => {
        boton.addEventListener('click', function() {
            window.location.href ='login-register.html';
        });
    });
});

y.style.display='none';

btnEdit.addEventListener('click', function() {
    y.style.display = '';
    x.style.display = 'none';
});

btnCancel.addEventListener('click', function() {
    x.style.display = '';
    y.style.display = 'none';
});

btnImg.addEventListener('click', function() {
    elegirImagen()
});

function elegirImagen() {

    var input = document.createElement('input');
    input.type = 'file';

    input.accept = 'image/*'; 
    input.multiple = false; 

    input.addEventListener('change', function(event) {

        var file = event.target.files[0];
        var reader = new FileReader();

        reader.onload = function(e) {
            
            var imageUrl = e.target.result;
            var imgElement = document.getElementById('profile-pic');
            imgElement.src = imageUrl;
        };

        reader.readAsDataURL(file);
    });
    input.click();
}

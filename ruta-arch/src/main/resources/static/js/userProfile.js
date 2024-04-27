document.addEventListener('DOMContentLoaded', function() {

    const botonEditarPerfil = document.querySelector('#editar');
    const botonEliminarCuenta = document.querySelector('#eliminar');
    var x = document.getElementById('profile');
    var y = document.getElementById('edit-form');
    const btnCancel = document.getElementById('cancelar');
    const btnImg = document.getElementById('cambiar');
    const btnSave = document.getElementById('guardar');

    botonEditarPerfil.addEventListener('click', function() {
        y.style.display = '';
        x.style.display = 'none';
    });

    botonEliminarCuenta.addEventListener('click', function() {
        console.log('Eliminar cuenta');
    });

    btnCancel.addEventListener('click', function() {
        x.style.display = '';
        y.style.display = 'none';
    });

    btnImg.addEventListener('click', function() {
        elegirImagen()
    });

    btnSave.addEventListener('click', function(event) {
        const nombre = document.getElementById('name').value;
        const apodo = document.getElementById('nickname').value;
        const correo = document.getElementById('email').value;
        const imagen = document.getElementById('profile-pic').src;
        updateUser(nombre, apodo, correo)
        //location.reload()

        // código para enviar los datos al servidor 
        
    });
});

function updateUser(name, nick, email){
    let userDetalles = {
            name: name,
            nick: nick,
            email: email
    };

    $.ajax({
        type: "PUT", // Método HTTP a utilizar
        url: "api/user/" + localStorage.email, // URL del endpoint en el servidor
        contentType: "application/json", // Tipo de contenido que se está enviando
        data: JSON.stringify(userDetalles),
        beforeSend: function(xhr) {
            // Obtener el token de la cookie
            var token = getCookie("access_token");
            if (token) {
                // Agregar el token JWT a la cabecera de autorización
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            }
        }, // Convertir los datos del usuario a formato JSON
        success: function(response) {
            // La solicitud fue exitosa
            localStorage.email = email
            console.log("Usuario actualizado correctamente");
            console.log("Respuesta del servidor:", response);
        },
        error: function(xhr, status, error) {
            console.error("Error al actualizar el usuario:", error);
        }
    });
}

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

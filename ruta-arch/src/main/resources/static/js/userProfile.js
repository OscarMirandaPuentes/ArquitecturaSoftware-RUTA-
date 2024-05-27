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
        deleteUser();
    });

    btnCancel.addEventListener('click', function() {
        x.style.display = '';
        y.style.display = 'none';
    });

    btnImg.addEventListener('click', function() {
        elegirImagen()
    });

    btnSave.addEventListener('click', function(event) {

        event.preventDefault();

        const nombre = document.getElementById('name').value;
        const apodo = document.getElementById('nickname').value;
        const correo = document.getElementById('email').value;
        const imagen = document.getElementById('profile-pic').src;
        updateUser(nombre, apodo, correo).then(function(response) {
            console.log('Cambio exitoso:', response);
            location.reload();
        })
        .catch(function(error) {
            console.error('Error en la solicitud:', error);
        });

        // código para enviar los datos al servidor 
        
    });
});

function updateUser(name, nick, email){
    return new Promise((resolve, reject) => {
        let myData = {
            name: name,
            nick: nick,
            email: email,
        };
        $.ajax({
            url: "/api/user/" + localStorage.email,
            type: 'PUT',
            contentType: "application/json",
            data: JSON.stringify(myData),
            dataType: "json",
            success: function (r) {
                localStorage.email = myData.email;
                setCookie("access_token", r['access_token'], 1);
                setCookie("refresh_token", r['refresh_token'], 1);
                resolve(r);
            },
            error: function (err) {
                reject(err);
            }
        });
    });
}

function deleteUser(){
    let email = {
            "email": localStorage.email
    };

    $.ajax({
        type: "DELETE", // Corregir el método HTTP a utilizar
        url: "/api/user", // URL del endpoint en el servidor
        contentType: "application/json", // Tipo de contenido que se está enviando
        data: JSON.stringify(email),
        success: function(response) {
            // La solicitud fue exitosa
            console.log(response)
            limpiarCookies();
            window.location.href ='login-register.html';
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

function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

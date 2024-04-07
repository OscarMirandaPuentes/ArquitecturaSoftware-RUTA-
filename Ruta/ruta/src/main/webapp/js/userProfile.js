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

        location.reload()

        // código para enviar los datos al servidor 
    });
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

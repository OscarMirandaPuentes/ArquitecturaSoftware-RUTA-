var a = document.getElementById("loginBtn");
var b = document.getElementById("registerBtn");
var x = document.getElementById("loginc");
var y = document.getElementById("registerc");
const btnLogin = document.getElementById('login');
const btnRegister = document.getElementById('register');


function goLogin() {
    x.style.left = "4px";
    y.style.right = "-520px";
    a.className += " white-btn";
    b.className = "btn";
    x.style.opacity = 1;
    y.style.opacity = 0;
}

function goRegister() {
    x.style.left = "-510px";
    y.style.right = "5px";
    a.className = "btn";
    b.className += " white-btn";
    x.style.opacity = 0;
    y.style.opacity = 1;
}

btnLogin.addEventListener('click', function(event) {
    event.preventDefault(); 
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    if (!email.trim() || !password.trim()) {
        alert('Por favor, complete todos los campos.');
        return;
    }
    let pathname = window.location.pathname;

        // Dividir la ruta en partes usando el separador "/"
        let partes = pathname.split("/");
        if (partes[0] === "") {
            partes.shift();
        }
        if (partes.length === 1){
            window.location.href = 'html/home.html';
        }  else{
            window.location.href = 'home.html';
        }
});

btnRegister.addEventListener('click', function(event) {
    event.preventDefault(); 
    const name = document.getElementById('namer').value;
    const nickname = document.getElementById('nicknamer').value;
    const email = document.getElementById('emailr').value;
    const password = document.getElementById('passwordr').value;
    if (!name.trim() || !nickname.trim() || !email.trim() || !password.trim()) {
        alert('Por favor, complete todos los campos.');
        return;
    }
    //Agregar encriptación de contraseña

    //Logica de guardado de datos
    ajaxRegister("CREATE",name, nickname, email, password)
    .then(function(response) {
        console.log('Registro exitoso:', response);
        goLogin();
    })
    .catch(function(error) {
        console.error('Error en la solicitud:', error);
    });
    
});


function ajaxRegister(action, name, nickname, email, password) {
    return new Promise((resolve, reject) => {
        let myData = {
            action: action,
            name: name,
            nickname: nickname,
            email: email,
            password: password
        };
        $.ajax({
            url: '/ruta/usuario',
            type: 'POST',
            data: myData,
            success: function (r) {
                resolve(r); // Resolvemos la promesa con el valor booleano del response
            },
            error: function (err) {
                reject(err); // Rechazamos la promesa en caso de error
            }
        });
    });
}
